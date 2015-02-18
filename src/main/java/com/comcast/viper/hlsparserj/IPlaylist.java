package com.comcast.viper.hlsparserj;

import java.util.List;

import com.comcast.viper.hlsparserj.tags.UnparsedTag;
import com.comcast.viper.hlsparserj.tags.Version;

/**
 * Serves as the interface of a playlist object.  There are
 * two kinds of playlists that are supported:
 *
 * <ul>
 *  <li>master playlist: if URI lines in playlist identify media playlists</li>
 *  <li>media playlist: if URI lines in playlist identify media segments</li>
 * </ul>
 */
public interface IPlaylist {

    /**
     * Returns the version of the playlist.
     * @return version
     */
    Version getVersion();

    /**
     * Returns true if this is a master playlist; false if a media playlist.
     * @return boolean
     */
    boolean isMasterPlaylist();

    /**
     * Returns list of tags in this playlist.
     * @return list of tags
     */
    List<UnparsedTag> getTags();
}
