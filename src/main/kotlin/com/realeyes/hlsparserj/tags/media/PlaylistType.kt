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
package com.realeyes.hlsparserj.tags.media

import com.realeyes.hlsparserj.tags.Tag
import com.realeyes.hlsparserj.tags.Tag.Companion.UNNAMEDATTR0
import com.realeyes.hlsparserj.tags.UnparsedTag

/**
 * <pre>
 * Represents a playlist type tag.
 *
 * The EXT-X-PLAYLIST-TYPE tag provides mutability information about the
 * Playlist file.  It applies to the entire Playlist file.  It is
 * OPTIONAL.  Its format is:
 *
 * #EXT-X-PLAYLIST-TYPE:&lt;EVENT|VOD&gt;
 *
 * Section 6.2.1 defines the implications of the EXT-X-PLAYLIST-TYPE
 * tag.
 *
 * The EXT-X-PLAYLIST-TYPE tag MUST NOT appear in a Master Playlist.
</pre> *
 */
class PlaylistType(override var tag: UnparsedTag?) : Tag {
    override var headerTag = true

    /**
     * Returns the type attribute.
     * @return type attribute
     */
    val type: String
        get() = tag?.getAttributes()?.get(UNNAMEDATTR0) ?: ""
}
