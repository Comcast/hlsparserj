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
package com.comcast.viper.hlsparserj.tags.media;

/**
 * <pre>
 * Represents a byte range stream tag.
 *
 * The EXT-X-BYTERANGE tag indicates that a media segment is a sub-range
 * of the resource identified by its media URI.  It applies only to the
 * next media URI that follows it in the Playlist.  Its format is:
 *
 *   #EXT-X-BYTERANGE:&lt;n&gt;[@&lt;o&gt;]
 *
 * where n is a decimal-integer indicating the length of the sub-range
 * in bytes.  If present, o is a decimal-integer indicating the start of
 * the sub-range, as a byte offset from the beginning of the resource.
 * If o is not present, the sub-range begins at the next byte following
 * the sub-range of the previous media segment.
 *
 * If o is not present, a previous media segment MUST appear in the
 * Playlist file and MUST be a sub-range of the same media resource.
 *
 * A media URI with no EXT-X-BYTERANGE tag applied to it specifies a
 * media segment that consists of the entire resource.
 *
 * The EXT-X-BYTERANGE tag appeared in version 4 of the protocol.  It
 * MUST NOT appear in a Master Playlist.
 * </pre>
 */
public class ByteRange extends Segment {

    @Override
    public String getURI() {
        return tag.getURI();
    }

    /**
     * Returns the length attribute.
     * @return length attribute
     */
    public int getLength() {
        final String line = tag.getAttributes().get(UNNAMEDATTR0);
        return Integer.valueOf(line.split("@")[0]);
    }

    /**
     * Returns the offset attribute.
     * @return offset attribute
     */
    public int getOffset() {
        final String line = tag.getAttributes().get(UNNAMEDATTR0);
        final String[] splitLine = line.split("@");
        if (splitLine.length > 1) {
            return Integer.valueOf(splitLine[1]);
        } else {
            return 0;
        }
    }
}
