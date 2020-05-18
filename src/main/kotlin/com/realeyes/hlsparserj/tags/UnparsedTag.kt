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

import java.io.IOException
import java.io.StreamTokenizer
import java.io.StringReader
import java.util.regex.Pattern

/**
 * UnparsedTag represents a generic tag in the playlist.
 */
class UnparsedTag(val line: String?) {
    /**
     * Return the tag name.
     * @return name
     */
    /**
     * Sets the tag name.
     * @param tagName tag name
     */
    var tagName: String? = null
    private var attributes: MutableMap<String?, String?>
    /**
     * Returns the URI for this tag.
     * @return URI
     */
    /**
     * Sets the URI for this tag.
     * @param uriString URI
     */
    var uri: String? = null
    /**
     * Returns the raw tag string for the playlist line.
     * @return raw original tag string
     */
    var rawTag: String? = null
        private set

    /**
     * Constructor.
     */
    init {
        attributes = hashMapOf()
        this.line?.let {
            rawTag = it
            parseTagLine(it)
        }
    }

    /**
     * Returns the list of attributes for this tag.
     * @return list of attributes
     */
    fun getAttributes(): Map<String?, String?> {
        return attributes
    }

    /**
     * Sets the list of attributes for this tag.
     * @param attributes list of attributes
     */
    fun setAttributes(attributes: MutableMap<String?, String?>) {
        this.attributes = attributes
    }

    /**
     * Parses the tag line.
     * @param line playlist line item
     */
    private fun parseTagLine(line: String) {
        val lineMatcher = TAGPATTERN.matcher(line)
        // Create a matcher that uses the TAGPATTERN
        if (lineMatcher.find()) {
            tagName = lineMatcher.group(1)
            val attributeList = lineMatcher.group(2)
            val tokenizer = StreamTokenizer(StringReader(attributeList))
            tokenizer.resetSyntax()
            tokenizer.wordChars(' '.toInt(), 255)
            tokenizer.quoteChar('"'.toInt())
            tokenizer.ordinaryChar(','.toInt())
            tokenizer.ordinaryChar('='.toInt())
            var attributeName: String? = null
            var attributeValue: String? = null
            var noNameCount = 0
            do {
                var ttype: Int
                ttype = try {
                    tokenizer.nextToken()
                } catch (e: IOException) { // Should never get here because reading from String
                    throw IllegalStateException(e)
                }
                if (ttype == ','.toInt() || ttype == StreamTokenizer.TT_EOF) {
                    if (attributeValue == null) { // Not actually an attribute - just a single value
                        attributes["NONAME$noNameCount"] = attributeName
                        noNameCount++
                        attributeName = null
                    } else {
                        attributes[attributeName] = attributeValue
                        attributeName = null
                        attributeValue = null
                    }
                } else if (ttype == StreamTokenizer.TT_WORD || ttype == '"'.toInt()) {
                    if (attributeName == null) {
                        attributeName = tokenizer.sval
                    } else {
                        attributeValue = tokenizer.sval
                    }
                }
            } while (tokenizer.ttype != StreamTokenizer.TT_EOF)
            // Set the URI if a URI attribute is present
            if (attributes.containsKey(URI_ATTR)) {
                uri = attributes[URI_ATTR]
            }
        } else { // If the line startex with #EXT but does not contain a colon it is a
// tag with no attributes
            tagName = line.substring(1)
        }
    }

    companion object {
        private val TAGPATTERN = Pattern.compile("^#(EXT.*?):(.*)")
        private const val URI_ATTR = "URI"
    }
}
