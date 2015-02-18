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
package com.comcast.viper.hlsparserj.tags.master;

import com.comcast.viper.hlsparserj.tags.Tag;

/**
 * <pre>
 * Represents a media playlist that contain alternative renditions
 * of the same content.
 *
 * The EXT-X-MEDIA tag is used to relate Media Playlists that contain
 * alternative renditions of the same content.  For example, three EXT-
 * X-MEDIA tags can be used to identify audio-only Media Playlists that
 * contain English, French and Spanish renditions of the same
 * presentation.  Or two EXT-X-MEDIA tags can be used to identify video-
 * only Media Playlists that show two different camera angles.
 *
 * The EXT-X-MEDIA tag stands alone, in that it does not apply to a
 * particular URI in the Master Playlist.  Its format is:
 *
 *   #EXT-X-MEDIA:&lt;attribute-list&gt;
 *
 * The following attributes are defined:
 *
 * TYPE
 *
 * The value is enumerated-string; valid strings are AUDIO, VIDEO,
 * SUBTITLES and CLOSED-CAPTIONS.  If the value is AUDIO, the Playlist
 * described by the tag MUST contain audio media.  If the value is
 * VIDEO, the Playlist MUST contain video media.  If the value is
 * SUBTITLES, the Playlist MUST contain subtitle media.  If the value is
 * CLOSED-CAPTIONS, the media segments for the video renditions can
 * include closed captions.  This attribute is REQUIRED.
 *
 * URI
 *
 * The value is a quoted-string containing a URI that identifies the
 * Playlist file.  This attribute is OPTIONAL; see Section 3.4.10.1.  If
 * the TYPE is CLOSED-CAPTIONS, the URI attribute MUST NOT be present.
 *
 * GROUP-ID
 *
 * The value is a quoted-string identifying a mutually-exclusive group
 * of renditions.  The presence of this attribute signals membership in
 * the group.  See Section 3.4.9.1.  This attribute is REQUIRED.
 *
 * LANGUAGE
 *
 * The value is a quoted-string containing an RFC 5646 [RFC5646]
 * language tag that identifies the primary language used in the
 * rendition.  This attribute is OPTIONAL.
 *
 * ASSOC-LANGUAGE
 *
 * The value is a quoted-string containing an RFC 5646 [RFC5646]
 * language tag that identifies a language that is associated with the
 * rendition.  An associated language is often used in a different role
 * than the language specified by the LANGUAGE attribute (e.g. written
 * vs. spoken, or as a fallback dialect).  This attribute is OPTIONAL.
 *
 * NAME
 *
 * The value is a quoted-string containing a human-readable description
 * of the rendition.  If the LANGUAGE attribute is present then this
 * description SHOULD be in that language.  This attribute is REQUIRED.
 *
 * DEFAULT
 *
 * The value is an enumerated-string; valid strings are YES and NO.  If
 * the value is YES, then the client SHOULD play this rendition of the
 * content in the absence of information from the user indicating a
 * different choice.  This attribute is OPTIONAL.  Its absence indicates
 * an implicit value of NO.
 *
 * AUTOSELECT
 *
 * The value is an enumerated-string; valid strings are YES and NO.
 * This attribute is OPTIONAL.  If it is present, its value MUST be YES
 * if the value of the DEFAULT attribute is YES.  If the value is YES,
 * then the client MAY choose to play this rendition in the absence of
 * explicit user preference because it matches the current playback
 * environment, such as chosen system language.
 *
 * FORCED
 *
 * The value is an enumerated-string; valid strings are YES and NO.
 * This attribute is OPTIONAL.  Its absence indicates an implicit value
 * of NO.  The FORCED attribute MUST NOT be present unless the TYPE is
 * SUBTITLES.
 *
 * A value of YES indicates that the rendition contains content which is
 * considered essential to play.  When selecting a FORCED rendition, a
 * client should choose the one that best matches the current playback
 * environment (e.g. language).
 *
 * A value of NO indicates that the rendition contains content which is
 * intended to be played in response to explicit user request.
 *
 * INSTREAM-ID
 *
 * The value is a quoted-string that specifies a rendition within the
 * segments in the Media Playlist.  This attribute is REQUIRED if the
 * TYPE attribute is CLOSED-CAPTIONS, in which case it MUST have one of
 * the values: "CC1", "CC2", "CC3", "CC4".  For all other TYPE values,
 * the INSTREAM-ID SHOULD NOT be specified.
 *
 * CHARACTERISTICS
 *
 * The value is a quoted-string containing one or more Uniform Type
 * Identifiers [UTI] separated by comma (,) characters.  This attribute
 * is OPTIONAL.  Each UTI indicates an individual characteristic of the
 * rendition.
 *
 * A SUBTITLES rendition MAY include the following characteristics:
 * "public.accessibility.transcribes-spoken-dialog";
 * "public.accessibility.describes-music-and-sound"; "public.easy-to-
 * read" (which indicates that the subtitles have been edited for ease
 * of reading).
 *
 * An AUDIO rendition MAY include the following characteristics:
 * "public.accessibility.describes-video".
 *
 * The CHARACTERISTICS attribute MAY include private UTIs.
 *
 * The EXT-X-MEDIA tag appeared in version 4 of the protocol.  The EXT-
 * X-MEDIA tag MUST NOT appear in a Media Playlist.
 * </pre>
 */
public class Media extends Tag {

    private static final String TYPE = "TYPE";
    private static final String URI = "URI";
    private static final String GROUPID = "GROUP-ID";
    private static final String LANGUAGE = "LANGUAGE";
    private static final String ASSOCLANGUAGE = "ASSOC-LANGUAGE";
    private static final String NAME = "NAME";
    private static final String DEFAULT = "DEFAULT";
    private static final String AUTOSELECT = "AUTOSELECT";
    private static final String FORCED = "FORCED";
    private static final String INSTREAMID = "INSTREAM-ID";
    private static final String CHARACTERISTICS = "CHARACTERISTICS";

    /**
     * Returns the type attribute.
     * @return type attribute
     */
    public String getType() {
        return tag.getAttributes().get(TYPE);
    }

    /**
     * Returns the URI attribute.
     * @return URI attribute
     */
    public String getURI() {
        return tag.getAttributes().get(URI);
    }

    /**
     * Returns the group ID attribute.
     * @return group ID attribute
     */
    public String getGroupId() {
        return tag.getAttributes().get(GROUPID);
    }

    /**
     * Returns the language attribute.
     * @return language attribute
     */
    public String getLanguage() {
        return tag.getAttributes().get(LANGUAGE);
    }

    /**
     * Returns the associated language attribute.
     * @return associated language attribute
     */
    public String getAssocLanguage() {
        return tag.getAttributes().get(ASSOCLANGUAGE);
    }

    /**
     * Returns the name attribute.
     * @return name attribute
     */
    public String getName() {
        return tag.getAttributes().get(NAME);
    }

    /**
     * Returns the default attribute flag as boolean.
     * @return default attribute flag
     */
    public boolean getDefault() {
        return yesNoBoolean(tag.getAttributes().get(DEFAULT));
    }

    /**
     * Returns the auto select attribute flag as boolean.
     * @return auto select attribute flag
     */
    public boolean getAutoSelect() {
        return yesNoBoolean(tag.getAttributes().get(AUTOSELECT));
    }

    /**
     * Returns the forced attribute flag as boolean.
     * @return forced attribute flag
     */
    public boolean getForced() {
        return yesNoBoolean(tag.getAttributes().get(FORCED));
    }

    /**
     * Returns the instream ID attribute.
     * @return instream ID attribute
     */
    public String getInstreamId() {
        return tag.getAttributes().get(INSTREAMID);
    }

    /**
     * Returns the characteristics attribute.
     * @return characteristics attribute
     */
    public String getCharacteristics() {
        return tag.getAttributes().get(CHARACTERISTICS);
    }
}
