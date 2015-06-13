package com.slugmandrew.imagegallery.client.dispatch;

import com.gwtplatform.dispatch.rpc.shared.Result;
import com.slugmandrew.imagegallery.shared.LoginInfo;

public class GetLoginInfoResult implements Result
{
	private LoginInfo loginInfo;
	
	public GetLoginInfoResult()
	{
	}
	
	public GetLoginInfoResult(LoginInfo loginInfo)
	{
		this.loginInfo = loginInfo;
	}
	
	public LoginInfo getLoginInfo()
	{
		return loginInfo;
	}
	
}
