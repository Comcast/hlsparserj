package com.comcast.viper.hlsparserj.tags;

import org.junit.Assert;
import org.junit.Test;

public class UnparsedTagTest {
	@Test
	public void testParsingNoValues() {
		UnparsedTag tag = new UnparsedTag("#EXTM3U");

		Assert.assertTrue(tag.getAttributes().isEmpty());
	}

	@Test
	public void testParsingSingleValue() {
		UnparsedTag tag = new UnparsedTag("#EXT-X-MEDIA-SEQUENCE:0");

		Assert.assertEquals("0", tag.getAttributes().get(Tag.UNNAMEDATTR0));
	}

	@Test
	public void testParsingDoubleValue() {
		UnparsedTag tag = new UnparsedTag("#EXTINF:100,Segment Title");

		Assert.assertEquals("100", tag.getAttributes().get(Tag.UNNAMEDATTR0));
		Assert.assertEquals("Segment Title", tag.getAttributes().get(Tag.UNNAMEDATTR1));
	}

	@Test
	public void testParsingAttributeWithEqualsSign() {
		UnparsedTag tag = new UnparsedTag("#EXT-X-MEDIA:TYPE=AUDIO,GROUP-ID=\"group\",NAME=\"en\",URI=\"https://example.org/playlist.m3u8?param=value\"");

		Assert.assertEquals("https://example.org/playlist.m3u8?param=value", tag.getAttributes().get("URI"));
	}

	@Test
	public void testParsingAttributeWithoutEqualsSign() {
		UnparsedTag tag = new UnparsedTag("#EXT-X-MEDIA:TYPE=AUDIO,GROUP-ID=\"group\",NAME=\"en\",URI=\"https://example.org/playlist.m3u8\"");

		Assert.assertEquals("https://example.org/playlist.m3u8", tag.getAttributes().get("URI"));
	}
	@Test
	public void testParsingAttributeWithComma() {
		UnparsedTag tag = new UnparsedTag("#EXT-X-MEDIA:TYPE=AUDIO,GROUP-ID=\"group, subgroup\",NAME=\"en\",URI=\"https://example.org/playlist.m3u8\"");

		Assert.assertEquals("group, subgroup", tag.getAttributes().get("GROUP-ID"));
		Assert.assertEquals("https://example.org/playlist.m3u8", tag.getAttributes().get("URI"));
	}
}
