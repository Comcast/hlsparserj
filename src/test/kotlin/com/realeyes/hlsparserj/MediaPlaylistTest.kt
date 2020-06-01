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

import com.realeyes.hlsparserj.tags.media.Key
import com.realeyes.hlsparserj.tags.media.SCTE35
import java.io.InputStream
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MediaPlaylistTest {
    private var mediaPlaylistIS: InputStream? = null
    private var mediaPlaylist: MediaPlaylist? = null

    @Before
    fun before() {
        mediaPlaylistIS = MediaPlaylistTest::class.java
            .getResourceAsStream("/mediaplaylist.m3u8")
        try {
            mediaPlaylist = PlaylistFactory.parsePlaylist(
                PlaylistVersion.TWELVE, mediaPlaylistIS
            ) as MediaPlaylist?
        } catch (e: Exception) {
            e.printStackTrace()
            Assert.fail()
        }
    }

    @After
    fun after() {
        try {
            if (mediaPlaylistIS != null) {
                mediaPlaylistIS!!.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Test
    fun mediaPlaylist() {
        assertEquals(mediaPlaylist?.segments?.size, 15)
    }

    @Test
    fun segmentURIs() {
        Assert.assertTrue(
            mediaPlaylist?.segments?.get(0)?.uri
                .equals("20140311T113819-01-338559live.ts")
        )
        Assert.assertTrue(
            mediaPlaylist?.segments?.get(14)?.uri
                .equals("20140311T113819-01-338573live.ts")
        )
    }

    @Test
    fun segmentDurations() {
        val dur = "2.002".toFloat()
        Assert.assertEquals(
            mediaPlaylist?.segments?.get(0)?.duration?.compareTo(dur)?.toLong(), 0L
        )
        Assert.assertEquals(
            mediaPlaylist?.segments?.get(14)?.duration?.compareTo(dur)?.toLong(), 0L
        )
    }

    @Test
    fun segmentDateTime() {
        val dateTime = 1266562463031L
        val datetiem = mediaPlaylist?.segments?.get(0)?.absoluteTime
        println(dateTime)
        Assert.assertEquals(
            mediaPlaylist?.segments?.get(0)?.absoluteTime, dateTime
        )
        Assert.assertEquals(
                mediaPlaylist?.segments?.get(1)?.absoluteTime, dateTime + (mediaPlaylist?.segments?.get(1)?.duration?.times(1000F))!!.toLong()
        )
    }

    @Test
    fun targetDuration() {
        assertEquals(mediaPlaylist?.targetDuration?.duration, 3)
    }

    @Test
    fun mediaSequence() {
        assertEquals(
            mediaPlaylist?.mediaSequence?.sequenceNumber,
            338559
        )
    }

    @Test
    fun key() {
        val key: Key? = mediaPlaylist?.segments?.get(5)?.key
        assertEquals(key?.iv, "0xb059217aa2649ce170b734")
        assertEquals(key?.method, "AES-128")
        assertEquals(key?.uri, "https://secure.domain.com")
    }

    @Test
    fun version() {
        assertEquals(mediaPlaylist!!.getVersion()?.version, 4)
    }

    @Test
    fun scte35() {
        val scte: SCTE35? = mediaPlaylist?.segments?.get(0)?.scte35
        assertEquals(scte?.cue, "/DAgAAAAAAAAAP/wDwUAAF0bf0/+AAAAAAAAAAAAAOYi0Es=")
    }
}
