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
 * <pre>
 * Represents an allow cache tag.
 *
 * The EXT-X-ALLOW-CACHE tag indicates whether the client MAY or MUST
 * NOT cache downloaded media segments for later replay.  It MAY occur
 * anywhere in a Media Playlist file; it MUST NOT occur more than once.
 * The EXT-X-ALLOW-CACHE tag applies to all segments in the playlist.
 * Its format is:
 *
 *   #EXT-X-ALLOW-CACHE: &lt;YES|NO&gt;
 * </pre>
 */
public class AllowCache extends Tag {

    /**
     * Returns if caching is allowed.
     * @return boolean
     */
    public boolean allowCache() {
        return yesNoBoolean(tag.getAttributes().get(UNNAMEDATTR0));
    }
}
