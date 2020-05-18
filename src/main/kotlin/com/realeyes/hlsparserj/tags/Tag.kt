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

/**
 * Abstract class for a tag.
 */
interface Tag {
    var headerTag: Boolean
    /**
     * Wrapped tag object.
     */
    var tag: UnparsedTag?

    /**
     * Returns boolean equivalent of a playlist boolean string (true if 'yes', and false if 'no').
     * @param bool boolean string ('yes' or 'no')
     * @return boolean
     */
    fun yesNoBoolean(bool: String?): Boolean {
        return "yes".equals(bool, ignoreCase = true)
    }

    companion object {
        /**
         * Unnamed attribute 0.
         */
        const val UNNAMEDATTR0 = "NONAME0"
        // TODO WHAT IS THIS
        /**
         * Unnamed attribute 1.
         */
        const val UNNAMEDATTR1 = "NONAME1"
    }
}
