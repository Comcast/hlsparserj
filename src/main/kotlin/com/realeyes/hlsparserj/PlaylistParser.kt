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

import com.realeyes.hlsparserj.tags.TagNames
import com.realeyes.hlsparserj.tags.UnparsedTag
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.util.StringTokenizer
import kotlin.collections.ArrayList

/**
 * Class to parse playlists.  Capable to parse playlist string or inputStream.
 */
class PlaylistParser {
    private var playlistStream: InputStream? = null
    private val tags: MutableList<UnparsedTag>
    /**
     * Returns boolean to indicate if the playlist is a master playlist.
     * @return boolean
     */
    var isMasterPlaylist = false
        private set

    /**
     * Parse a given playlist string.
     * @param playlist playlist string
     */
    fun parse(playlist: String) {
        parseString(playlist)
    }

    /**
     * Parse an inputStream of a correctly formatted playlist.
     * @param inputStream inputStream
     * @throws IOException on connection and parsing exception
     */
    @Throws(IOException::class)
    fun parse(inputStream: InputStream?) {
        playlistStream = inputStream
        parseInputStream()
    }

    /**
     * Returns list of unparsed tags.
     * @return list of tags
     */
    fun getTags(): MutableList<UnparsedTag> {
        return tags
    }

    /**
     * Parse a given playlist string.
     * @param playlist playlist string
     */
    private fun parseString(playlist: String) {
        val tokenizer = StringTokenizer(playlist, "\n")
        var line: String
        var lastTag: UnparsedTag? = null
        while (tokenizer.hasMoreElements()) {
            line = tokenizer.nextToken()
            lastTag = processLine(line, lastTag)
        }
    }

    /**
     * Parse a given inputStream to a valid playlist.
     * @throws IOException on reading the inputStream
     */
    @Throws(IOException::class)
    private fun parseInputStream() {
        val isReader = InputStreamReader(playlistStream)
        val bufReader = BufferedReader(isReader)
        var lastTag: UnparsedTag? = null
//        var line: String
        bufReader.forEachLine {
            lastTag = processLine(it, lastTag)
        }
//        while (bufReader.readLine().also { line = it } != null) lastTag = processLine(line, lastTag)
    }

    /**
     * Parse a given string line from the playlist.
     *
     * If the line is prefixed with a "#", it is handled as a new tag and
     * parsed/added to the list of tags.
     *
     * If the line is a URI, it is set as the URI attribute of the last tag.
     *
     * @param line playlist line item
     * @param lastTag last tag
     * @return unparsed tag
     */
    private fun processLine(line: String, lastTag: UnparsedTag?): UnparsedTag? {
        if (line matches TAGPATTERN) {
            val newUnparsedTag = UnparsedTag(line)
            tags.add(newUnparsedTag)
            // Check if this tag specifies a variant stream. If so, this is
// a master playlist
            if (newUnparsedTag.tagName == TagNames.EXTXSTREAMINF) {
                isMasterPlaylist = true
            }
            return newUnparsedTag
        } else if (line matches URIPATTERN && lastTag != null) { // If a line doesn't start with a # it is a URI associated with the last tag
            lastTag.uri = line
            return lastTag
        }
        // Unexpected situation
        return lastTag
    }

    companion object {
        private val TAGPATTERN = "^#EXT.*".toRegex()
        private val URIPATTERN = "^[^#].*".toRegex()
    }

    /**
     * Constructor.
     */
    init {
        tags = ArrayList<UnparsedTag>()
    }
}
