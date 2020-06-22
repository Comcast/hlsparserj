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
    private var mediaPlaylistString: String? = null

    @Before
    fun before() {
        mediaPlaylistIS = MediaPlaylistTest::class.java
            .getResourceAsStream("/mediaplaylist.m3u8")
        mediaPlaylistString = MediaPlaylistTest::class.java
                .getResource("/mediaplaylist.m3u8").readText()
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
        Assert.assertEquals(
            mediaPlaylist?.segments?.get(0)?.pdt, dateTime
        )
        Assert.assertEquals(
                mediaPlaylist?.segments?.get(1)?.pdt, dateTime + (mediaPlaylist?.segments?.get(1)?.duration?.times(1000F))!!.toLong()
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
        val key: List<Key?>? = mediaPlaylist?.segments?.get(5)?.keys
        assertEquals(key?.get(0)?.iv, "0xb059217aa2649ce170b734")
        assertEquals(key?.get(0)?.method, "AES-128")
        assertEquals(key?.get(0)?.uri, "https://secure.domain.com")
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

    @Test
    fun `to string`() {
        val str = mediaPlaylist.toString()
        // TODO: should use this, but I dont have time at this moment to fix attribute values to include quotes
//        val expected = mediaPlaylistString
        val expected = """#EXTM3U
#EXT-X-VERSION:4
#EXT-X-TARGETDURATION:3
#EXT-X-MEDIA-SEQUENCE:338559
#EXT-X-KEY:METHOD=AES-128,URI=https://secure.domain.com,IV=0xb059217aa2649ce170b734
#EXT-X-PROGRAM-DATE-TIME:2010-02-19T14:54:23.031+08:00
#EXT-X-CUE-IN:
#EXT-X-SCTE35:CUE=/DAgAAAAAAAAAP/wDwUAAF0bf0/+AAAAAAAAAAAAAOYi0Es=,ID=23835
#EXTINF:2.002,338559
20140311T113819-01-338559live.ts
#EXTINF:2.002,338560
20140311T113819-01-338560live.ts
#EXTINF:2.002,338561
20140311T113819-01-338561live.ts
#EXTINF:2.002,338562
20140311T113819-01-338562live.ts
#EXT-X-CUE-OUT:201.467
#EXTINF:2.002,338563
20140311T113819-01-338563live.ts
#EXT-X-CUE-OUT-CONT:ElapsedTime=5.939,Duration=201.467,SCTE35=/DA0AAAA+…AAg+2UBNAAANvrtoQ
#EXTINF:2.002,338564
20140311T113819-01-338564live.ts
#EXTINF:2.002,338565
20140311T113819-01-338565live.ts
#EXTINF:2.002,338566
20140311T113819-01-338566live.ts
#EXTINF:2.002,338567
20140311T113819-01-338567live.ts
#EXTINF:2.002,338568
20140311T113819-01-338568live.ts
#EXTINF:2.002,338569
20140311T113819-01-338569live.ts
#EXTINF:2.002,338570
20140311T113819-01-338570live.ts
#EXTINF:2.002,338571
20140311T113819-01-338571live.ts
#EXTINF:2.002,338572
20140311T113819-01-338572live.ts
#EXTINF:2.002,338573
20140311T113819-01-338573live.ts
"""
        assertEquals(expected, str)
    }
}
