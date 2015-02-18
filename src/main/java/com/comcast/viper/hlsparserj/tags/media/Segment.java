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
package com.comcast.viper.hlsparserj.tags.media;

import com.comcast.viper.hlsparserj.tags.Tag;

/**
 * Abstract class to represent a segment tag.
 */
public abstract class Segment extends Tag {

    private boolean discontinuity = false;
    private Key key;
    private String dateTime;

    /**
     * Returns the URI tag.
     * @return URI
     */
    public String getURI() {
        return tag.getURI();
    }

    /**
     * Returns the discontinuity flag.
     * @return discontinuity flag
     */
    public boolean getDiscontinuity() {
        return this.discontinuity;
    }

    /**
     * Sets the discontinuity flag.
     * @param discontinuity discontinuity flag
     */
    public void setDiscontinuity(final boolean discontinuity) {
        this.discontinuity = discontinuity;
    }

    /**
     * Returns the key.
     * @return key
     */
    public Key getKey() {
        return key;
    }

    /**
     * Sets the key.
     * @param key key
     */
    public void setKey(final Key key) {
        this.key = key;
    }

    /**
     * Returns the date and time string.
     * @return date and time string
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * Sets the date and time string.
     * @param dateTime date and time string
     */
    public void setDateTime(final String dateTime) {
        this.dateTime = dateTime;
    }
}
