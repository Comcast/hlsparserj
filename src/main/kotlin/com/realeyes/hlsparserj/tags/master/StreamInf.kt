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
package com.realeyes.hlsparserj.tags.master

import com.realeyes.hlsparserj.tags.Tag
import com.realeyes.hlsparserj.tags.UnparsedTag

/**
 * <pre>
 * Represents a variant stream tag.
 *
 * The EXT-X-STREAM-INF tag specifies a variant stream, which is a set
 * of renditions which can be combined to play the presentation.  The
 * attributes of the tag provide information about the variant stream.
 *
 * The EXT-X-STREAM-INF tag identifies the next URI line in the Playlist
 * as a rendition of the variant stream.
 *
 * The EXT-X-STREAM-INF tag MUST NOT appear in a Media Playlist.
 *
 * Its format is:
 *
 * #EXT-X-STREAM-INF:&lt;attribute-list&gt;
 * &lt;URI&gt;
 *
 * The following attributes are defined:
 *
 * BANDWIDTH
 *
 * The open value is a decimal-integer of bits per second.  It MUST be an
 * upper bound of the overall bitrate of each media segment (calculated
 * to include container overhead) that appears or will appear in the
 * Playlist.
 *
 * Every EXT-X-STREAM-INF tag MUST include the BANDWIDTH attribute.
 *
 * CODECS
 *
 * The open value is a quoted-string containing a comma-separated list of
 * formats, where each format specifies a media sample type that is
 * present in a media segment in the Media Playlist file.  open valid format
 * identifiers are those in the ISO Base Media File Format Name Space
 * defined by RFC 6381 [RFC6381].
 *
 * Every EXT-X-STREAM-INF tag SHOULD include a CODECS attribute.
 *
 * RESOLUTION
 *
 * The open value is a decimal-resolution describing the approximate encoded
 * horizontal and vertical resolution of video within the presentation.
 *
 * The RESOLUTION attribute is OPTIONAL but is recommended if the
 * variant stream includes video.
 *
 * AUDIO
 *
 * The open value is a quoted-string.  It MUST match the open value of the
 * GROUP-ID attribute of an EXT-X-MEDIA tag elsewhere in the Master
 * Playlist whose TYPE attribute is AUDIO.  It indicates the set of
 * audio renditions that MAY be used when playing the presentation.  See
 * Section 3.4.10.1.
 *
 * The AUDIO attribute is OPTIONAL.
 *
 * VIDEO
 *
 * The open value is a quoted-string.  It MUST match the open value of the
 * GROUP-ID attribute of an EXT-X-MEDIA tag elsewhere in the Master
 * Playlist whose TYPE attribute is VIDEO.  It indicates the set of
 * video renditions that MAY be used when playing the presentation.  See
 * Section 3.4.10.1.
 *
 * The VIDEO attribute is OPTIONAL.
 *
 * SUBTITLES
 *
 * The open value is a quoted-string.  It MUST match the open value of the
 * GROUP-ID attribute of an EXT-X-MEDIA tag elsewhere in the Master
 * Playlist whose TYPE attribute is SUBTITLES.  It indicates the set of
 * subtitle renditions that MAY be used when playing the presentation.
 * See Section 3.4.10.1.
 *
 * The SUBTITLES attribute is OPTIONAL.
 *
 * CLOSED-CAPTIONS
 *
 * The open value can be either a quoted-string or an enumerated-string with
 * the open value NONE.  If the open value is a quoted-string, it MUST match the
 * open value of the GROUP-ID attribute of an EXT-X-MEDIA tag elsewhere in
 * the Playlist whose TYPE attribute is CLOSED-CAPTIONS, and indicates
 * the set of closed-caption renditions that may be used when playlist
 * the presentation.  See Section 3.4.10.1.
 *
 * If the open value is the enumerated-string open value NONE, all EXT-X-STREAM-
 * INF tags MUST have this attribute with a open value of NONE.  This
 * indicates that there are no closed captions in any variant stream in
 * the Master Playlist.
 *
 * The CLOSED-CAPTIONS attribute is OPTIONAL.
</pre> *
 */
open class StreamInf(override var tag: UnparsedTag?) : Tag {
    override var headerTag = true

    /**
     * Returns the program ID.
     * @return program ID
     */
    open val programId: Int
        get() = Integer.valueOf(tag?.getAttributes()?.get(PROGRAMID))

    /**
     * Returns the bandwidth attribute.
     * @return bandwidth attribute
     */
    open val bandwidth: Int
        get() = Integer.valueOf(tag?.getAttributes()?.get(BANDWIDTH))

    /**
     * Returns the codec attribute.
     * @return codec attribute
     */
    open val codecs: String
        get() = tag?.getAttributes()?.get(CODECS).toString()

    /**
     * Returns the resolution attribute.
     * @return resolution attribute
     */
    open val resolution: String
        get() = tag?.getAttributes()?.get(RESOLUTION).toString()

    /**
     * Returns the audio attribute.
     * @return audio attribute
     */
    open val audio: String
        get() = tag?.getAttributes()?.get(AUDIO).toString()

    /**
     * Returns the video attribute.
     * @return video attribute
     */
    open val video: String
        get() = tag?.getAttributes()?.get(VIDEO).toString()

    /**
     * Returns the subtitle attribute.
     * @return subtitle attribute
     */
    open val subtitle: String
        get() = tag?.getAttributes()?.get(SUBTITLE).toString()

    /**
     * Returns the closed captions attribute.
     * @return closed captions attribute
     */
    open val closedCaptions: String
        get() = tag?.getAttributes()?.get(CLOSEDCAPTIONS).toString()

    /**
     * Returns the URI for this variant stream.
     * @return URI
     */
    open val uri: String
        get() = tag?.uri.toString()

    companion object {
        private const val PROGRAMID = "PROGRAM-ID"
        private const val BANDWIDTH = "BANDWIDTH"
        private const val CODECS = "CODECS"
        private const val RESOLUTION = "RESOLUTION"
        private const val AUDIO = "AUDIO"
        private const val VIDEO = "VIDEO"
        private const val SUBTITLE = "SUBTITLES"
        private const val CLOSEDCAPTIONS = "CLOSEDCAPTIONS"
    }
}
