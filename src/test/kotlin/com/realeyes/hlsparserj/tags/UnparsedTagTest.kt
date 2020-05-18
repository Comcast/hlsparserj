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
