package com.slugmandrew.imagegallery.shared;

import java.io.Serializable;

import com.google.common.base.Objects;

public class CurrentUserDto implements Serializable
{
	private Long userId; // id of datastore GoogleUser or Contact
	private Boolean isAdmin;
	private Boolean isLoggedIn;
	private Boolean isContact;
	private String logoutUrl;
	private String loginUrl;
	private String nickname;
	private String email;
	
	// default constructor
	public CurrentUserDto()
	{
		userId = null;
		isAdmin = false;
		isLoggedIn = false;
		isContact = false;
		loginUrl = "";
		logoutUrl = "";
	}
	
	// new constructor with userId string instead of User Object
	public CurrentUserDto(Boolean isLoggedIn, Long userId, boolean isContact)
	{
		this.isLoggedIn = isLoggedIn;
		this.userId = userId;
		this.isContact = isContact;
	}
	
	@Override
	public String toString()
	{
		return Objects.toStringHelper(this)
				.add("userId", userId)
				.add("isAdmin", isAdmin)
				.add("isLoggedIn", isLoggedIn)
				.add("isContact", isContact)
				.add("logoutUrl", logoutUrl)
				.add("loginUrl", loginUrl)
				.add("nickname", nickname)
				.add("email", email)
				.toString();
	}
	
	public Long getUserId()
	{
		return userId;
	}
	
	public Boolean isLoggedIn()
	{
		return isLoggedIn;
	}
	
	public Boolean isAdmin()
	{
		return isAdmin;
	}
	
	public Boolean isContact()
	{
		return isContact;
	}
	
	public void setLogoutUrl(String logoutUrl)
	{
		this.logoutUrl = logoutUrl;
	}
	
	public String getLogoutUrl()
	{
		return logoutUrl;
	}
	
	public void setLoginUrl(String loginUrl)
	{
		this.loginUrl = loginUrl;
	}
	
	public String getLoginUrl()
	{
		return loginUrl;
	}
	
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}
	
	public String getNickname()
	{
		return nickname;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public void copyFrom(CurrentUserDto currentUser)
	{
		userId = currentUser.userId;
		isAdmin = currentUser.isAdmin;
		isLoggedIn = currentUser.isLoggedIn;
		isContact = currentUser.isContact;
		logoutUrl = currentUser.logoutUrl;
		loginUrl = currentUser.loginUrl;
		nickname = currentUser.nickname;
		email = currentUser.email;
	}
	
	public void echo()
	{
		System.out.println("userId = " + userId);
		System.out.println("isAdmin = " + isAdmin);
		System.out.println("isLoggedIn = " + isLoggedIn);
		System.out.println("isContact = " + isContact);
		System.out.println("logoutUrl = " + logoutUrl);
		System.out.println("loginUrl = " + loginUrl);
		System.out.println("nickname = " + nickname);
		System.out.println("email = " + email);
	}
	
	public void setIsAdmin(Boolean isAdmin)
	{
		this.isAdmin = isAdmin;
	}
	
	public void setIsContact(Boolean isContact)
	{
		this.isContact = isContact;
	}
	
	public void setIsLoggedIn(Boolean isLoggedIn)
	{
		this.isLoggedIn = isLoggedIn;
	}
	
}
