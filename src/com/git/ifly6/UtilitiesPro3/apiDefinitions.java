package com.git.ifly6.UtilitiesPro3;

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
	public String getPluginName();

	public void setInput(String input);

	public String getOutput();

	public String getLog();

	public boolean hasError();
}
