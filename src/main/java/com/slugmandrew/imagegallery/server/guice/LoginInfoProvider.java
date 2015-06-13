package com.slugmandrew.imagegallery.server.guice;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.inject.Provider;
import com.slugmandrew.imagegallery.shared.LoginInfo;

public class LoginInfoProvider implements Provider<LoginInfo>
{

	private UserService userService = UserServiceFactory.getUserService();

	@Override
	public LoginInfo get()
	{
		User user = userService.getCurrentUser();
		LoginInfo loginInfo = new LoginInfo();
		
		if(user != null)
		{
			loginInfo.setLoggedIn(true);
			loginInfo.setEmail(user.getEmail());
			loginInfo.setNickname(user.getNickname());
			loginInfo.setLogoutUrl(userService.createLogoutURL("/"));
			loginInfo.setId(user.getUserId());
		}
		else
		{
			loginInfo.setLoggedIn(false);
			loginInfo.setLoginUrl(userService.createLoginURL("/"));
		}
		return loginInfo;
	}
	
}
