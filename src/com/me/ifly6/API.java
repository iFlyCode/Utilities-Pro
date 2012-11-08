package com.me.ifly6;

public interface API {

	/**
	 * @return whether the plugin should let the command through to the OS after execution.
	 */
	public boolean blockCommandPassthrough();
	
	/**
	 * @param command: The command with args passed to the plugin by the console.
	 * @param c: The instance of console that the plugin is being called from.
	 * @return 0 if all went well
	 */
	public int execute(String[] command, Console c);
	
	/**
	 * @return Command the console should hand off to the plugin.
	 */
	public String claimedCommand();
}
