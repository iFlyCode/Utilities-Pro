/* Copyright (c) 2017 ifly6
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. */

package com.git.ifly6.UtilitiesPro3;

/**
 * Contains all relevant scripts to the Script menu in the programme.
 *
 * @author ifly6
 * @since 3.0 All Toolbar>Script Commands go here
 */
public class ScriptCommands extends UtilitiesPro {

	/**
	 * Method shows Finder Quit button and all hidden files.
	 *
	 * @since 3.3_dev03
	 */
	static void finderConfig(boolean status) {
		String truths = "false";
		if (status) {
			truths = "true";
		}
		System.out.println(truths);

		String[] finderHidden = { "defaults", "write", "com.apple.Finder", "AppleShowAllFiles", truths };
		String[] finderQuit = { "defaults", "write", "com.apple.Finder", "QuitMenuItem", truths };

		ExecEngine.exec(finderHidden);
		ExecEngine.exec(finderQuit);

		ExecEngine.exec("killall Finder");
	}

	/**
	 * Method runs a Runtime section to purge inactive memory. It relies on a command inside OSX, called purge.
	 *
	 * @since 2.3_dev3
	 */
	static void purge() {
		if (System.getProperty("os.name").startsWith("10.8")) {
			out("Please wait. Purging inactive memory cache...");
			ExecEngine.exec("purge");
			out("Purge Complete.");
		} else {
			out("This computer uses compressed memory and cannot be purged without super-user authorisation.");
		}
	}

	/**
	 * It turns off then 500 milliseconds later, turns on wireless adaptor (en0) for the computer. This is nearly word
	 * for word copied from iUtilities v1.0's implementation
	 *
	 * @since iUtilities v1.0
	 */
	static void wireless() {
		try {
			ExecEngine.exec("networksetup -setairportpower en1 off");
			Thread.sleep(500);
			ExecEngine.exec("networksetup -setairportpower en1 on");
		} catch (InterruptedException e) {
			log("Airport Restart Failed");
		}
		String outPut = ("Airport Connection Restarted.");
		out(outPut);
		log(outPut);
	}

}
