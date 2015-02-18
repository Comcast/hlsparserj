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
 * Represents a variant stream tag.
 *
 * The EXTINF tag specifies the duration of a media segment.  It applies
 * only to the media segment that follows it, and MUST be followed by a
 * media segment URI.  Each media segment MUST be preceded by an EXTINF
 * tag.  Its format is:
 *
 *   #EXTINF:&lt;duration&gt;,&lt;title&gt;
 *
 * where duration is an decimal-integer or decimal-floating-point number
 * that specifies the duration of the media segment in seconds.
 * Durations that are reported as integers SHOULD be rounded to the
 * nearest integer.  Durations MUST be integers if the protocol version
 * of the Playlist file is less than 3.  Durations SHOULD be floating-
 * point if the version is equal to or greater than 3.  The remainder of
 * the line following the comma is an optional human-readable
 * informative title of the media segment.
 * </pre>
 */
public class ExtInf extends Segment {

    /**
     * Returns the duration of this media segment.
     * @return duration
     */
    public float getDuration() {
        return Float.valueOf(tag.getAttributes().get(UNNAMEDATTR0));
    }

    /**
     * Returns the optional 'human-readable informative title' of this media segment.
     * @return title
     */
    public String getTitle() {
        return tag.getAttributes().get(UNNAMEDATTR1);
    }
}
