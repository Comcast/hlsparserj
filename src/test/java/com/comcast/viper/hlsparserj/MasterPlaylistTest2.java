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
import static org.junit.Assert.fail;

import java.io.InputStream;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.comcast.viper.hlsparserj.MasterPlaylist;
import com.comcast.viper.hlsparserj.PlaylistFactory;
import com.comcast.viper.hlsparserj.PlaylistVersion;
import com.comcast.viper.hlsparserj.tags.master.StreamInf;

public class MasterPlaylistTest2 {
    private InputStream masterPlaylistIS = null;
    private MasterPlaylist masterPlaylist;

    @Before
    public void before() {
        masterPlaylistIS = MasterPlaylistTest.class
                .getResourceAsStream("/masterplaylist2.m3u8");
        try {
            masterPlaylist = (MasterPlaylist) PlaylistFactory.parsePlaylist(
                    PlaylistVersion.TWELVE, masterPlaylistIS);
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
        assertEquals(streamInfList.size(), 4);

    }

    @Test
    public void streamInfBitrates() {
        List<StreamInf> streamInfList = masterPlaylist.getVariantStreams();

        assertEquals(streamInfList.get(0).getBandwidth(), 200000);
        assertEquals(streamInfList.get(1).getBandwidth(), 311111);
        assertEquals(streamInfList.get(2).getBandwidth(), 484444);
        assertEquals(streamInfList.get(3).getBandwidth(), 737777);

    }

    @Test
    public void streamInfProgramId() {
        List<StreamInf> streamInfList = masterPlaylist.getVariantStreams();
        for (StreamInf streamInf : streamInfList) {
            assertEquals(streamInf.getProgramId(), 1);
        }
    }
}
