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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MediaPlaylistByteRangesTest {

    private InputStream mediaPlaylistIS = null;
    private MediaPlaylist mediaPlaylist;

    @Before
    public void before() {
        mediaPlaylistIS = MediaPlaylistByteRangesTest.class
                .getResourceAsStream("/mediaplaylist-byterange.m3u8");
        try {
            mediaPlaylist = (MediaPlaylist) PlaylistFactory.parsePlaylist(
                    PlaylistVersion.TWELVE, mediaPlaylistIS);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @After
    public void after() {
        try {
            if (mediaPlaylistIS != null) {
                mediaPlaylistIS.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void byteRanges() throws Exception {
        assertEquals(8, mediaPlaylist.getByteRanges().size());
        assertEquals(86920, mediaPlaylist.getByteRanges().get(0).getLength());
        assertEquals(0, mediaPlaylist.getByteRanges().get(0).getOffset());
        assertEquals("main.aac", mediaPlaylist.getByteRanges().get(0).getURI());
    }

    @Test
    public void byteRangesAfterSegments() throws Exception {
        mediaPlaylist.getSegments();
        assertEquals(8, mediaPlaylist.getByteRanges().size());
        assertEquals(86920, mediaPlaylist.getByteRanges().get(0).getLength());
        assertEquals(0, mediaPlaylist.getByteRanges().get(0).getOffset());
        assertEquals("main.aac", mediaPlaylist.getByteRanges().get(0).getURI());
    }
}
