package com.slugmandrew.imagegallery.server.handler;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;
import com.slugmandrew.imagegallery.client.dispatch.Initialize;
import com.slugmandrew.imagegallery.client.dispatch.InitializeResult;

public class InitializeHandler extends AbstractAction<Initialize, InitializeResult>
{
	
	@Inject
	public InitializeHandler()
	{
		super(Initialize.class);
		
	}
	
	@Override
	public InitializeResult execute(Initialize action, ExecutionContext context) throws ActionException
	{
		
		return new InitializeResult();
	}
	
}
