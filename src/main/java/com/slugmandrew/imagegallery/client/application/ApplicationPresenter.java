package com.slugmandrew.imagegallery.client.application;

import javax.inject.Inject;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.slugmandrew.imagegallery.client.application.imagepopup.ImagePopupPresenter;
import com.slugmandrew.imagegallery.client.events.ViewImageEvent;
import com.slugmandrew.imagegallery.client.events.ViewImageEvent.ViewImageHandler;

public class ApplicationPresenter extends Presenter<ApplicationPresenter.MyView, ApplicationPresenter.MyProxy> implements ViewImageHandler
{
	interface MyView extends View
	{
	}
	
	@ContentSlot
	public static final Type<RevealContentHandler<?>> SLOT_SetMainContent = new Type<>();
	
	@ProxyStandard
	interface MyProxy extends Proxy<ApplicationPresenter>
	{
	}
	
	private ImagePopupPresenter imagePopupPresenter;
	
	@Inject
	ApplicationPresenter(EventBus eventBus, MyView view, MyProxy proxy, ImagePopupPresenter imagePopupPresenter)
	{
		super(eventBus, view, proxy, RevealType.Root);
		
		this.imagePopupPresenter = imagePopupPresenter;
		
	}
	
	@Override
	protected void onBind()
	{
		super.onBind();
		
		addRegisteredHandler(ViewImageEvent.getType(), this);
	}
	
	@Override
	public void onViewImage(ViewImageEvent event)
	{
		imagePopupPresenter.initialize(event.getImage());
		addToPopupSlot(imagePopupPresenter);
	}
}
