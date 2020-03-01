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
package com.comcast.viper.hlsparserj.tags;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * UnparsedTag represents a generic tag in the playlist.
 */
public class UnparsedTag {

    private static final Pattern TAGPATTERN = Pattern.compile("^#(EXT.*?):(.*)");
    private static final String URI_ATTR = "URI";

    private String tagName;
    private Map<String, String> attributes;
    private String uri;
    private String rawTag;

    /**
     * Constructor.
     */
    public UnparsedTag() {
        attributes = new HashMap<String, String>();
    }

    /**
     * Constructor.
     * @param line playlist line item
     */
    public UnparsedTag(final String line) {
        rawTag = line;
        attributes = new HashMap<String, String>();
        parseTagLine(line);
    }

    /**
     * Return the tag name.
     * @return name
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * Sets the tag name.
     * @param tagName tag name
     */
    public void setTagName(final String tagName) {
        this.tagName = tagName;
    }

    /**
     * Returns the list of attributes for this tag.
     * @return list of attributes
     */
    public Map<String, String> getAttributes() {
        return attributes;
    }

    /**
     * Sets the list of attributes for this tag.
     * @param attributes list of attributes
     */
    public void setAttributes(final Map<String, String> attributes) {
        this.attributes = attributes;
    }

    /**
     * Returns the URI for this tag.
     * @return URI
     */
    public String getURI() {
        return uri;
    }

    /**
     * Sets the URI for this tag.
     * @param uriString URI
     */
    public void setURI(final String uriString) {
        this.uri = uriString;
    }

    /**
     * Returns the raw tag string for the playlist line.
     * @return raw original tag string
     */
    public String getRawTag() {
        return rawTag;
    }

    /**
     * Parses the tag line.
     * @param line playlist line item
     */
    private void parseTagLine(final String line) {
        final Matcher lineMatcher = TAGPATTERN.matcher(line);

        // Create a matcher that uses the TAGPATTERN
        if (lineMatcher.find()) {
            tagName = lineMatcher.group(1);

            final String attributeList = lineMatcher.group(2);

            final StreamTokenizer tokenizer = new StreamTokenizer(new StringReader(attributeList));
            tokenizer.resetSyntax();
            tokenizer.wordChars(' ', 255);
            tokenizer.quoteChar('"');
            tokenizer.ordinaryChar(',');
            tokenizer.ordinaryChar('=');

            String attributeName = null;
            String attributeValue = null;
            int noNameCount = 0;
            do {
                int ttype;
                try {
                    ttype = tokenizer.nextToken();
                } catch (IOException e) {
                    // Should never get here because reading from String
                    throw new IllegalStateException(e);
                }

                if (ttype == ',' || ttype == StreamTokenizer.TT_EOF) {
                    if (attributeValue == null) {
                        // Not actually an attribute - just a single value
                        attributes.put("NONAME" + noNameCount, attributeName);
                        noNameCount++;
                        attributeName = null;
                    } else {
                        attributes.put(attributeName, attributeValue);
                        attributeName = null;
                        attributeValue = null;
                    }
                } else if (ttype == StreamTokenizer.TT_WORD || ttype == '"') {
                    if (attributeName == null) {
                        attributeName = tokenizer.sval;
                    } else {
                        attributeValue = tokenizer.sval;
                    }
                }
            } while (tokenizer.ttype != StreamTokenizer.TT_EOF);

            // Set the URI if a URI attribute is present
            if (attributes.containsKey(URI_ATTR)) {
                uri = attributes.get(URI_ATTR);
            }
        } else {
            // If the line startex with #EXT but does not contain a colon it is a
            // tag with no attributes
            tagName = line.substring(1);
        }
    }
}
