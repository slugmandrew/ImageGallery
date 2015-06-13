package com.slugmandrew.imagegallery.shared;

import java.io.Serializable;

import com.google.common.base.Objects;

@SuppressWarnings("serial")
public class LoginInfo implements Serializable
{
	private boolean loggedIn = false;
	private String loginUrl;
	private String logoutUrl;
	private String email;
	private String nickname;
	private String id;
	
	
	public void copyFrom(LoginInfo loginInfo)
	{
		loggedIn = loginInfo.loggedIn;
		id = loginInfo.id;
		logoutUrl = loginInfo.logoutUrl;
		loginUrl = loginInfo.loginUrl;
		nickname = loginInfo.nickname;
		email = loginInfo.email;
	}
	
	
	public boolean isLoggedIn()
	{
		return loggedIn;
	}
	
	public String getId()
	{
		return id;
	}
	
	public void setId(String id)
	{
		this.id = id;
	}
	
	public void setLoggedIn(boolean loggedIn)
	{
		this.loggedIn = loggedIn;
	}
	
	public String getLoginUrl()
	{
		return loginUrl;
	}
	
	public void setLoginUrl(String loginUrl)
	{
		this.loginUrl = loginUrl;
	}
	
	public String getLogoutUrl()
	{
		return logoutUrl;
	}
	
	public void setLogoutUrl(String logoutUrl)
	{
		this.logoutUrl = logoutUrl;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getNickname()
	{
		return nickname;
	}
	
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}
	
	@Override
	public String toString()
	{
		return Objects.toStringHelper(this)
				.add("loggedIn", loggedIn)
				.add("loginUrl", loginUrl)
				.add("logoutUrl", logoutUrl)
				.add("emailAddress", email)
				.add("nickname", nickname)
				.add("id", id)
				.toString();
	}


}
