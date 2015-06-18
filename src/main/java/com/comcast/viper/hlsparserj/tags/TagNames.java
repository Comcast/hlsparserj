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

import com.comcast.viper.hlsparserj.tags.master.IFrameStreamInf;
import com.comcast.viper.hlsparserj.tags.master.Media;
import com.comcast.viper.hlsparserj.tags.master.StreamInf;
import com.comcast.viper.hlsparserj.tags.media.AllowCache;
import com.comcast.viper.hlsparserj.tags.media.ByteRange;
import com.comcast.viper.hlsparserj.tags.media.ExtInf;
import com.comcast.viper.hlsparserj.tags.media.IFramesOnly;
import com.comcast.viper.hlsparserj.tags.media.Key;
import com.comcast.viper.hlsparserj.tags.media.MediaSequence;
import com.comcast.viper.hlsparserj.tags.media.PlaylistType;
import com.comcast.viper.hlsparserj.tags.media.TargetDuration;

/**
 * Contains the playlist tag names.  Automatically registers them with the tag factory.
 */
public class TagNames {

    /**
     * Version tag.
     *
     * <pre>
     * Format:
     *   #EXT-X-VERSION:&lt;n&gt;
     *
     * Example:
     *   #EXT-X-VERSION:4
     * </pre>
     */
    public static final String EXTXVERSION = "EXT-X-VERSION";
    static {
        TagFactory.registerTag(EXTXVERSION, Version.class);
    }

    /**
     * Variant stream tag.
     *
     * <pre>
     * Format:
     *   #EXT-X-STREAM-INF:&lt;attribute-list&gt;
     *   &lt;URI&gt;
     *
     * Example:
     *   #EXT-X-STREAM-INF:PROGRAM-ID=1, BANDWIDTH=200000
     *   gear1/prog_index.m3u8
     * </pre>
     */
    public static final String EXTXSTREAMINF = "EXT-X-STREAM-INF";
    static {
        TagFactory.registerTag(EXTXSTREAMINF, StreamInf.class);
    }

    /**
     * Media playlist that contain alternate rendition tag.
     *
     * <pre>
     * Format:
     *   #EXT-X-MEDIA:&lt;attribute-list&gt;
     *
     * Example:
     *   #EXT-X-MEDIA:TYPE=AUDIO,GROUP-ID="Audio1",NAME="mp4a",LANGUAGE="spa",DEFAULT=YES,AUTOSELECT=YES,URI="A1.m3u8"
     * </pre>
     */
    public static final String EXTXMEDIA = "EXT-X-MEDIA";
    static {
        TagFactory.registerTag(EXTXMEDIA, Media.class);
    }

    /**
     * Media segment tag.
     *
     * <pre>
     * Format:
     *   #EXTINF:&lt;duration&gt;,&lt;title&gt;
     *   &lt;URI&gt;
     *
     * Example:
     *   #EXTINF:2.002,338559
     *   20140311T113819-01-338559live.ts
     * </pre>
     */
    public static final String EXTINF = "EXTINF";
    static {
        TagFactory.registerTag(EXTINF, ExtInf.class);
    }

    /**
     * Byte range tag.
     *
     * <pre>
     * Format:
     *   #EXT-X-BYTERANGE:&lt;n&gt;[@&lt;o&gt;]
     * </pre>
     */
    public static final String EXTXBYTERANGE = "EXT-X-BYTERANGE";
    static {
        TagFactory.registerTag(EXTXBYTERANGE, ByteRange.class);
    }

    /**
     * Target duration tag.
     *
     * <pre>
     * Format:
     *   #EXT-X-TARGETDURATION:&lt;s&gt;
     *
     * Target:
     *   #EXT-X-TARGETDURATION:3
     * </pre>
     */
    public static final String EXTXTARGETDURATION = "EXT-X-TARGETDURATION";
    static {
        TagFactory.registerTag(EXTXTARGETDURATION, TargetDuration.class);
    }

    /**
     * Media sequence tag.
     *
     * <pre>
     * Format:
     *   #EXT-X-MEDIA-SEQUENCE:&lt;number&gt;
     *
     * Example:
     *   #EXT-X-MEDIA-SEQUENCE:338559
     * </pre>
     */
    public static final String EXTXMEDIASEQUENCE = "EXT-X-MEDIA-SEQUENCE";
    static {
        TagFactory.registerTag(EXTXMEDIASEQUENCE, MediaSequence.class);
    }

    /**
     * Discontinuity tag.
     *
     * No discontinuity class is required.
     */
    public static final String EXTXDISCONTINUITY = "EXT-X-DISCONTINUITY";

    /**
     * Key tag.
     *
     * <pre>
     * Format:
     *   #EXT-X-KEY:&lt;attribute-list&gt;
     *
     * Example:
     *   #EXT-X-KEY:METHOD=AES-128,URI="https://security.domain.com",IV=0xaa2649ce170b734
     * </pre>
     */
    public static final String EXTXKEY = "EXT-X-KEY";
    static {
        TagFactory.registerTag(EXTXKEY, Key.class);
    }

    /**
     * Program date time tag.
     *
     * (No date-time class is required)
     *
     * <pre>
     * Format:
     *   #EXT-X-PROGRAM-DATE-TIME:&lt;YYYY-MM-DDThh:mm:ssZ&gt;
     *
     * Example:
     *   #EXT-X-PROGRAM-DATE-TIME:2010-02-19T14:54:23.031+08:00
     * </pre>
     */
    public static final String EXTXPROGRAMDATETIME = "EXT-X-PROGRAM-DATE-TIME";

    /**
     * Allow cache tag.
     *
     * <pre>
     * Format:
     *   #EXT-X-ALLOW-CACHE:&lt;YES|NO&gt;
     *
     * Example:
     *   #EXT-X-ALLOW-CACHE:YES
     * </pre>
     */
    public static final String EXTXALLOWCACHE = "EXT-X-ALLOW-CACHE";
    static {
        TagFactory.registerTag(EXTXALLOWCACHE, AllowCache.class);
    }

    /**
     * Playlist type tag.
     *
     * <pre>
     * Format:
     *   #EXT-X-PLAYLIST-TYPE:&lt;EVENT|VOD&gt;
     *
     * Example:
     *   #EXT-X-PLAYLIST-TYPE:EVENT
     * </pre>
     */
    public static final String EXTXPLAYLISTTYPE = "EXT-X-PLAYLIST-TYPE";
    static {
        TagFactory.registerTag(EXTXPLAYLISTTYPE, PlaylistType.class);
    }

    /**
     * Iframes only tag.
     *
     * <pre>
     * Format:
     *   #EXT-X-I-FRAMES-ONLY
     * </pre>
     */
    public static final String EXTXIFRAMESONLY = "EXT-X-I-FRAMES-ONLY";
    static {
        TagFactory.registerTag(EXTXIFRAMESONLY, IFramesOnly.class);
    }

    /**
     * Iframes stream tag.
     *
     * <pre>
     * Format:
     *   #EXT-X-STREAM-INF:&lt;attribute-list&gt;
     *
     * Example:
     *  #EXT-X-I-FRAME-STREAM-INF:BANDWIDTH=28451,CODECS="avc1.4d400d",URI="iframe_index.m3u8"
     * </pre>
     */
    public static final String EXTXIFRAMESSTREAMINF = "EXT-X-I-FRAME-STREAM-INF";
    static {
        TagFactory.registerTag(EXTXIFRAMESSTREAMINF, IFrameStreamInf.class);
    }
}
