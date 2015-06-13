package com.slugmandrew.imagegallery.client.application.home;

import com.allen_sauer.gwt.log.client.Log;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.slugmandrew.imagegallery.client.application.ApplicationPresenter;
import com.slugmandrew.imagegallery.client.application.gallery.GalleryPresenter;
import com.slugmandrew.imagegallery.client.application.upload.UploadPresenter;
import com.slugmandrew.imagegallery.client.place.NameTokens;
import com.slugmandrew.imagegallery.shared.LoginInfo;

public class HomePagePresenter extends Presenter<HomePagePresenter.MyView, HomePagePresenter.MyProxy>
{
	public static Object SLOT_UPLOAD = new Object();
	public static Object SLOT_GALLERY = new Object();
	
	interface MyView extends View
	{
		// void initUploadWidget(LoginInfo loginInfo);
		void initLoginWidget(LoginInfo loginInfo);
	}
	
	@ProxyStandard
	@NameToken(NameTokens.home)
	interface MyProxy extends ProxyPlace<HomePagePresenter>
	{
	}
	
	private LoginInfo loginInfo;
	private UploadPresenter uploadPresenter;
	private GalleryPresenter galleryPresenter;
	
	@Inject
	HomePagePresenter(EventBus eventBus, MyView view, MyProxy proxy, LoginInfo loginInfo, UploadPresenter uploadPresenter, GalleryPresenter galleryPresenter)
	{
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_SetMainContent);
		
		this.loginInfo = loginInfo;
		this.uploadPresenter = uploadPresenter;
		this.galleryPresenter = galleryPresenter;
	}
	
	@Override
	protected void onReveal()
	{
		super.onReveal();
		
		Log.info("HomePagePresenter -> onReveal() loginInfo: " + loginInfo);
		
		if(loginInfo.isLoggedIn())
		{
			setInSlot(SLOT_UPLOAD, uploadPresenter);
		}
		else
		{
			getView().initLoginWidget(loginInfo);
		}
		
		setInSlot(SLOT_GALLERY, galleryPresenter);
	}
	
}
