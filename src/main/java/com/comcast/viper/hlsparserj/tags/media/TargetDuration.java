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

import com.comcast.viper.hlsparserj.tags.Tag;

/**
 * <pre>
 * Represents the target duration tag.
 *
 * The EXT-X-TARGETDURATION tag specifies the maximum media segment
 * duration.  The EXTINF duration of each media segment in the Playlist
 * file, when rounded to the nearest integer, MUST be less than or equal
 * to the target duration.  This tag MUST appear once in a Media
 * Playlist file.  It applies to the entire Playlist file.  Its format
 * is:
 *
 *   #EXT-X-TARGETDURATION:&lt;s&gt;
 *
 * where s is a decimal-integer indicating the target duration in
 * seconds.
 *
 * The EXT-X-TARGETDURATION tag MUST NOT appear in a Master Playlist.
 * </pre>
 */
public class TargetDuration extends Tag {

    /**
     * Returns the duration.
     * @return duration
     */
    public int getDuration() {
        return Integer.valueOf(tag.getAttributes().get(UNNAMEDATTR0));
    }
}
