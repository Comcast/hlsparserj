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
package com.realeyes.hlsparserj

import com.realeyes.hlsparserj.tags.UnparsedTag
import com.realeyes.hlsparserj.tags.Version

/**
 * Serves as the interface of a playlist object.  There are
 * two kinds of playlists that are supported:
 *
 *
 *  * master playlist: if URI lines in playlist identify media playlists
 *  * media playlist: if URI lines in playlist identify media segments
 *
 */
interface IPlaylist {
    /**
     * Returns the version of the playlist.
     * @return version
     */
    fun getVersion(): Version?

    /**
     * Returns true if this is a master playlist; false if a media playlist.
     * @return boolean
     */
    val isMasterPlaylist: Boolean

    /**
     * Returns list of tags in this playlist.
     * @return list of tags
     */
    val tags: List<UnparsedTag>
}
