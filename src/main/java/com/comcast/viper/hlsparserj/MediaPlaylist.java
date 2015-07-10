/**
 * Copyright 2015 Comcast Cable Communications Management, LLC
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
package com.comcast.viper.hlsparserj;

import java.util.ArrayList;
import java.util.List;

import com.comcast.viper.hlsparserj.tags.Tag;
import com.comcast.viper.hlsparserj.tags.TagFactory;
import com.comcast.viper.hlsparserj.tags.TagNames;
import com.comcast.viper.hlsparserj.tags.UnparsedTag;
import com.comcast.viper.hlsparserj.tags.media.AllowCache;
import com.comcast.viper.hlsparserj.tags.media.ByteRange;
import com.comcast.viper.hlsparserj.tags.media.ExtInf;
import com.comcast.viper.hlsparserj.tags.media.Key;
import com.comcast.viper.hlsparserj.tags.media.MediaSequence;
import com.comcast.viper.hlsparserj.tags.media.PlaylistType;
import com.comcast.viper.hlsparserj.tags.media.Segment;
import com.comcast.viper.hlsparserj.tags.media.TargetDuration;

/**
 * Abstract class for a media playlist.
 */
public abstract class MediaPlaylist extends AbstractPlaylist {

    /**
     * Constructor.
     * @param version version
     * @param tags list of tags
     */
    public MediaPlaylist(final PlaylistVersion version, final List<UnparsedTag> tags) {
        super(version, tags);
    }

    /**
     * Return list of media segments tags in this media playlist.
     *
     * Tag key: EXTINF.
     *
     * @return list of media segments tags
     */
    @SuppressWarnings("unchecked")
    public List<ExtInf> getSegments() {
        return (List<ExtInf>) getGenericSegments(TagNames.EXTINF);
    }

    /**
     * Return list of byte ranges tags in the media playlist.
     *
     * Tag key: EXT-X-BYTERANGE.
     *
     * @return list of byte ranges tags
     */
    @SuppressWarnings("unchecked")
    public List<ByteRange> getByteRanges() {
        return (List<ByteRange>) getGenericSegments(TagNames.EXTXBYTERANGE);
    }

    /**
     * Returns the target duration tag.  Value specifies the maximum media segment duration.
     *
     * Tag key: EXT-X-TARGETDURATION.
     *
     * @return target duration tag
     */
    public TargetDuration getTargetDuration() {
        return (TargetDuration) getTag(TagNames.EXTXTARGETDURATION);
    }

    /**
     * Return the media sequence tag in the playlist.  Sequence number represents the
     * sequence number of the first segment that appeared in the playlist file.
     *
     * Tag key: EXT-X-MEDIA-SEQUENCE.
     *
     * @return media sequence
     */
    public MediaSequence getMediaSequence() {
        return (MediaSequence) getTag(TagNames.EXTXMEDIASEQUENCE);
    }

    /**
     * Return the allow cache tag in the playlist.  Indicates whether the client MAY
     * or MUST NOT cache downloaded media segment for later replay.  It MAY occur
     * anywhere in a Media Playlist file; it MUST NOT occur more than once.
     *
     * Tag key: EXT-X-ALLOW-CACHE.
     *
     * @return allow cache tag
     */
    public AllowCache getAllowCache() {
        return (AllowCache) getTag(TagNames.EXTXALLOWCACHE);
    }

    /**
     * Return the playlist type tag in the playlist.  Provides mutability information
     * about the playlist file.  It applies to the entire Playlist file.  It is
     * OPTIONAL.  Its format is:
     *
     *   #EXT-X-PLAYLIST-TYPE:&lt;EVENT|VOD&gt;
     *
     * Tag key: EXT-X-PLAYLIST-TYPE.
     *
     * @return playlist type
     */
    public PlaylistType getPlaylistType() {
        return (PlaylistType) getTag(TagNames.EXTXPLAYLISTTYPE);
    }

    @Override
    public boolean isMasterPlaylist() {
        return false;
    }

    /**
     * Returns list of 'generic' media segments found in this playlist.  Function is
     * aware of common tags in the playlists that affects the segments (discontinuity,
     * key, and dateTime).
     * @param segmentTagName segment tag name
     * @return list of tags
     */
    private List<? extends Tag> getGenericSegments(final String segmentTagName) {
        List<Tag> tagList = parsedTagListCache.get(segmentTagName);
        if (tagList == null) {
            tagList = new ArrayList<Tag>();
            boolean discontinuity = false;
            String programDateTime = null;
            Key key = null;

            for (UnparsedTag unparsedTag : tags) {
                final String tagName = unparsedTag.getTagName();
                if (tagName.equals(TagNames.EXTXDISCONTINUITY)) {
                    discontinuity = true;
                } else if (tagName.equals(TagNames.EXTXPROGRAMDATETIME)) {
                    programDateTime = unparsedTag.getAttributes().get("NONAME0");
                } else if (tagName.equals(TagNames.EXTXKEY)) {
                    key = new Key();
                    key.setTag(unparsedTag);
                } else if (unparsedTag.getTagName().equals(segmentTagName)) {
                    final Segment segment = (Segment) TagFactory.createTag(segmentTagName);
                    segment.setTag(unparsedTag);
                    segment.setDiscontinuity(discontinuity);
                    segment.setKey(key);
                    segment.setDateTime(programDateTime);
                    tagList.add(segment);
                    discontinuity = false;
                    programDateTime = null;
                }
            }

            parsedTagListCache.put(segmentTagName, tagList);
        }
        return tagList;
    }

    /**
     * Return boolean to indicate if this playlist consists of I-Frames only.
     * @return boolean flag
     */
    public boolean getIFramesOnly() {
        final Object ifo = getTag(TagNames.EXTXIFRAMESONLY);
        if (ifo == null) {
            return false;
        }
        return true;
    }
}
