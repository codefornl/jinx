/*
 * Jinx is Copyright 2010-2014 by Jeremy Brooks and Contributors
 *
 * Jinx is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jinx is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Jinx.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.jeremybrooks.jinx.api;

import net.jeremybrooks.jinx.Jinx;
import net.jeremybrooks.jinx.OAuthAccessToken;
import net.jeremybrooks.jinx.response.auth.oauth.OAuthCredentials;
import org.junit.Test;

import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Jeremy Brooks
 */
public class OAuthApiTest {



	/**
	 * Run this test manually to get an OAuth token. It requires user input to complete, so should not be run
	 * as part of the test suite.
	 * <p/>
	 * To run this test, you must have a "secret.properties" file in the test/java/resources directory.
	 * This file must contain:
	 *
	 * path.to.oauth.token=[full path to save the oauth token data]
	 * flickr.key=[your test app key]
	 * flickr.secret=[your test app secret]
	 *
	 * If you are testing "testGetAccessToken" to convert a legacy token, the file must also contain:
	 *
	 * path.to.token=[full path to the saved legacy token data]
	 *
	 * <p/>
	 * Calling this will overwrite any existing file at path.to.auth.token, so be careful.
	 *
	 * @throws Exception
	 */
	@Test
	public void testOauth() throws Exception {
//		testGetOauthAccessToken();
//		testCheckToken();
		//testGetAccessToken();	// This will cause the legacy token to be removed from Flickr in 24 hours
	}


	private void testGetOauthAccessToken() throws Exception {
		Properties p = new Properties();
		p.load(OAuthApiTest.class.getResourceAsStream("/response/auth/secret.properties"));

		String filename = p.getProperty("path.to.oauth.token");

		Jinx jinx = new Jinx(p.getProperty("flickr.key"), p.getProperty("flickr.secret"));
		OAuthApi oAuthApi = new OAuthApi(jinx);

		String url = oAuthApi.getOAuthRequestToken(null);
		assertNotNull(url);

		System.out.println(url);
		String verificationCode = JOptionPane.showInputDialog("Authorize at \n " + url + "\nand then enter the validation code.");

		OAuthAccessToken token = oAuthApi.getOAuthAccessToken(verificationCode);
		assertNotNull(token);

		token.store(new FileOutputStream(new File(filename)));
	}


	private void testCheckToken() throws Exception {
		Properties p = new Properties();
		p.load(OAuthApiTest.class.getResourceAsStream("/response/auth/secret.properties"));

		String filename = p.getProperty("path.to.oauth.token");
		assertNotNull(filename);

		File file = new File(filename);
		assertTrue(file.exists());

		OAuthAccessToken oAuthAccessToken = new OAuthAccessToken();
		oAuthAccessToken.load(new FileInputStream(file));

		assertNotNull(oAuthAccessToken);

		Jinx jinx = new Jinx(p.getProperty("flickr.key"), p.getProperty("flickr.secret"), oAuthAccessToken);
		OAuthApi oAuthApi = new OAuthApi(jinx);

		OAuthCredentials credentials = oAuthApi.checkToken(oAuthAccessToken.getOauthToken());
		assertEquals("ok", credentials.getStat());
		assertNotNull(credentials);
		assertEquals(oAuthAccessToken.getOauthToken(), credentials.getOauthToken());
		assertEquals(oAuthAccessToken.getUsername(), credentials.getUsername());
		assertEquals(oAuthAccessToken.getNsid(), credentials.getNsid());
		assertEquals(oAuthAccessToken.getFullname(), credentials.getFullname());
		assertNotNull(credentials.getPerms());
	}


	private void testGetAccessToken() throws Exception {
		Properties p = new Properties();
		p.load(OAuthApiTest.class.getResourceAsStream("/response/auth/secret.properties"));

		String filename = p.getProperty("path.to.token");
		assertNotNull(filename);

		File file = new File(filename);
		assertTrue(file.exists());

		Jinx jinx = new Jinx(p.getProperty("flickr.key"), p.getProperty("flickr.secret"));
		OAuthApi oAuthApi = new OAuthApi(jinx);

		OAuthAccessToken oAuthAccessToken = oAuthApi.getAccessToken(new FileInputStream(file));
		assertNotNull(oAuthAccessToken);
	}
}
