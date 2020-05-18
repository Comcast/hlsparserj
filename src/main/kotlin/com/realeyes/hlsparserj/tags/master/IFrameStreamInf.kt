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

import com.realeyes.hlsparserj.tags.UnparsedTag

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
 * #EXT-X-I-FRAME-STREAM-INF:&lt;attribute-list&gt;
 *
 * All attributes defined for the EXT-X-STREAM-INF tag (Section 3.4.10)
 * are also defined for the EXT-X-I-FRAME-STREAM-INF tag, except for the
 * AUDIO, SUBTITLES and CLOSED-CAPTIONS attributes.  In addition, the
 * following attribute is defined:
 *
 * URI
</pre> *
 */
class IFrameStreamInf(tag: UnparsedTag) : StreamInf(tag) {
    override var headerTag = true

    override val audio: String
        get() {
            throw UnsupportedOperationException("AUDIO is not supported")
        }

    override val video: String
        get() {
            throw UnsupportedOperationException("VIDEO is not supported")
        }

    override val subtitle: String
        get() {
            throw UnsupportedOperationException("SUBTITLE is not supported")
        }

    override val closedCaptions: String
        get() {
            throw UnsupportedOperationException("CLOSEDCAPTIONS is not supported")
        }

    override val uri: String
        get() = tag?.getAttributes()?.get(URI).toString()

    companion object {
        private const val URI = "URI"
    }
}
