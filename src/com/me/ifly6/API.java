package com.me.ifly6;

public interface API {

	/**
	 * @return whether the plugin should let the command through to the OS after execution.
	 */
	public boolean blocksCommand();
	
	/**
	 * @param command, the command with args passed to the plugin by the console.
	 * @return 0 if all went well
	 */
	public int execute(String[] command);
	
	/**
	 * 
	 * @return String array of commands the console should hand off to the plugin
	 */
	public int claimedCommands();
}
