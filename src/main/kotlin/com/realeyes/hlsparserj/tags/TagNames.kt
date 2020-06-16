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
package com.realeyes.hlsparserj.tags

import com.realeyes.hlsparserj.tags.master.IFrameStreamInf
import com.realeyes.hlsparserj.tags.master.Media
import com.realeyes.hlsparserj.tags.master.StreamInf
import com.realeyes.hlsparserj.tags.media.AllowCache
import com.realeyes.hlsparserj.tags.media.Asset
import com.realeyes.hlsparserj.tags.media.ByteRange
import com.realeyes.hlsparserj.tags.media.Cue
import com.realeyes.hlsparserj.tags.media.CueOut
import com.realeyes.hlsparserj.tags.media.CueOutCont
import com.realeyes.hlsparserj.tags.media.DiscontinuitySequence
import com.realeyes.hlsparserj.tags.media.ExtInf
import com.realeyes.hlsparserj.tags.media.IFramesOnly
import com.realeyes.hlsparserj.tags.media.Key
import com.realeyes.hlsparserj.tags.media.MediaSequence
import com.realeyes.hlsparserj.tags.media.PlaylistType
import com.realeyes.hlsparserj.tags.media.SCTE35
import com.realeyes.hlsparserj.tags.media.SegmentMap
import com.realeyes.hlsparserj.tags.media.TargetDuration

/**
 * Contains the playlist tag names.  Automatically registers them with the tag factory.
 */
object TagNames {
    /**
     * Version tag.
     *
     * <pre>
     * Format:
     * #EXT-X-VERSION:&lt;n&gt;
     *
     * Example:
     * #EXT-X-VERSION:4
    </pre> *
     */
    const val EXTXVERSION = "EXT-X-VERSION"
    /**
     * Variant stream tag.
     *
     * <pre>
     * Format:
     * #EXT-X-STREAM-INF:&lt;attribute-list&gt;
     * &lt;URI&gt;
     *
     * Example:
     * #EXT-X-STREAM-INF:PROGRAM-ID=1, BANDWIDTH=200000
     * gear1/prog_index.m3u8
    </pre> *
     */
    const val EXTXSTREAMINF = "EXT-X-STREAM-INF"
    /**
     * Media playlist that contain alternate rendition tag.
     *
     * <pre>
     * Format:
     * #EXT-X-MEDIA:&lt;attribute-list&gt;
     *
     * Example:
     * #EXT-X-MEDIA:TYPE=AUDIO,GROUP-ID="Audio1",NAME="mp4a",LANGUAGE="spa",DEFAULT=YES,AUTOSELECT=YES,URI="A1.m3u8"
    </pre> *
     */
    const val EXTXMEDIA = "EXT-X-MEDIA"
    /**
     * Media segment tag.
     *
     * <pre>
     * Format:
     * #EXTINF:&lt;duration&gt;,&lt;title&gt;
     * &lt;URI&gt;
     *
     * Example:
     * #EXTINF:2.002,338559
     * 20140311T113819-01-338559live.ts
    </pre> *
     */
    const val EXTINF = "EXTINF"
    /**
     * Byte range tag.
     *
     * <pre>
     * Format:
     * #EXT-X-BYTERANGE:&lt;n&gt;[@&lt;o&gt;]
    </pre> *
     */
    const val EXTXBYTERANGE = "EXT-X-BYTERANGE"
    /**
     * Target duration tag.
     *
     * <pre>
     * Format:
     * #EXT-X-TARGETDURATION:&lt;s&gt;
     *
     * Target:
     * #EXT-X-TARGETDURATION:3
    </pre> *
     */
    const val EXTXTARGETDURATION = "EXT-X-TARGETDURATION"
    /**
     * Media sequence tag.
     *
     * <pre>
     * Format:
     * #EXT-X-MEDIA-SEQUENCE:&lt;number&gt;
     *
     * Example:
     * #EXT-X-MEDIA-SEQUENCE:338559
    </pre> *
     */
    const val EXTXMEDIASEQUENCE = "EXT-X-MEDIA-SEQUENCE"
    /**
     * Discontinuity sequence tag.
     *
     * <pre>
     * Format:
     * #EXT-X-DISCONTINUITY-SEQUENCE:&lt;number&gt;
     *
     * Example:
     * #EXT-X-DISCONTINUITY-SEQUENCE:338559
    </pre> *
     */
    const val EXTXDISCONTINUITYSEQUENCE = "EXT-X-DISCONTINUITY-SEQUENCE"
    /**
     * Discontinuity tag.
     *
     * No discontinuity class is required.
     */
    const val EXTXDISCONTINUITY = "EXT-X-DISCONTINUITY"
    /**
     * Key tag.
     *
     * <pre>
     * Format:
     * #EXT-X-KEY:&lt;attribute-list&gt;
     *
     * Example:
     * #EXT-X-KEY:METHOD=AES-128,URI="https://security.domain.com",IV=0xaa2649ce170b734
    </pre> *
     */
    const val EXTXKEY = "EXT-X-KEY"
    /**
     * Program date time tag.
     *
     * (No date-time class is required)
     *
     * <pre>
     * Format:
     * #EXT-X-PROGRAM-DATE-TIME:&lt;YYYY-MM-DDThh:mm:ssZ&gt;
     *
     * Example:
     * #EXT-X-PROGRAM-DATE-TIME:2010-02-19T14:54:23.031+08:00
    </pre> *
     */
    const val EXTXPROGRAMDATETIME = "EXT-X-PROGRAM-DATE-TIME"
    /**
     * Allow cache tag.
     *
     * <pre>
     * Format:
     * #EXT-X-ALLOW-CACHE:&lt;YES|NO&gt;
     *
     * Example:
     * #EXT-X-ALLOW-CACHE:YES
    </pre> *
     */
    const val EXTXALLOWCACHE = "EXT-X-ALLOW-CACHE"
    /**
     * Playlist type tag.
     *
     * <pre>
     * Format:
     * #EXT-X-PLAYLIST-TYPE:&lt;EVENT|VOD&gt;
     *
     * Example:
     * #EXT-X-PLAYLIST-TYPE:EVENT
    </pre> *
     */
    const val EXTXPLAYLISTTYPE = "EXT-X-PLAYLIST-TYPE"
    /**
     * Iframes only tag.
     *
     * <pre>
     * Format:
     * #EXT-X-I-FRAMES-ONLY
    </pre> *
     */
    const val EXTXIFRAMESONLY = "EXT-X-I-FRAMES-ONLY"
    /**
     * Iframes stream tag.
     *
     * <pre>
     * Format:
     * #EXT-X-STREAM-INF:&lt;attribute-list&gt;
     *
     * Example:
     * #EXT-X-I-FRAME-STREAM-INF:BANDWIDTH=28451,CODECS="avc1.4d400d",URI="iframe_index.m3u8"
    </pre> *
     */
    const val EXTXIFRAMESSTREAMINF = "EXT-X-I-FRAME-STREAM-INF"
    /**
     * SCTE35 tag.
     *
     * <pre>
     * Format:
     * #EXT-X-SCTE35:&lt;attribute-list&gt;
     *
     * Example:
     * #EXT-X-SCTE35:/DA0AAAAAAAAAAAABQb+ADAQ6QAeAhxDVUVJQAAAO3/PAAEUrEoICAAAAAAg+2UBNAAANvrtoQ==
    </pre> *
     */
    const val EXTXSCTE35 = "EXT-X-SCTE35"
    /**
     * Cue tag.
     *
     * <pre>
     * Format:
     * #EXT-X-CUE:&lt;attribute-list&gt;
     *
     * Example:
     * #EXT-X-CUE:DURATION="201.467",ID="0",TYPE="SpliceOut",TIME="414.171"
    </pre> *
     */
    const val EXTXCUE = "EXT-X-CUE"
    /**
     * Cue out tag.
     *
     * <pre>
     * Format:
     * #EXT-X-CUE-OUT:&lt;attribute-list&gt;
     *
     * Example:
     * #EXT-X-CUE-OUT:30.000
    </pre> *
     */
    const val EXTXCUEOUT = "EXT-X-CUE-OUT"
    /**
     * Cue in tag.
     *
     * <pre>
     * Format:
     * #EXT-X-CUE-IN
     *
     * Example:
     * #EXT-X-CUE-IN
    </pre> *
     */
    const val EXTXCUEIN = "EXT-X-CUE-IN"
    /**
     * Asset tag.
     *
     * <pre>
     * Format:
     * #EXT-X-ASSET:&lt;attribute-list&gt;
     *
     * Example:
     * #EXT-X-ASSET:CAID=0x0000000020FB6501
    </pre> *
     */
    const val EXTXASSET = "EXT-X-ASSET"
    /**
     * Cue out cont tag.
     *
     * <pre>
     * Format:
     * #EXT-X-CUE-OUT-CONT:&lt;attribute-list&gt;
     *
     * Example:
     * #EXT-X-CUE-OUT-CONT:ElapsedTime=5.939,Duration=201.467,SCTE35=/DA0AAAA+…AAg+2UBNAAANvrtoQ==
    </pre> *
     */
    const val EXTXCUEOUTCONT = "EXT-X-CUE-OUT-CONT"
    /**
     * Map tag.
     *
     * <pre>
     * Format:
     * #EXT-X-MAP:&lt;attribute-list&gt;
     *
     * Example:
     * #EXT-X-MAP:URI="main.mp4",BYTERANGE="560@0"
    </pre> *
     */
    const val EXTXMAP = "EXT-X-MAP"
    /**
     * Endlist tag.
     *
     * (No endlist class is required)
     *
     * <pre>
     * Format:
     * #EXT-X-ENDLIST;
     *
     * Example:
     * #EXT-X-ENDLIST
    </pre> *
     */
    const val EXTXENDLIST = "EXT-X-ENDLIST"

    /**
     * The EXTM3U tag indicates that the file is an Extended M3U [M3U]
     * Playlist file.  It MUST be the first line of every Media Playlist and
     * every Master Playlist.  Its format is:
     *
     * #EXTM3U
     */
    const val EXTM3U = "EXTM3U"

    /**
     * The EXT-X-INDEPENDENT-SEGMENTS tag indicates that all media samples
     * in a Media Segment can be decoded without information from other
     * segments.  It applies to every Media Segment in the Playlist.
     *
     * Its format is:
     *
     * #EXT-X-INDEPENDENT-SEGMENTS
     *
     * If the EXT-X-INDEPENDENT-SEGMENTS tag appears in a Master Playlist,
     * it applies to every Media Segment in every Media Playlist in the
     * Master Playlist.
     */
    const val EXTXINDEPENDENTSEGMENTS = "EXT-X-INDEPENDENT-SEGMENTS"

    /**
     * The EXT-X-GAP tag indicates that the segment URL to which it applies
     * does not contain media data and SHOULD NOT be loaded by clients.  It
     * applies only to the next Media Segment.
     *
     * Its format is:
     *
     * #EXT-X-GAP
     */
    const val EXTXGAP = "EXT-X-GAP"

    init {
        TagFactory.registerTag(EXTM3U, Version::class)
        TagFactory.registerTag(EXTXGAP, Version::class)
        TagFactory.registerTag(EXTXINDEPENDENTSEGMENTS, Version::class)
        TagFactory.registerTag(EXTXVERSION, Version::class)
        TagFactory.registerTag(EXTXSTREAMINF, StreamInf::class)
        TagFactory.registerTag(EXTXMEDIA, Media::class)
        TagFactory.registerTag(EXTINF, ExtInf::class)
        TagFactory.registerTag(EXTXBYTERANGE, ByteRange::class)
        TagFactory.registerTag(EXTXTARGETDURATION, TargetDuration::class)
        TagFactory.registerTag(EXTXMEDIASEQUENCE, MediaSequence::class)
        TagFactory.registerTag(EXTXDISCONTINUITYSEQUENCE, DiscontinuitySequence::class)
        TagFactory.registerTag(EXTXKEY, Key::class)
        TagFactory.registerTag(EXTXALLOWCACHE, AllowCache::class)
        TagFactory.registerTag(EXTXPLAYLISTTYPE, PlaylistType::class)
        TagFactory.registerTag(EXTXIFRAMESONLY, IFramesOnly::class)
        TagFactory.registerTag(EXTXIFRAMESSTREAMINF, IFrameStreamInf::class)
        TagFactory.registerTag(EXTXSCTE35, SCTE35::class)
        TagFactory.registerTag(EXTXCUE, Cue::class)
        TagFactory.registerTag(EXTXCUEOUT, CueOut::class)
        TagFactory.registerTag(EXTXASSET, Asset::class)
        TagFactory.registerTag(EXTXCUEOUTCONT, CueOutCont::class)
        TagFactory.registerTag(EXTXMAP, SegmentMap::class)
    }
}
