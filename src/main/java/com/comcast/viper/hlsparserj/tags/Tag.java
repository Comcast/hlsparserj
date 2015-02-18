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

/**
 * Abstract class for a tag.
 */
public abstract class Tag {

    /**
     * Wrapped tag object.
     */
    protected UnparsedTag tag;

    /**
     * Unnamed attribute 0.
     */
    protected static final String UNNAMEDATTR0 = "NONAME0";

    // TODO WHAT IS THIS
    /**
     * Unnamed attribute 1.
     */
    protected static final String UNNAMEDATTR1 = "NONAME1";

    /**
     * Sets the wrapped tag object.
     * @param tag wrapped tag
     */
    public void setTag(final UnparsedTag tag) {
        this.tag = tag;
    }

    /**
     * Returns boolean equivalent of a playlist boolean string (true if 'yes', and false if 'no').
     * @param bool boolean string ('yes' or 'no')
     * @return boolean
     */
    public boolean yesNoBoolean(final String bool) {
        return "yes".equalsIgnoreCase(bool);
    }

    /**
     * Returns the wrapped tag object.
     * @return wrapped tag
     */
    public UnparsedTag getTag() {
        return tag;
    }
}
