package com.me.ifly6.plugin;

import com.me.ifly6.API;
import com.me.ifly6.Console;

public class PluginSample implements API{

	@Override
	public boolean blockCommandPassthrough() {return true;}

	@Override
	public int execute(String[] command, Console c) {
		c.output.append("\nyo");
		return 0;
	}

	@Override
	public String claimedCommand() {
		return "Hi";
	}

}
