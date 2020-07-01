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

package ifly6.UtilitiesPro3;

/**
 * This defines the interface and the methods which must be contained. getPluginName() is self-explanatory. setInput()
 * is the input string into the plugin. The plugin should be able to do something with that. getOutput() is the String
 * which the plugin gives. If there is a problem, say so using the hasError() method, then force a call of the getLog()
 * method.
 *
 * @author ifly6
 * @since 3.3_dev04
 */
public interface apiDefinitions {

	/**
	 * Defines the name of the plugin. Note that the plugin name cannot be 'list', or it will be excluded from an
	 * attempt to run by the program.
	 *
	 * @since 3.3_dev04
	 **/
	public String getPluginName();

	/**
	 * Gives an input to the plugin. The plugin will then call the input from this string and use it as the primary form
	 * of operation. Naturally, it shall execute inside a sandbox.
	 *
	 * @since 3.3_dev04
	 **/
	public void setInput(String input);

	/**
	 * The plugin should route all of its output into a string. That string should be accessible by the method
	 * getOutput. getOutput should also be the execution thread of the programme, as this is more in line with an idea
	 * of lazy-evaluation, lowering the amount of work necessary in the programme. getOutput as a execution thread helps
	 * a lot in keeping the plugin under control.
	 *
	 * @since 3.3_dev04
	 **/
	public String getOutput();

	/**
	 * In the same way as getOutput(), another string should contain an error log of all errors thrown by the
	 * application. It should then route all those errors into a string which is accessible by getLog().
	 *
	 * @since 3.3_dev04
	 **/
	public String getLog();

	/**
	 * This flag tells us whether there is an error. A boolean regarding errors should be set, and if an error is
	 * present, it should be shown by a 'true' on this flag.
	 *
	 * @since 3.3_dev04
	 **/
	public boolean hasError();
}
