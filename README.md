
# HLSParserJ

## Introduction
*hlsparserj* is a Java library for parsing HTTP Live Streaming (HLS) playlist files. It is compliant with Version 12 of the Pantos spec:

http://tools.ietf.org/html/draft-pantos-http-live-streaming-12 


## Examples


### Find the Variant Playlist from the Master Playlist with the Highest Bitrate:

```
    // Get the playlist, but make sure it's a master playlist returned
    Playlist genericPlaylist = PlaylistFactory.parsePlaylist(PlaylistVersion.TWELVE, is);
    if (!genericPlaylist.isMasterPlaylist()) {
	    throw new Exception("Expected a master playlist but got a media playlist");
    }
    MasterPlaylist playlist = (MasterPlaylist)genericPlaylist;

    URL topBitrateVariant = null;
    int highestBitrate = Integer.MIN_VALUE;
    for (StreamInf variant : playlist.getVariantStreams()) {
    	if (variant.getBandwidth() > highestBitrate) {
    		topBitrateVariant = new URL(variant.getURI());
    	}
    }
```

## Developer Notes

### How to Add New/Custom Tags

1. Extend the abstract Tag class and create a new class for your tag in com.comcast.viper.hlsparserj.tags. 
  * Add getter methods for the attributes of your tag. See the Media object for an example.
  * Unnamed attributes (e.g. EXT-X-VERSION:4) are referred to as UNNAMEDATTR[num]. See the Version object for an example.
2. Add a new name for your tag to com.viper.hlsparserj.tags.TagNames
  * Also add the static block to register this new tag with the TagFactory
3. Create a new playlist version in com.viper.hlsparserj.PlaylistVersion
  * Numeric version identifiers should be reserved for Pantos spec-compliant implementation
  * Create a new package, similar to the v12 package
  * Extend either MasterPlaylist or MediaPlaylist, depending on where your tag lives
  * Add a getter method to this extension. See the top level MasterPlaylist class for examples
