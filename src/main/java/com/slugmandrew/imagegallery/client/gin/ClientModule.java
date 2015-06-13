package com.slugmandrew.imagegallery.client.gin;

import com.slugmandrew.imagegallery.client.application.ApplicationModule;
import com.slugmandrew.imagegallery.client.place.NameTokens;
import com.slugmandrew.imagegallery.shared.LoginInfo;
import com.gwtplatform.dispatch.rpc.client.gin.RpcDispatchAsyncModule;
import com.gwtplatform.dispatch.shared.SecurityCookie;
import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.annotations.ErrorPlace;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.gwtplatform.mvp.shared.proxy.RouteTokenFormatter;

/**
 * See more on setting up the PlaceManager on <a
 * href="// See more on: https://github.com/ArcBees/GWTP/wiki/PlaceManager">DefaultModule's > DefaultPlaceManager</a>
 */
public class ClientModule extends AbstractPresenterModule
{
	@Override
	protected void configure()
	{
		install(new DefaultModule());
		install(new RpcDispatchAsyncModule.Builder().build());
		install(new ApplicationModule());
		
		// security
		bindConstant().annotatedWith(SecurityCookie.class).to("JSESSIONID");
		
		// DefaultPlaceManager Places
		bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.home);
		bindConstant().annotatedWith(ErrorPlace.class).to(NameTokens.home);
		bindConstant().annotatedWith(UnauthorizedPlace.class).to(NameTokens.home);

		
		bind(LoginInfo.class).asEagerSingleton();
		
	}
}
