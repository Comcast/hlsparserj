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

import com.comcast.viper.hlsparserj.tags.TagNames;
import com.comcast.viper.hlsparserj.tags.UnparsedTag;
import com.comcast.viper.hlsparserj.tags.master.Media;
import com.comcast.viper.hlsparserj.tags.master.StreamInf;

/**
 * Abstract class for a master playlist.
 */
public abstract class MasterPlaylist extends AbstractPlaylist {

    /**
     * Constructor.
     * @param version playlist version
     * @param tags list of tags
     */
    public MasterPlaylist(final PlaylistVersion version, final List<UnparsedTag> tags) {
        super(version, tags);
    }

    /**
     * Return list of variant streams (tags prefixed with 'EXT-X-STREAM-INF').
     * @return list of variant streams
     */
    @SuppressWarnings("unchecked")
    public List<StreamInf> getVariantStreams() {
        return (List<StreamInf>) getTagList(TagNames.EXTXSTREAMINF);
    }

    /**
     * Return list of alternate renditions.
     *
     * Tag key: EXT-X-MEDIA.
     *
     * @return list of alternate renditions
     */
    @SuppressWarnings("unchecked")
    public List<Media> getAlternateRenditions() {
        return (List<Media>) getTagList(TagNames.EXTXMEDIA);
    }

    /**
     * Removes a given variant stream from the playlist.
     * @param variantStream variant stream
     */
    public void removeVariantStream(final StreamInf variantStream) {
        tags.remove(variantStream.getTag());
        parsedTagListCache.get(TagNames.EXTXSTREAMINF).remove(variantStream);
    }

    /**
     * Removes all variant streams except the one closest to a given bitrate.
     * @param bitrate bitrate
     */
    public void keepVariantStreamClosestToBitrate(final int bitrate) {
        StreamInf variantStreamToKeep = variantStreamClosestToBitrate(bitrate);

        List<StreamInf> variantStreams = getVariantStreams();
        List<StreamInf> streamsToRemove = new ArrayList<StreamInf>();

        for (int i = 0; i < variantStreams.size(); i++) {
            StreamInf variantStream = variantStreams.get(i);
            if (!variantStream.equals(variantStreamToKeep)) {
                streamsToRemove.add(variantStream);
            }
        }
        for (StreamInf stream : streamsToRemove) {
            removeVariantStream(stream);
        }
    }

    /**
     * Return the variant stream within the playlist that is closest to a given bitrate.
     * @param bitrate bitrate
     * @return variant stream
     */
    public StreamInf variantStreamClosestToBitrate(final int bitrate) {
        int closestBitrate = Integer.MAX_VALUE;
        StreamInf closestVariant = null;

        for (StreamInf variant : getVariantStreams()) {
            int bitrateDelta = Math.abs(variant.getBandwidth() - bitrate);
            if (bitrateDelta < closestBitrate) {
                closestBitrate = bitrateDelta;
                closestVariant = variant;
            }
        }

        return closestVariant;
    }

    @Override
    public boolean isMasterPlaylist() {
        return true;
    }
}
