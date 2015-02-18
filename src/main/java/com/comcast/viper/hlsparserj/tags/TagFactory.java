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

import java.lang.reflect.Constructor;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Provides factory methods to create tags.  Registration of the tags
 * are required for the factory to be aware of them.
 */
public class TagFactory {

    private static ConcurrentHashMap<String, Class<?>> tagMap = new ConcurrentHashMap<String, Class<?>>();

    /**
     * Registers a tag.
     * @param tagName tag name
     * @param tagClass tag class
     */
    public static void registerTag(final String tagName, final Class<?> tagClass) {
        tagMap.put(tagName, tagClass);
    }

    /**
     * Creates a tag given the name.
     * @param tagName tag name
     * @return tag
     */
    public static Tag createTag(final String tagName) {
        Object tagInstance = null;

        Class<?> tagClass = tagMap.get(tagName);
        if (tagClass != null) {
            try {
                Constructor<?> tagCons = tagClass.getConstructor(new Class[] {});
                tagInstance = tagCons.newInstance(new Object[] {});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (tagInstance instanceof Tag) {
            return (Tag) tagInstance;
        } else {
            return null;
        }
    }
}
