package com.slugmandrew.imagegallery.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.slugmandrew.imagegallery.shared.LoginInfo;

public interface LoginServiceAsync
{
	public void login(String requestUri, AsyncCallback<LoginInfo> async);
}
