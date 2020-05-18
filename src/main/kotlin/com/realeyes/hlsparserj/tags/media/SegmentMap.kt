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
import com.realeyes.hlsparserj.tags.UnparsedTag

class SegmentMap(override var tag: UnparsedTag?) : Tag {
    override var headerTag = false

    val uri: String by lazy { tag?.getAttributes()?.get(URI).toString() }

    val byteRange: String? by lazy { tag?.getAttributes()?.get(BYTE_RANGE) }

    companion object {
        private const val URI = "URI"
        private const val BYTE_RANGE = "BYTERANGE"
    }
}
