/**
*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.realeyes.hlsparserj

import com.realeyes.hlsparserj.tags.Tag
import com.realeyes.hlsparserj.tags.TagFactory
import com.realeyes.hlsparserj.tags.TagNames
import com.realeyes.hlsparserj.tags.UnparsedTag
import com.realeyes.hlsparserj.tags.media.*
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.set


/**
 * Abstract class for a media playlist.
 */
abstract class MediaPlaylist(version: PlaylistVersion, tags: MutableList<UnparsedTag>) : AbstractPlaylist(version, tags) {
    /**
     * Return list of media segments tags in this media playlist.
     *
     * Tag key: EXTINF.
     *
     * @return list of media segments tags
     */
    val segments = getGenericSegments(TagNames.EXTINF) as List<ExtInf?>

    /**
     * Return list of byte ranges tags in the media playlist.
     *
     * Tag key: EXT-X-BYTERANGE.
     *
     * @return list of byte ranges tags
     */
    val byteRanges= getGenericSegments(TagNames.EXTXBYTERANGE) as List<ByteRange?>

    /**
     * Returns the target duration tag.  Value specifies the maximum media segment duration.
     *
     * Tag key: EXT-X-TARGETDURATION.
     *
     * @return target duration tag
     */
    val targetDuration = getTag(TagNames.EXTXTARGETDURATION) as TargetDuration?

    /**
     * Return the media sequence tag in the playlist.  Sequence number represents the
     * sequence number of the first segment that appeared in the playlist file.
     *
     * Tag key: EXT-X-MEDIA-SEQUENCE.
     *
     * @return media sequence
     */
    val mediaSequence = getTag(TagNames.EXTXMEDIASEQUENCE) as MediaSequence?

    /**
     * Return the allow cache tag in the playlist.  Indicates whether the client MAY
     * or MUST NOT cache downloaded media segment for later replay.  It MAY occur
     * anywhere in a Media Playlist file; it MUST NOT occur more than once.
     *
     * Tag key: EXT-X-ALLOW-CACHE.
     *
     * @return allow cache tag
     */
    val allowCache = getTag(TagNames.EXTXALLOWCACHE) as AllowCache?

    /**
     * Return the playlist type tag in the playlist.  Provides mutability information
     * about the playlist file.  It applies to the entire Playlist file.  It is
     * OPTIONAL.  Its format is:
     *
     * #EXT-X-PLAYLIST-TYPE:&lt;EVENT|VOD&gt;
     *
     * Tag key: EXT-X-PLAYLIST-TYPE.
     *
     * @return playlist type
     */
    val playlistType = getTag(TagNames.EXTXPLAYLISTTYPE) as PlaylistType?

    override val isMasterPlaylist = false

    /**
     * Returns list of 'generic' media segments found in this playlist.  Function is
     * aware of common tags in the playlists that affects the segments (discontinuity,
     * key, and dateTime).
     * @param segmentTagName segment tag name
     * @return list of tags
     */
    private fun getGenericSegments(segmentTagName: String): List<Tag>? {
        var tagList: MutableList<Tag>? = parsedTagListCache[segmentTagName]
        if (tagList == null) {
            tagList = ArrayList()
            var discontinuity = false
            var mediaSequence = 1
            var discontinuitySequence = 1
            var cueIn = false
            var map: String? = null
            var programDateTime: String? = null
            var key: Key? = null
            var caid: String? = null
            var scte35: SCTE35? = null
            var breakDuration: Float? = null
            var breakElapsed: Float? = null
            var breakId: Int? = null
            var breakPts: Float? = null
            var tagSet: MutableList<Tag> = mutableListOf()
            var absoluteTime: Long = 0
            var startTime: Float? = null
            var timeUpdated = false
            var endList = false
            for (unparsedTag in tags) {
                val tagName: String? = unparsedTag.tagName
                val parsedTag: Tag? = TagFactory.createTag(tagName, unparsedTag)
                var duration = 0F
                if (tagName == TagNames.EXTINF) {
                    val infTag = parsedTag as ExtInf
                    duration = infTag.duration ?: 0F
                }
                when (tagName) {
                    TagNames.EXTXMEDIASEQUENCE -> {
                        mediaSequence = (parsedTag as MediaSequence).sequenceNumber
                    }
                    TagNames.EXTXDISCONTINUITYSEQUENCE -> {
                        discontinuitySequence = (parsedTag as DiscontinuitySequence).sequenceNumber
                    }
                    TagNames.EXTXDISCONTINUITY -> {
                        discontinuity = true
                    }
                    TagNames.EXTXPROGRAMDATETIME -> {
                        programDateTime = unparsedTag.getAttributes()["NONAME0"]
                        absoluteTime = timeFromISO(programDateTime)
                        timeUpdated = true
                    }
                    TagNames.EXTXKEY -> {
                        key = parsedTag as Key
                        tagSet.add(parsedTag)
                    }
                    TagNames.EXTXASSET -> {
                        caid = (parsedTag as Asset).caid
                        tagSet.add(parsedTag)
                    }
                    TagNames.EXTXCUE -> {
                        val cueTag = parsedTag as Cue
                        caid = cueTag.caid
                        breakDuration = cueTag.duration
                        breakId = cueTag.id
                        breakPts = cueTag.time
                        tagSet.add(parsedTag)
                    }
                    TagNames.EXTXCUEIN -> {
                        cueIn = true
                    }
                    TagNames.EXTXSCTE35 -> {
                        scte35 = parsedTag as SCTE35
                        tagSet.add(parsedTag)
                    }
                    TagNames.EXTXMAP -> {
                        map = (parsedTag as SegmentMap).uri
                        tagSet.add(parsedTag)
                    }
                    TagNames.EXTXCUEOUT -> {
                        breakDuration = (parsedTag as CueOut).duration
                        tagSet.add(parsedTag)
                    }
                    TagNames.EXTXCUEOUTCONT -> {
                        val cueOutTag = parsedTag as CueOutCont
                        breakDuration = cueOutTag.duration
                        breakElapsed = cueOutTag.elapsedTime
                        tagSet.add(parsedTag)
                    }
                    TagNames.EXTXENDLIST -> {
                        endList = true
                    }
                    segmentTagName -> {
                        val segment = parsedTag as Segment
                        startTime = if (startTime == null) 0F else startTime + duration
                        segment.tag = unparsedTag
                        segment.pdtUpdated = timeUpdated
                        segment.discontinuity = discontinuity
                        segment.cueIn = cueIn
                        segment.key = key
                        segment.dateTime = programDateTime
                        segment.breakDuration = breakDuration
                        segment.breakElapsed = breakElapsed
                        segment.breakId = breakId
                        segment.breakPts = breakPts
                        segment.caid = caid
                        segment.map = map
                        segment.scte35 = scte35
                        segment.mediaSequence = mediaSequence
                        segment.discontinuitySequence = discontinuitySequence
                        segment.tags = tagSet
                        segment.startTime = startTime
                        segment.pdt = absoluteTime
                        segment.endList = endList
                        tagList.add(segment)
                        absoluteTime += (duration * 1000F).toLong()
                        discontinuity = false
                        cueIn = false
                        timeUpdated = false
                        programDateTime = null
                        caid = null
                        scte35 = null
                        breakDuration = null
                        breakElapsed = null
                        breakId = null
                        breakPts = null
                        tagSet = mutableListOf()
                    }
                    else -> {
                        if (parsedTag != null && !parsedTag.headerTag) {
                            tagSet.add(parsedTag)
                        }
                    }
                }
            }
            parsedTagListCache[segmentTagName] = tagList
        }
        return tagList
    }

    /**
     * Return boolean to indicate if this playlist consists of I-Frames only.
     * @return boolean flag
     */
    val iFramesOnly: Boolean
        get() {
            val ifo = getTag(TagNames.EXTXIFRAMESONLY) ?: return false
            return true
        }

    private fun timeFromISO(dateStr: String?): Long {
        return try {
            val ta = DateTimeFormatter.ISO_DATE_TIME.parse(dateStr)
            val i = Instant.from(ta)
            Date.from(i).time
        } catch (e: Exception) {
            0
        }
    }
}
