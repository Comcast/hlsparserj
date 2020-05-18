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
package com.realeyes.hlsparserj.v12

import com.realeyes.hlsparserj.MasterPlaylist
import com.realeyes.hlsparserj.PlaylistVersion
import com.realeyes.hlsparserj.tags.UnparsedTag

/**
 * Concrete class implementation for a V12 master playlist.
 */
class MasterPlaylistV12(tags: MutableList<UnparsedTag>) : MasterPlaylist(PlaylistVersion.TWELVE, tags)
