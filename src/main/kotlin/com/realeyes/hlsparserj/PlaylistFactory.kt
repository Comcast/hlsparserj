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

import com.realeyes.hlsparserj.v12.MasterPlaylistV12
import com.realeyes.hlsparserj.v12.MediaPlaylistV12
import java.io.IOException
import java.io.InputStream

/**
 * Provides factory methods to generate and parse playlists.
 */
object PlaylistFactory {
    /**
     * Factory method to generate a playlist object. This method performs no
     * HTTP actions. It uses the playlistStream parameter as the playlist.
     *
     * @param playlistVersion version of the playlist (V12 is the default)
     * @param playlistStream inputStream containing a correctly formatted playlist
     * @return parsed playlist
     * @throws IOException on parsing exception
     */
    @Throws(IOException::class)
    fun parsePlaylist(
        playlistVersion: PlaylistVersion,
        playlistStream: InputStream?
    ): AbstractPlaylist? {
        val parser = PlaylistParser()
        parser.parse(playlistStream)
        return getVersionSpecificPlaylist(parser, playlistVersion)
    }

    /**
     * Factory method to generate playlist object.  This method performs no
     * HTTP actions. It uses the playlistString parameter as the playlist.
     *
     * @param playlistVersion version of the playlist (V12 is the default)
     * @param playlistString string containing a correctly formatted playlist
     * @return parsed playlist
     */
    fun parsePlaylist(
        playlistVersion: PlaylistVersion,
        playlistString: String?
    ): AbstractPlaylist? {
        val parser = PlaylistParser()
        parser.parse(playlistString!!)
        return getVersionSpecificPlaylist(parser, playlistVersion)
    }

    /**
     * Returns a playlist that represents a specific object version of the playlist.
     * Currently, only V12 is supported.
     * @param parser playlist parser
     * @param playlistVersion preferred playlist version
     * @return playlist
     */
    private fun getVersionSpecificPlaylist(
        parser: PlaylistParser,
        playlistVersion: PlaylistVersion
    ): AbstractPlaylist? {
        var playlist: AbstractPlaylist?
        when (playlistVersion) {
            PlaylistVersion.TWELVE, PlaylistVersion.DEFAULT -> if (parser.isMasterPlaylist) {
                playlist = MasterPlaylistV12(parser.getTags())
            } else {
                playlist = MediaPlaylistV12(parser.getTags())
            }
        }
        return playlist
    }

    init {
        try {
            Class.forName("com.realeyes.hlsparserj.tags.TagNames")
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}
