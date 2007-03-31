/* Gemi tasimaciligi yukleme, bosaltma, manifesto takip programi.
 * Copyright (C) 2006  Kod ve Us
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package net.kodveus.kumanifest.utility;

/*
 * Bare Bones Browser Launch Version 1.5 December 10, 2005 Supports: Mac OS X,
 * GNU/Linux, Unix, Windows XP Example Usage: String url =
 * "http://www.centerkey.com/"; BareBonesBrowserLaunch.openURL(url); Public
 * Domain Software -- Free to Use as You Like
 */
import java.lang.reflect.Method;

public class BareBonesBrowserLaunch {

	private static final String errMsg = "Error attempting to launch web browser";

	public static void openURL(String url) {
		String osName = System.getProperty("os.name");
		try {
			if (osName.startsWith("Mac OS")) {
				Method openURL = Class.forName("com.apple.eio.FileManager")
						.getDeclaredMethod("openURL",
								new Class[] { String.class });
				openURL.invoke(null, new Object[] { url });
			} else if (osName.startsWith("Windows"))
				Runtime.getRuntime().exec(
						"rundll32 url.dll,FileProtocolHandler " + url);
			else { // assume Unix or Linux
				String[] browsers = { "firefox", "opera", "konqueror",
						"epiphany", "mozilla", "netscape" };
				String browser = null;
				for (int count = 0; count < browsers.length && browser == null; count++)
					if (Runtime.getRuntime().exec(
							new String[] { "which", browsers[count] })
							.waitFor() == 0)
						browser = browsers[count];
				if (browser == null)
					throw new Exception("Could not find web browser");
				Runtime.getRuntime().exec(new String[] { browser, url });
			}
		} catch (Exception e) {
			StatusHelper.getInstance().mesajGoster(errMsg + ":\n"
					+ e.getLocalizedMessage());
			LogHelper.getInstance().exception(e);
		}
	}

}
