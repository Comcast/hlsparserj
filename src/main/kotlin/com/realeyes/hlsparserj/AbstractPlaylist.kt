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

import com.realeyes.hlsparserj.tags.Tag
import com.realeyes.hlsparserj.tags.TagFactory
import com.realeyes.hlsparserj.tags.TagNames
import com.realeyes.hlsparserj.tags.UnparsedTag
import com.realeyes.hlsparserj.tags.Version
import java.util.concurrent.ConcurrentHashMap

/**
 * Base class for the playlist object.
 *
 * Initially, all the unparsed tags of the playlist are kept in a list.
 * As they are requested for, and are parsed, they are put in a cached
 * container.
 *
 * If a tag entry has multiple entries (ex. #EXTINF), once it's parsed,
 * it is cached in parsedTagListCache.
 *
 * If a tag entry is single valued (ex. #EXT-X-VERSION), once it's parsed,
 * it is cached in parsedTagCache.
 */
abstract class AbstractPlaylist(
    /**
     * Version tag of the playlist.
     */
    val playlistVersion: PlaylistVersion,
    override val tags: MutableList<UnparsedTag>
) : IPlaylist {

    /**
     * Cache map to store tags that has multiple entries/values.
     */
    protected var parsedTagListCache: ConcurrentHashMap<String, MutableList<Tag>?> = ConcurrentHashMap<String, MutableList<Tag>?>()

    /**
     * Cache map to store tags that is single valued.
     */
    protected var parsedTagCache: ConcurrentHashMap<String, Tag?> = ConcurrentHashMap<String, Tag?>()

    /**
     * Returns a list of tags that matches the tag name.
     * @param tagName tag name
     * @return list of tags
     */
    protected fun getTagList(tagName: String): MutableList<Tag>? {
        var tagList: MutableList<Tag>? = parsedTagListCache[tagName]
        if (tagList == null) {
            tagList = arrayListOf()
            for (unparsedTag in tags) {
                if (unparsedTag.tagName == tagName) {
                    val tag: Tag? = TagFactory.createTag(tagName, unparsedTag)
                    tag?.tag = unparsedTag
                    tag?.let { tagList.add(it) }
                }
            }
            parsedTagListCache[tagName] = tagList
        }
        return tagList
    }

    /**
     * Returns the tag that matches the tag name.  If it is still unparsed, it would
     * proceed with the parsing and return it.
     * @param tagName tag name
     * @return tag
     */
    protected fun getTag(tagName: String): Tag? {
        var tag: Tag? = parsedTagCache[tagName]
        if (tag == null) {
            for (unparsedTag in tags) {
                if (unparsedTag.tagName == tagName) {
                    tag = TagFactory.createTag(tagName, unparsedTag)
                    tag?.tag = unparsedTag
                }
            }
            if (tag != null) {
                parsedTagCache[tagName] = tag
            }
        }
        return tag
    }

    override fun getVersion(): Version? {
        return getTag(TagNames.EXTXVERSION) as Version?
    }

    abstract override val isMasterPlaylist: Boolean

    override fun toString(): String {
        val builder = StringBuilder()
        for (tag in tags) {
            builder.append(tag.rawTag)
            if (tag.uri != null) {
                builder.append("\n")
                builder.append(tag.uri)
            }
            builder.append("\n")
        }
        return builder.toString()
    }
}
