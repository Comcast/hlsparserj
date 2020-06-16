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

/**
 * Abstract class to represent a segment tag.
 */
abstract class Segment : Tag {
    override var headerTag = false
    var cueIn = false
    var caid: String? = null
    var breakDuration: Float? = null
    var breakElapsed: Float? = null
    var targetDuration: Float? = null
    var mediaSequence: Int? = null
    var discontinuitySequence: Int? = null
    var endList = false
    var map: String? = null
    var scte35: SCTE35? = null
    var breakPts: Float? = null
    var breakId: Int? = null
    var pdtUpdated: Boolean = false

    /**
     * Returns the discontinuity flag.
     * @return discontinuity flag
     */
    /**
     * Sets the discontinuity flag.
     * @param discontinuity discontinuity flag
     */
    var discontinuity = false
    /**
     * Returns the key.
     * @return key
     */
    /**
     * Sets the key.
     * @param key key
     */
    var keys: List<Key?>? = null

    /**
     * Returns the date and time string.
     * @return date and time string
     */
    /**
     * Sets the date and time string.
     * @param dateTime date and time string
     */
    var dateTime: String? = null

    /**
     * The absolute time of segment in ms.
     * @return timestamp long for the segment
     */
    /**
     * Sets the absolute time in ms.
     * @param pdt absolute time Long
     */
    var pdt: Long? = null

    /**
     * The relative time of segment in ms.
     * @return timestamp long for the segment
     */
    /**
     * Sets the relative time in ms.
     * @param startTime relative time Long
     */
    var startTime: Float? = null

    /**
     * Returns all tags associated with the segment
     * @return list of Tags
     */
    /**
     * Sets tags on generic segment
     * @param tags List of tag objects
     */
    var tags: MutableList<Tag>? = mutableListOf()
    /**
     * Returns the URI tag.
     * @return URI
     */
    open val uri: String
        get() = tag?.uri ?: ""
}
