/**
 * Copyright 2015 Comcast Cable Communications Management, LLC
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.comcast.viper.hlsparserj.tags.master;

/**
 * <pre>
 * Represents a I-Frame stream tag.
 *
 * The EXT-X-I-FRAME-STREAM-INF tag identifies a Media Playlist file
 * containing the I-frames of a multimedia presentation.  It stands
 * alone, in that it does not apply to a particular URI in the Master
 * Playlist
 *
 * Its format is:
 *
 *   #EXT-X-I-FRAME-STREAM-INF:&lt;attribute-list&gt;
 *
 * All attributes defined for the EXT-X-STREAM-INF tag (Section 3.4.10)
 * are also defined for the EXT-X-I-FRAME-STREAM-INF tag, except for the
 * AUDIO, SUBTITLES and CLOSED-CAPTIONS attributes.  In addition, the
 * following attribute is defined:
 *
 * URI
 * </pre>
 */
public class IFrameStreamInf extends StreamInf {

    private static final String URI = "URI";

    @Override
    public String getAudio() {
        throw new UnsupportedOperationException("AUDIO is not supported");
    }

    @Override
    public String getVideo() {
        throw new UnsupportedOperationException("VIDEO is not supported");
    }

    @Override
    public String getSubtitle() {
        throw new UnsupportedOperationException("SUBTITLE is not supported");
    }

    @Override
    public String getClosedCaptions() {
        throw new UnsupportedOperationException("CLOSEDCAPTIONS is not supported");
    }

    @Override
    public String getURI() {
        return tag.getAttributes().get(URI);
    }
}
