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

import com.realeyes.hlsparserj.tags.Tag.Companion.UNNAMEDATTR0

/**
 * <pre>
 * Represents the version tag in the playlist.
 *
 * The EXT-X-VERSION tag indicates the compatibility version of the
 * Playlist file.  The Playlist file, its associated media, and its
 * server MUST comply with all provisions of the most-recent version of
 * this document describing the protocol version indicated by the tag
 * value.
 *
 * The EXT-X-VERSION tag applies to the entire Playlist file.  Its
 * format is:
 *
 * #EXT-X-VERSION:&lt;n&gt;
 *
 * where n is an integer indicating the protocol version.
 *
 * A Playlist file MUST NOT contain more than one EXT-X-VERSION tag.  A
 * Playlist file that does not contain an EXT-X-VERSION tag MUST comply
 * with version 1 of this protocol.
 *
 * The EXT-X-VERSION tag MAY appear in either Master Playlist or Media
 * Playlist.  It MUST appear in all playlists containing tags or
 * attributes that are not compatible with protocol version 1.
</pre> *
 */
class Version(override var tag: UnparsedTag?) : Tag {
    override var headerTag = true
    /**
     * Returns the playlist version.
     * @return version
     */
    val version: Int
        get() = Integer.valueOf(tag?.getAttributes()?.get(UNNAMEDATTR0))
}
