package com.slugmandrew.imagegallery.server.guice;

import javax.inject.Singleton;

import com.google.inject.servlet.RequestScoped;
import com.gwtplatform.dispatch.rpc.server.guice.HandlerModule;
import com.slugmandrew.imagegallery.server.handler.DispatchHandlersModule;
import com.slugmandrew.imagegallery.shared.LoginInfo;

public class ServerModule extends HandlerModule
{
	@Override
	protected void configureHandlers()
	{
		install(new DispatchHandlersModule());
		
		bind(LoginInfoProvider.class).in(Singleton.class);
		
		// bind the CurrentUserDto to the provider so the provider is used to get the CurrentUserDto when we need it injected somewhere (I think)
		bind(LoginInfo.class).toProvider(LoginInfoProvider.class).in(RequestScoped.class);
	}
}
