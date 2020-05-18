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

import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

/**
 * Provides factory methods to create tags.  Registration of the tags
 * are required for the factory to be aware of them.
 */
object TagFactory {
    private val tagMap = ConcurrentHashMap<String, KClass<*>>()
    /**
     * Registers a tag.
     * @param tagName tag name
     * @param tagClass tag class
     */
    fun registerTag(tagName: String, tagClass: KClass<*>) {
        tagMap[tagName] = tagClass
    }

    /**
     * Creates a tag given the name.
     * @param tagName tag name
     * @return tag
     */
    fun createTag(tagName: String?, tag: UnparsedTag): Tag? {
        var tagInstance: Tag? = null
        val tagClass = tagMap[tagName]
        if (tagClass != null) {
            try {
                tagInstance = tagClass.constructors.first().call(tag) as Tag?
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return if (tagInstance is Tag) {
            tagInstance
        } else {
            null
        }
    }
}
