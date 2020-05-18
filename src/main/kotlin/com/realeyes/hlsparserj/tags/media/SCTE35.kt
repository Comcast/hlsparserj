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
import com.realeyes.hlsparserj.tags.Tag.Companion.UNNAMEDATTR0
import com.realeyes.hlsparserj.tags.UnparsedTag

class SCTE35(override var tag: UnparsedTag?) : Tag {
    override var headerTag = false

    val legacyAttribute: String? by lazy { tag?.getAttributes()?.get(UNNAMEDATTR0) }

    val cue: String? by lazy { tag?.getAttributes()?.get(CUE) }

    val id: String? by lazy { tag?.getAttributes()?.get(ID) }

    val duration: Float? by lazy { tag?.getAttributes()?.get(DURATION)?.toFloat() }

    val elapsed: Float? by lazy { tag?.getAttributes()?.get(ELAPSED)?.toFloat() }

    val time: Float? by lazy { tag?.getAttributes()?.get(TIME)?.toFloat() }

    val type: Int? by lazy { tag?.getAttributes()?.get(TYPE)?.toInt() }

    val upid: String? by lazy { tag?.getAttributes()?.get(UPID) }

    val blackout: String? by lazy { tag?.getAttributes()?.get(BLACKOUT) }

    val cueOut: String? by lazy { tag?.getAttributes()?.get(CUE_OUT) }

    val cueIn: String? by lazy { tag?.getAttributes()?.get(CUE_IN) }

    val segne: String? by lazy { tag?.getAttributes()?.get(SEGNE) }

    companion object {
        private const val CUE = "CUE" // string
        private const val ID = "ID" // string
        private const val DURATION = "DURATION" // double
        private const val ELAPSED = "ELAPSED" // double
        private const val TIME = "TIME" // double
        private const val TYPE = "TYPE" // int
        private const val UPID = "UPID" // string
        private const val BLACKOUT = "BLACKOUT" // string
        private const val CUE_OUT = "CUE-OUT" // string
        private const val CUE_IN = "CUE-IN" // string
        private const val SEGNE = "SEGNE" // string
    }
}
