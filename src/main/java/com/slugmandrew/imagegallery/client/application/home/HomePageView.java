package com.slugmandrew.imagegallery.client.application.home;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.slugmandrew.imagegallery.shared.LoginInfo;

class HomePageView extends ViewImpl implements HomePagePresenter.MyView
{
	interface Binder extends UiBinder<Widget, HomePageView>
	{
	}
	
	@UiField
	SimplePanel uploadPanel, photoSharing, uploadedImage, galleryPanel;
	
	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginLabel = new Label("Sign in to upload images!");
	private Anchor signInLink = new Anchor("Sign In");
	
	@Inject
	HomePageView(Binder uiBinder)
	{
		initWidget(uiBinder.createAndBindUi(this));
		
	}

	
	@Override
	public void initLoginWidget(LoginInfo loginInfo)
	{
		// Assemble login panel.
		signInLink.setHref(loginInfo.getLoginUrl());
		loginPanel.add(loginLabel);
		loginPanel.add(signInLink);
		photoSharing.setWidget(loginPanel);
	}
	
	@Override
	public void setInSlot(Object slot, IsWidget content)
	{
		if(slot == HomePagePresenter.SLOT_UPLOAD)
		{
			uploadPanel.setWidget(content);
		}
		else if(slot == HomePagePresenter.SLOT_GALLERY)
		{
			galleryPanel.setWidget(content);
		}
		else
		{
			super.setInSlot(slot, content);
		}
	}
	
}
