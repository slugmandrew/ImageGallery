package com.slugmandrew.imagegallery.client.dispatch;

import com.gwtplatform.dispatch.rpc.shared.ActionImpl;

/**
 * This is a dummy action that starts up the server
 * 
 * @author Drew
 * 
 */
public class Initialize extends ActionImpl<InitializeResult>
{
	
	public Initialize()
	{
	}
	
	@Override
	public boolean isSecured()
	{
		return false;
	}
	
}
