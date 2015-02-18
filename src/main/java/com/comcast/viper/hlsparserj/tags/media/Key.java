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
package com.comcast.viper.hlsparserj.tags.media;

import com.comcast.viper.hlsparserj.tags.Tag;

/**
 * <pre>
 * Represents the EXT-X-KEY tag.
 *
 * Media segments MAY be encrypted.  The EXT-X-KEY tag specifies how to
 * decrypt them.  It applies to every media segment that appears between
 * it and the next EXT-X-KEY tag in the Playlist file with the same
 * KEYFORMAT attribute (or the end of the Playlist file).  Two or more
 * EXT-X-KEY tags with different KEYFORMAT attributes MAY apply to the
 * same media segment, in which case they MUST resolve to the same key.
 * Its format is:
 *
 *   #EXT-X-KEY:&lt;attribute-list&gt;
 *
 * The following attributes are defined:
 *
 * METHOD
 *
 * The value is an enumerated-string that specifies the encryption
 * method.  This attribute is REQUIRED.
 *
 * The methods defined are: NONE, AES-128, and SAMPLE-AES.
 *
 * An encryption method of NONE means that media segments are not
 * encrypted.  If the encryption method is NONE, the following
 * attributes MUST NOT be present: URI; IV; KEYFORMAT;
 * KEYFORMATVERSIONS.
 *
 * An encryption method of AES-128 means that media segments are
 * completely encrypted using the Advanced Encryption Standard [AES_128]
 * with a 128-bit key and PKCS7 padding [RFC5652].  If the encryption
 * method is AES-128, the URI attribute MUST be present.  The IV
 * attribute MAY be present; see Section 5.2.
 *
 * An encryption method of SAMPLE-AES means that the media segments
 * contain elementary streams of audio, video, or other samples that are
 * encrypted using the Advanced Encryption Standard [AES_128].  How an
 * elementary stream is encrypted depends on the media encoding.  The
 * encryption format for H.264 [H_264], AAC [ISO_14496] and AC-3 [AC_3]
 * elementary streams is described by [SampleEnc].  The IV attribute MAY
 * be present; see Section 5.2.
 *
 * A client MUST NOT not attempt to decrypt any segments whose EXT-X-KEY
 * tag has a METHOD attribute that it does not recognize.
 *
 * URI
 *
 * The value is a quoted-string containing a URI [RFC3986] that
 * specifies how to obtain the key.  This attribute is REQUIRED unless
 * the METHOD is NONE.
 *
 * IV
 *
 * The value is a hexadecimal-integer that specifies the Initialization
 * Vector to be used with the key.  The IV attribute appeared in
 * protocol version 2.  See Section 5.2 for when the IV attribute is
 * used.
 *
 * KEYFORMAT
 *
 * The value is a quoted-string that specifies how the key is
 * represented in the resource identified by the URI; see Section 5 for
 * more detail.  This attribute is OPTIONAL; its absence indicates, an
 * implicit value of "identity".  The KEYFORMAT attribute appeared in
 * protocol version 5.
 *
 * KEYFORMATVERSIONS
 *
 * The value is a quoted-string containing one or more positive integers
 * separated by the "/" character (for example, "1/3").  If more than
 * one version of a particular KEYFORMAT is defined, this attribute can
 * be used to indicate which version(s) this instance complies with.
 * This attribute is OPTIONAL; if it is not present, its value is
 * considered to be "1".  The KEYFORMATVERSIONS attribute appeared in
 * protocol version 5.
 *
 * If the Media Playlist file does not contain an EXT-X-KEY tag then
 * media segments are not encrypted.
 *
 * See Section 5 for the format of the key file, and Section 5.2,
 * Section 6.2.3 and Section 6.3.6 for additional information on media
 * segment encryption.
 * </pre>
 */
public class Key extends Tag {

    private static final String METHOD = "METHOD";
    private static final String URI = "URI";
    private static final String IV = "IV";
    private static final String KEYFORMAT = "KEYFORMAT";
    private static final String KEYFORMATVERSIONS = "KEYFORMATVERSIONS";

    /**
     * Returns the method attribute value.  Possible values:
     *   - NONE
     *   - AES-128
     *   - SAMPLE-AES
     * @return method value
     */
    public String getMethod() {
        return tag.getAttributes().get(METHOD);
    }

    /**
     * Returns the URI attribute value.  The value is a quoted-string containing
     * a URI [RFC3986] that specifies how to obtain the key.  This attribute is
     * REQUIRED unless the METHOD is NONE.
     * @return URI
     */
    public String getURI() {
        return tag.getAttributes().get(URI);
    }

    /**
     * Returns the IV attribute value.  The value is a hexadecimal-integer that
     * specifies the Initialization Vector to be used with the key.  The IV
     * attribute appeared in protocol version 2.
     * @return IV
     */
    public String getIV() {
        return tag.getAttributes().get(IV);
    }

    /**
     * Returns the key format attribute value.  The value is a quoted-string
     * that specifies how the key is represented in the resource identified by
     * the URI.  This attribute is OPTIONAL; its absence indicates, an implicit
     * value of "identity".  The KEYFORMAT attribute appeared in protocol version 5.
     * @return key format
     */
    public String getKeyFormat() {
        return tag.getAttributes().get(KEYFORMAT);
    }

    /**
     * Returns they key format versions attribute value.  The value is a quoted-string
     * containing one or more positive integers separated by the "/" character
     * (for example, "1/3").  If more than one version of a particular KEYFORMAT is
     * defined, this attribute can be used to indicate which version(s) this instance
     * complies with.  This attribute is OPTIONAL; if it is not present, its value is
     * considered to be "1".  The KEYFORMATVERSIONS attribute appeared in protocol version 5.
     * @return key format versions
     */
    public String getKeyFormatVersions() {
        return tag.getAttributes().get(KEYFORMATVERSIONS);
    }
}
