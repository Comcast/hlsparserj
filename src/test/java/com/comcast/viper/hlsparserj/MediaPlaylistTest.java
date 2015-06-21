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

import com.comcast.viper.hlsparserj.tags.media.Key;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class MediaPlaylistTest {

    private InputStream mediaPlaylistIS = null;
    private MediaPlaylist mediaPlaylist;

    @Before
    public void before() {
        mediaPlaylistIS = MediaPlaylistTest.class
                .getResourceAsStream("/mediaplaylist.m3u8");
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
    public void mediaPlaylist() {
        assertEquals(mediaPlaylist.getSegments().size(), 15);
    }

    @Test
    public void segmentURIs() {
        assertTrue(mediaPlaylist.getSegments().get(0).getURI()
                .equals("20140311T113819-01-338559live.ts"));
        assertTrue(mediaPlaylist.getSegments().get(14).getURI()
                .equals("20140311T113819-01-338573live.ts"));
    }

    @Test
    public void segmentDurations() {
        float dur = Float.parseFloat("2.002");
        assertEquals(Float.compare(mediaPlaylist.getSegments().get(0)
                .getDuration(), dur), 0);
        assertEquals(Float.compare(mediaPlaylist.getSegments().get(14)
                .getDuration(), dur), 0);
    }

    @Test
    public void targetDuration() {
        assertEquals(mediaPlaylist.getTargetDuration().getDuration(), 3);
    }

    @Test
    public void mediaSequence() {
        assertEquals(mediaPlaylist.getMediaSequence().getSequenceNumber(),
                338559);
    }

    @Test
    public void key() {
        Key key = mediaPlaylist.getSegments().get(5).getKey();
        assertEquals(key.getIV(), "0xb059217aa2649ce170b734");
        assertEquals(key.getMethod(), "AES-128");
        assertEquals(key.getURI(), "https://secure.domain.com");
    }

    @Test
    public void version() {
        assertEquals(mediaPlaylist.getVersion().getVersion(), 4);
    }
}
