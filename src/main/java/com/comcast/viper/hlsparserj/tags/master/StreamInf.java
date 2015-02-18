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
package com.comcast.viper.hlsparserj.tags.master;

import com.comcast.viper.hlsparserj.tags.Tag;

/**
 * <pre>
 * Represents a variant stream tag.
 *
 * The EXT-X-STREAM-INF tag specifies a variant stream, which is a set
 * of renditions which can be combined to play the presentation.  The
 *  attributes of the tag provide information about the variant stream.
 *
 * The EXT-X-STREAM-INF tag identifies the next URI line in the Playlist
 * as a rendition of the variant stream.
 *
 * The EXT-X-STREAM-INF tag MUST NOT appear in a Media Playlist.
 *
 * Its format is:
 *
 *   #EXT-X-STREAM-INF:&lt;attribute-list&gt;
 *   &lt;URI&gt;
 *
 * The following attributes are defined:
 *
 * BANDWIDTH
 *
 * The value is a decimal-integer of bits per second.  It MUST be an
 * upper bound of the overall bitrate of each media segment (calculated
 * to include container overhead) that appears or will appear in the
 * Playlist.
 *
 * Every EXT-X-STREAM-INF tag MUST include the BANDWIDTH attribute.
 *
 * CODECS
 *
 * The value is a quoted-string containing a comma-separated list of
 * formats, where each format specifies a media sample type that is
 * present in a media segment in the Media Playlist file.  Valid format
 * identifiers are those in the ISO Base Media File Format Name Space
 * defined by RFC 6381 [RFC6381].
 *
 * Every EXT-X-STREAM-INF tag SHOULD include a CODECS attribute.
 *
 * RESOLUTION
 *
 * The value is a decimal-resolution describing the approximate encoded
 * horizontal and vertical resolution of video within the presentation.
 *
 * The RESOLUTION attribute is OPTIONAL but is recommended if the
 * variant stream includes video.
 *
 * AUDIO
 *
 * The value is a quoted-string.  It MUST match the value of the
 * GROUP-ID attribute of an EXT-X-MEDIA tag elsewhere in the Master
 * Playlist whose TYPE attribute is AUDIO.  It indicates the set of
 * audio renditions that MAY be used when playing the presentation.  See
 * Section 3.4.10.1.
 *
 * The AUDIO attribute is OPTIONAL.
 *
 * VIDEO
 *
 * The value is a quoted-string.  It MUST match the value of the
 * GROUP-ID attribute of an EXT-X-MEDIA tag elsewhere in the Master
 * Playlist whose TYPE attribute is VIDEO.  It indicates the set of
 * video renditions that MAY be used when playing the presentation.  See
 * Section 3.4.10.1.
 *
 * The VIDEO attribute is OPTIONAL.
 *
 * SUBTITLES
 *
 * The value is a quoted-string.  It MUST match the value of the
 * GROUP-ID attribute of an EXT-X-MEDIA tag elsewhere in the Master
 * Playlist whose TYPE attribute is SUBTITLES.  It indicates the set of
 * subtitle renditions that MAY be used when playing the presentation.
 * See Section 3.4.10.1.
 *
 * The SUBTITLES attribute is OPTIONAL.
 *
 * CLOSED-CAPTIONS
 *
 * The value can be either a quoted-string or an enumerated-string with
 * the value NONE.  If the value is a quoted-string, it MUST match the
 * value of the GROUP-ID attribute of an EXT-X-MEDIA tag elsewhere in
 * the Playlist whose TYPE attribute is CLOSED-CAPTIONS, and indicates
 * the set of closed-caption renditions that may be used when playlist
 * the presentation.  See Section 3.4.10.1.
 *
 * If the value is the enumerated-string value NONE, all EXT-X-STREAM-
 * INF tags MUST have this attribute with a value of NONE.  This
 * indicates that there are no closed captions in any variant stream in
 * the Master Playlist.
 *
 * The CLOSED-CAPTIONS attribute is OPTIONAL.
 * </pre>
 */
public class StreamInf extends Tag {

    private static final String PROGRAMID = "PROGRAM-ID";
    private static final String BANDWIDTH = "BANDWIDTH";
    private static final String CODECS = "CODECS";
    private static final String RESOLUTION = "RESOLUTION";
    private static final String AUDIO = "AUDIO";
    private static final String VIDEO = "VIDEO";
    private static final String SUBTITLE = "SUBTITLE";
    private static final String CLOSEDCAPTIONS = "CLOSEDCAPTIONS";

    /**
     * Returns the program ID.
     * @return program ID
     */
    public int getProgramId() {
        return Integer.valueOf(tag.getAttributes().get(PROGRAMID));
    }

    /**
     * Returns the bandwidth attribute.
     * @return bandwidth attribute
     */
    public int getBandwidth() {
        return Integer.valueOf(tag.getAttributes().get(BANDWIDTH));
    }

    /**
     * Returns the codec attribute.
     * @return codec attribute
     */
    public String getCodecs() {
        return tag.getAttributes().get(CODECS);
    }

    /**
     * Returns the resolution attribute.
     * @return resolution attribute
     */
    public String getResolution() {
        return tag.getAttributes().get(RESOLUTION);
    }

    /**
     * Returns the audio attribute.
     * @return audio attribute
     */
    public String getAudio() {
        return tag.getAttributes().get(AUDIO);
    }

    /**
     * Returns the video attribute.
     * @return video attribute
     */
    public String getVideo() {
        return tag.getAttributes().get(VIDEO);
    }

    /**
     * Returns the subtitle attribute.
     * @return subtitle attribute
     */
    public String getSubtitle() {
        return tag.getAttributes().get(SUBTITLE);
    }

    /**
     * Returns the closed captions attribute.
     * @return closed captions attribute
     */
    public String getClosedCaptions() {
        return tag.getAttributes().get(CLOSEDCAPTIONS);
    }

    /**
     * Returns the URI for this variant stream.
     * @return URI
     */
    public String getURI() {
        return tag.getURI();
    }
}
