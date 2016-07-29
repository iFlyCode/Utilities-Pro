/* Copyright (c) 2016 Kevin Wong
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
package ifly6.utilitiesPro4.config;

/**
 * @author Kevin
 *
 */
public class UPVersion {

	public int		major;
	public int		minor;
	public int		revision;
	public boolean	dev;

	public UPVersion(int major, int minor, int revision, boolean dev) {
		
		this.major = major;
		this.minor = minor;
		this.revision = revision;
		this.dev = dev;
		
	}

	public boolean isNewerThan(UPVersion obj) {

		if (this.equals(obj)) { return false; }

		// Then, check major, minor, dev, and revision flags.
		if (this.major > obj.major) {
			return true;

		} else if (this.major < obj.major) {
			return false;

		} else {

			if (this.minor > obj.minor) {
				return true;

			} else if (this.minor < obj.minor) {
				return false;

			} else {

				if ((this.dev == false) && (obj.dev == true)) {
					return true;

				} else if ((this.dev == true) && (obj.dev == true)) {
					return false;

				} else {

					if (this.revision > obj.revision) {
						return true;

					} else if (this.revision < obj.revision) {
						return false;

					} else {
						return false;
					}

				}
			}
		}
	}
	
	public boolean equals(UPVersion obj) {
		return obj.toString().equals(this.toString());
	}
	
	@Override public String toString() {
		return major + "." + minor + ((revision == 0) ? "" : "_" + (dev ? "dev" : "") + revision);
	}
	
}
