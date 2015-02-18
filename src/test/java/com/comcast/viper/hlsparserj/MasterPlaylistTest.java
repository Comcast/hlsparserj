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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.comcast.viper.hlsparserj.MasterPlaylist;
import com.comcast.viper.hlsparserj.PlaylistFactory;
import com.comcast.viper.hlsparserj.PlaylistVersion;
import com.comcast.viper.hlsparserj.tags.master.Media;
import com.comcast.viper.hlsparserj.tags.master.StreamInf;

public class MasterPlaylistTest {

    private InputStream masterPlaylistIS = null;
    private MasterPlaylist masterPlaylist;

    @Before
    public void before() {
        masterPlaylistIS = MasterPlaylistTest.class.getResourceAsStream("/masterplaylist.m3u8");
        try {
            masterPlaylist = (MasterPlaylist) PlaylistFactory.parsePlaylist(PlaylistVersion.TWELVE, masterPlaylistIS);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @After
    public void after() {
        try {
            if (masterPlaylistIS != null) {
                masterPlaylistIS.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void masterPlaylist() {
        List<StreamInf> streamInfList = masterPlaylist.getVariantStreams();
        assertEquals(streamInfList.size(), 3);
    }

    @Test
    public void testIsMasterPlaylist() {
        assertTrue(masterPlaylist.isMasterPlaylist());
    }

    @Test
    public void streamInfBitrates() {
        List<StreamInf> streamInfList = masterPlaylist.getVariantStreams();

        assertEquals(streamInfList.get(0).getBandwidth(), 395000);
        assertEquals(streamInfList.get(1).getBandwidth(), 963000);
        assertEquals(streamInfList.get(2).getBandwidth(), 1695000);
    }

    @Test
    public void streamInfCodecs() {
        List<StreamInf> streamInfList = masterPlaylist.getVariantStreams();
        String codecString = "avc1.4d001f,mp4a.40.2";

        for (StreamInf variant : streamInfList) {
            assertEquals(variant.getCodecs(), codecString);
        }
    }

    @Test
    public void streamInfResolution() {
        List<StreamInf> streamInfList = masterPlaylist.getVariantStreams();

        assertTrue(streamInfList.get(0).getResolution().equals("320x240"));
        assertTrue(streamInfList.get(1).getResolution().equals("448x336"));
        assertTrue(streamInfList.get(2).getResolution().equals("640x480"));
    }

    @Test
    public void streamInfAudio() {
        List<StreamInf> streamInfList = masterPlaylist.getVariantStreams();
        String audioString = "Audio1";

        for (StreamInf variant : streamInfList) {
            assertEquals(variant.getAudio(), audioString);
        }
    }

    @Test
    public void version() {
        assertEquals(masterPlaylist.getVersion().getVersion(), 4);
    }

    @Test
    public void alternateRenditions() {
        List<Media> mediaList = masterPlaylist.getAlternateRenditions();

        assertEquals(mediaList.size(), 1);

        Media media = mediaList.get(0);
        assertTrue(media.getType().equals("AUDIO"));
        assertTrue(media.getGroupId().equals("Audio1"));
        assertTrue(media.getName().equals("mp4a.40.2_96K_Spanish"));
        assertTrue(media.getLanguage().equals("spa"));
        assertTrue(media.getDefault());
        assertTrue(media.getAutoSelect());
        assertTrue(media.getURI().equals("A1.m3u8"));
    }

    @Test
    public void testVariantStreamClosestToBitrate() {
        StreamInf variantStream = masterPlaylist.variantStreamClosestToBitrate(100000);
        assertNotNull(variantStream);
        assertEquals(395000, variantStream.getBandwidth());
    }

    @Test
    public void testVariantStreamClosestToBitrate_BitrateAtMidpoint() {
        StreamInf variantStream = masterPlaylist.variantStreamClosestToBitrate(679000);
        assertNotNull(variantStream);
        assertEquals(395000, variantStream.getBandwidth());
    }

    @Test
    public void testRemoveStreamInf() {
        List<StreamInf> variantStreams = masterPlaylist.getVariantStreams();

        StreamInf variantStream = variantStreams.get(0);
        masterPlaylist.removeVariantStream(variantStream);;

        assertEquals(2, masterPlaylist.getVariantStreams().size());
        assertFalse(masterPlaylist.getVariantStreams().contains(variantStream));
        assertFalse(masterPlaylist.toString().contains(String.valueOf(variantStream.getBandwidth())));
    }

    @Test
    public void testKeepVariantStreamClosestToBitrate() {
        List<StreamInf> variantStreams = masterPlaylist.getVariantStreams();
        assertEquals(3, variantStreams.size());

        masterPlaylist.keepVariantStreamClosestToBitrate(100000);

        assertEquals(1, masterPlaylist.getVariantStreams().size());
        assertTrue(masterPlaylist.toString().contains("395000"));
    }

    @Test
    public void testToString() throws Exception {
        assertEquals(masterPlaylistString(), masterPlaylist.toString());
    }

    private String masterPlaylistString() throws Exception {
        masterPlaylistIS = MasterPlaylistTest.class.getResourceAsStream("/masterplaylist.m3u8");
        InputStreamReader isReader = new InputStreamReader(masterPlaylistIS);
        BufferedReader bufReader = new BufferedReader(isReader);
        StringBuilder builder = new StringBuilder();

        String line;
        while ((line = bufReader.readLine()) != null) {
            builder.append(line);
            builder.append("\n");
        }
        return builder.toString();
    }
}
