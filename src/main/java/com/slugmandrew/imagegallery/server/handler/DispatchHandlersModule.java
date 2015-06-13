package com.slugmandrew.imagegallery.server.handler;

import com.google.inject.Singleton;
import com.googlecode.objectify.ObjectifyFilter;
import com.gwtplatform.dispatch.rpc.server.guice.HandlerModule;
import com.slugmandrew.imagegallery.client.dispatch.GetLoginInfo;
import com.slugmandrew.imagegallery.client.dispatch.Initialize;

public class DispatchHandlersModule extends HandlerModule
{
	@Override
	protected void configureHandlers()
	{
		bind(ObjectifyFilter.class).in(Singleton.class);
		
		// init
		bindHandler(Initialize.class, InitializeHandler.class);
		
		// get
		bindHandler(GetLoginInfo.class, GetLoginInfoHandler.class);
		
	}
	
}
