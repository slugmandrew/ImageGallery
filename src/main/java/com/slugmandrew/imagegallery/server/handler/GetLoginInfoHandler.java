package com.slugmandrew.imagegallery.server.handler;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;
import com.slugmandrew.imagegallery.client.dispatch.GetLoginInfo;
import com.slugmandrew.imagegallery.client.dispatch.GetLoginInfoResult;
import com.slugmandrew.imagegallery.server.guice.LoginInfoProvider;
import com.slugmandrew.imagegallery.shared.LoginInfo;

public class GetLoginInfoHandler extends AbstractAction<GetLoginInfo, GetLoginInfoResult>
{
	private LoginInfoProvider loginInfoProvider;
	
	@Inject
	public GetLoginInfoHandler(LoginInfoProvider loginInfoProvider)
	{
		super(GetLoginInfo.class);
		
		this.loginInfoProvider = loginInfoProvider;
	}
	
	@Override
	public GetLoginInfoResult execute(GetLoginInfo action, ExecutionContext context) throws ActionException
	{
		LoginInfo loginInfo = loginInfoProvider.get();
		
		return new GetLoginInfoResult(loginInfo);
	}
	
}
