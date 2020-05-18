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
import com.realeyes.hlsparserj.tags.TagNames
import com.realeyes.hlsparserj.tags.UnparsedTag
import com.realeyes.hlsparserj.tags.master.IFrameStreamInf
import com.realeyes.hlsparserj.tags.master.StreamInf
import kotlin.math.abs

/**
 * Abstract class for a master playlist.
 */
abstract class MasterPlaylist(version: PlaylistVersion, tags: MutableList<UnparsedTag>) : AbstractPlaylist(version, tags) {
    /**
     * Return list of variant streams (tags prefixed with 'EXT-X-STREAM-INF').
     * @return list of variant streams
     */
    val variantStreams: MutableList<StreamInf>?
        get() = getTagList(TagNames.EXTXSTREAMINF) as MutableList<StreamInf>?

    /**
     * Return list of I-Frame streams (tags prefixed with 'EXT-X-I-FRAME-STREAM-INF').
     * @return list of I-Frame streams
     */
    val iFrameStreams: MutableList<IFrameStreamInf>?
        get() = getTagList(TagNames.EXTXIFRAMESSTREAMINF) as MutableList<IFrameStreamInf>?

    /**
     * Return list of alternate renditions.
     *
     * Tag key: EXT-X-MEDIA.
     *
     * @return list of alternate renditions
     */
    val alternateRenditions: List<Any?>?
        get() = getTagList(TagNames.EXTXMEDIA)

    /**
     * Removes a given variant stream from the playlist.
     * @param variantStream variant stream
     */
    fun removeVariantStream(variantStream: StreamInf?) {
        tags.remove(variantStream?.tag)
        parsedTagListCache[TagNames.EXTXSTREAMINF]?.remove(variantStream as Tag)
    }

    /**
     * Removes all variant streams except the one closest to a given bitrate.
     * @param bitrate bitrate
     */
    fun keepVariantStreamClosestToBitrate(bitrate: Int) {
        val variantStreamToKeep: StreamInf? = variantStreamClosestToBitrate(bitrate)
        val variantStreams: List<StreamInf?>? = variantStreams
        val streamsToRemove: MutableList<StreamInf?> = ArrayList<StreamInf?>()
        for (i in variantStreams!!.indices) {
            val variantStream: StreamInf? = variantStreams[i]
            if (variantStream != null && variantStream != variantStreamToKeep) {
                streamsToRemove.add(variantStream)
            }
        }
        for (stream in streamsToRemove) {
            removeVariantStream(stream)
        }
    }

    /**
     * Return the variant stream within the playlist that is closest to a given bitrate.
     * @param bitrate bitrate
     * @return variant stream
     */
    fun variantStreamClosestToBitrate(bitrate: Int): StreamInf? {
        var closestBitrate = Int.MAX_VALUE
        var closestVariant: StreamInf? = null
        if (variantStreams != null) {
            for (variant in variantStreams!!) {
                val bitrateDelta: Int = abs(variant.bandwidth - bitrate)
                if (bitrateDelta < closestBitrate) {
                    closestBitrate = bitrateDelta
                    closestVariant = variant
                }
            }
        }
        return closestVariant
    }

    override val isMasterPlaylist: Boolean
        get() = true
}
