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
package com.comcast.viper.hlsparserj;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.comcast.viper.hlsparserj.tags.Tag;
import com.comcast.viper.hlsparserj.tags.TagFactory;
import com.comcast.viper.hlsparserj.tags.TagNames;
import com.comcast.viper.hlsparserj.tags.UnparsedTag;
import com.comcast.viper.hlsparserj.tags.Version;

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
public abstract class AbstractPlaylist implements IPlaylist {

    /**
     * List of tags in the playlist.
     */
    protected List<UnparsedTag> tags;

    /**
     * Cache map to store tags that has multiple entries/values.
     */
    protected ConcurrentHashMap<String, List<Tag>> parsedTagListCache;

    /**
     * Cache map to store tags that is single valued.
     */
    protected ConcurrentHashMap<String, Tag> parsedTagCache;

    /**
     * Version tag of the playlist.
     */
    protected PlaylistVersion version;

    /**
     * Constructor.
     * @param version playlist version
     * @param tags list of tags
     */
    public AbstractPlaylist(final PlaylistVersion version, final List<UnparsedTag> tags) {
        this.version = version;
        this.tags = tags;

        parsedTagListCache = new ConcurrentHashMap<String, List<Tag>>();
        parsedTagCache = new ConcurrentHashMap<String, Tag>();
    }

    /**
     * Returns a list of tags that matches the tag name.
     * @param tagName tag name
     * @return list of tags
     */
    protected List<? extends Tag> getTagList(final String tagName) {
        List<Tag> tagList = parsedTagListCache.get(tagName);
        if (tagList == null) {
            tagList = new ArrayList<Tag>();
            for (UnparsedTag unparsedTag : tags) {
                if (unparsedTag.getTagName().equals(tagName)) {
                    Tag tag = TagFactory.createTag(tagName);
                    tag.setTag(unparsedTag);
                    tagList.add(tag);
                }
            }
            parsedTagListCache.put(tagName, tagList);
        }
        return tagList;
    }

    /**
     * Returns the tag that matches the tag name.  If it is still unparsed, it would
     * proceed with the parsing and return it.
     * @param tagName tag name
     * @return tag
     */
    protected Tag getTag(final String tagName) {
        Tag tag = parsedTagCache.get(tagName);
        if (tag == null) {
            for (UnparsedTag unparsedTag : tags) {
                if (unparsedTag.getTagName().equals(tagName)) {
                    tag = TagFactory.createTag(tagName);
                    tag.setTag(unparsedTag);
                }
            }
            if (tag != null) {
                parsedTagCache.put(tagName, tag);
            }
        }
        return tag;
    }

    @Override
    public Version getVersion() {
        return (Version) getTag(TagNames.EXTXVERSION);
    }

    @Override
    public abstract boolean isMasterPlaylist();

    @Override
    public List<UnparsedTag> getTags() {
        return this.tags;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (UnparsedTag tag : tags) {
            builder.append(tag.getRawTag());
            if (tag.getURI() != null) {
                builder.append("\n");
                builder.append(tag.getURI());
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
