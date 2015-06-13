package com.slugmandrew.imagegallery.client.gin;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Bootstrapper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.slugmandrew.imagegallery.client.dispatch.GetLoginInfo;
import com.slugmandrew.imagegallery.client.dispatch.GetLoginInfoResult;
import com.slugmandrew.imagegallery.client.dispatch.Initialize;
import com.slugmandrew.imagegallery.client.dispatch.InitializeResult;
import com.slugmandrew.imagegallery.shared.LoginInfo;

public class BootstrapperImpl implements Bootstrapper
{
	private final PlaceManager placeManager;
	private final DispatchAsync dispatchAsync;
	private final LoginInfo loginInfo;
	
	@Inject
	public BootstrapperImpl(PlaceManager placeManager, DispatchAsync dispatchAsync, LoginInfo loginInfo)
	{
		this.placeManager = placeManager;
		this.dispatchAsync = dispatchAsync;
		this.loginInfo = loginInfo;
	}
	
	@Override
	public void onBootstrap()
	{
		Log.info("Initializing...");
		
		dispatchAsync.execute(new Initialize(), new AsyncCallback<InitializeResult>()
		{
			@Override
			public void onSuccess(InitializeResult result)
			{
				Log.info("Initialized!");
				
				// login
				fetchCurrentUser();
			}
			
			@Override
			public void onFailure(Throwable caught)
			{
				Log.fatal("Initialize() onFailure:", caught);
				Window.alert("Initialization failed... please refresh the page to try again.");
			}
		});
		
	}
	
	void fetchCurrentUser()
	{
		dispatchAsync.execute(new GetLoginInfo(), new AsyncCallback<GetLoginInfoResult>()
		{
			@Override
			public void onSuccess(GetLoginInfoResult result)
			{
				loginInfo.copyFrom(result.getLoginInfo());
				Log.info("BootstrapperImpl.fetchCurrentUser().new AsyncCallback() {...} -> onSuccess() loginInfo: " + loginInfo);
				placeManager.revealCurrentPlace();
			}
			
			@Override
			public void onFailure(Throwable caught)
			{
				Log.fatal("GetLoginInfo() onFailure:", caught);
				Window.alert("Initialization failed... please refresh the page to try again.");
				placeManager.revealCurrentPlace();
			}
			
		});
	}
	
}
