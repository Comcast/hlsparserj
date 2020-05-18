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

class CueOutCont(override var tag: UnparsedTag?) : Tag {
    override var headerTag = false

    val elapsedTime: Float? by lazy { tag?.getAttributes()?.get(ELAPSED_TIME)?.toFloat() }
    val duration: Float? by lazy { tag?.getAttributes()?.get(DURATION)?.toFloat() }
    val scte35: String? by lazy { tag?.getAttributes()?.get(SCTE35) }

    companion object {
        private const val ELAPSED_TIME = "ElapsedTime"
        private const val DURATION = "Duration"
        private const val SCTE35 = "SCTE35"
    }
}
