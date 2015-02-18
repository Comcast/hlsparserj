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
 * Represents the I frame tag.
 *
 * The EXT-X-I-FRAMES-ONLY tag indicates that each media segment in the
 * Playlist describes a single I-frame.  I-frames (or Intra frames) are
 * encoded video frames whose encoding does not depend on any other
 * frame.
 *
 * The EXT-X-I-FRAMES-ONLY tag applies to the entire Playlist.  Its
 * format is:
 *
 *   #EXT-X-I-FRAMES-ONLY
 *
 * In a Playlist with the EXT-X-I-FRAMES-ONLY tag, the media segment
 * duration (EXTINF tag value) is the time between the presentation time
 * of the I-frame in the media segment and the presentation time of the
 * next I-frame in the Playlist, or the end of the presentation if it is
 * the last I-frame in the Playlist.
 *
 * Media resources containing I-frame segments MUST begin with either a
 * Transport Stream PAT/PMT or be accompanied by an EXT-X-MAP tag
 * indicating the proper PAT/PMT.  The byte range of an I-frame segment
 * with an EXT-X-BYTERANGE tag applied to it (Section 3.4.1) MUST NOT
 * include a PAT/PMT.
 *
 * The EXT-X-I-FRAMES-ONLY tag appeared in version 4 of the protocol.
 * The EXT-X-I-FRAMES-ONLY tag MUST NOT appear in a Master Playlist.
 * </pre>
 */
public class IFramesOnly extends Tag {

}
