package com.slugmandrew.imagegallery.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.slugmandrew.imagegallery.shared.LoginInfo;

@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService
{
	public LoginInfo login(String requestUri);
}
