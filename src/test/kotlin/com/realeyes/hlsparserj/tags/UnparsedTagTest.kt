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
package com.realeyes.hlsparserj.tags

import org.junit.Assert
import org.junit.Test

class UnparsedTagTest {
    @Test
    fun testParsingNoValues() {
        val tag = UnparsedTag("#EXTM3U")
        Assert.assertTrue(tag.getAttributes().isEmpty())
    }

    @Test
    fun testParsingSingleValue() {
        val tag = UnparsedTag("#EXT-X-MEDIA-SEQUENCE:0")
        Assert.assertEquals("0", tag.getAttributes()[Tag.UNNAMEDATTR0])
    }

    @Test
    fun testParsingDoubleValue() {
        val tag = UnparsedTag("#EXTINF:100,Segment Title")
        Assert.assertEquals("100", tag.getAttributes()[Tag.UNNAMEDATTR0])
        Assert.assertEquals("Segment Title", tag.getAttributes()[Tag.UNNAMEDATTR1])
    }

    @Test
    fun testParsingAttributeWithEqualsSign() {
        val tag =
            UnparsedTag("#EXT-X-MEDIA:TYPE=AUDIO,GROUP-ID=\"group\",NAME=\"en\",URI=\"https://example.org/playlist.m3u8?param=value\"")
        Assert.assertEquals("https://example.org/playlist.m3u8?param=value", tag.getAttributes()["URI"])
    }

    @Test
    fun testParsingAttributeWithoutEqualsSign() {
        val tag =
            UnparsedTag("#EXT-X-MEDIA:TYPE=AUDIO,GROUP-ID=\"group\",NAME=\"en\",URI=\"https://example.org/playlist.m3u8\"")
        Assert.assertEquals("https://example.org/playlist.m3u8", tag.getAttributes()["URI"])
    }

    @Test
    fun testParsingAttributeWithComma() {
        val tag =
            UnparsedTag("#EXT-X-MEDIA:TYPE=AUDIO,GROUP-ID=\"group, subgroup\",NAME=\"en\",URI=\"https://example.org/playlist.m3u8\"")
        Assert.assertEquals("group, subgroup", tag.getAttributes()["GROUP-ID"])
        Assert.assertEquals("https://example.org/playlist.m3u8", tag.getAttributes()["URI"])
    }
}
