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
package com.comcast.viper.hlsparserj;

import java.net.SocketTimeoutException;
import java.net.URL;

import org.apache.http.conn.ConnectTimeoutException;
import org.apache.wink.client.MockHttpServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HttpConnectionTimeoutTest {

    private MockHttpServer mockServer;

    @Before
    public void before() {
        mockServer = new MockHttpServer(0);
        mockServer.startServer();
    }

    @After
    public void after() {
        mockServer.stopServer();
    }

    @Test(expected=ConnectTimeoutException.class)
    public void testConnectionTimeout() throws Exception {

        // Scenario:  To emulate connection timeout, simply connect to a non-routable IP address
        Exception exceptionCaught = null;
        final int connectTimeout = 1000;

        // Create a non-routable URL
        final String url = "http://10.255.255.1:" + mockServer.getServerPort() + "/something.m3u8";

        // Stop the server to simulate connection timeout
        mockServer.stopServer();

        long start = System.currentTimeMillis();
        try {
            PlaylistFactory.parsePlaylist(PlaylistVersion.TWELVE, new URL(url), connectTimeout, 0, 0);
        } catch (Exception ex) {
            exceptionCaught = ex;
        }

        // Verify that timeout occurred within 5 seconds (5 * connectTimeout)
        Assert.assertTrue(System.currentTimeMillis() - start < 5000);
        throw exceptionCaught;
    }

    @Test(expected=SocketTimeoutException.class)
    public void testSocketTimeout() throws Exception {

        // Scenario: To emulate socket timeout, simply tell the server to delay the response
        Exception exceptionCaught = null;

        final String url = "http://127.0.0.1:" + mockServer.getServerPort() + "/something.m3u8";

        // Instruct mock server to delay response to emulate socket timeout
        mockServer.setDelayResponse(5000);

        long start = System.currentTimeMillis();
        try {
            PlaylistFactory.parsePlaylist(PlaylistVersion.TWELVE, new URL(url), 0, 0, 1000);
        } catch (Exception ex) {
            exceptionCaught = ex;
        }

        // Verify that socket timeout occurred within 3 seconds
        Assert.assertTrue(System.currentTimeMillis() - start < 3000);

        throw exceptionCaught;
    }
}
