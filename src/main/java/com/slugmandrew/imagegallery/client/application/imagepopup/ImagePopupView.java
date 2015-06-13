package com.slugmandrew.imagegallery.client.application.imagepopup;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PopupViewWithUiHandlers;
import com.slugmandrew.imagegallery.shared.LoginInfo;
import com.slugmandrew.imagegallery.shared.UploadedImage;

class ImagePopupView extends PopupViewWithUiHandlers<ImagePopupUiHandlers> implements ImagePopupPresenter.MyView
{
	
	interface Binder extends UiBinder<Widget, ImagePopupView>
	{
	}
	
	@UiField
	Button deleteButton;
	
	@UiField
	Image image;
	
	@UiField
	Label timestamp;
	
	private LoginInfo loginInfo;
	
	@Inject
	ImagePopupView(EventBus eventBus, Binder uiBinder, LoginInfo loginInfo)
	{
		super(eventBus);
		
		this.loginInfo = loginInfo;
		
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@Override
	public void setInfo(UploadedImage uploadedImage)
	{
		image.setUrl(uploadedImage.getServingUrl());
		timestamp.setText("Created at:" + uploadedImage.getCreatedAt());
		
		if(loginInfo != null && (loginInfo.getId().equals(uploadedImage.getOwnerId())))
		{
			deleteButton.setText("Delete image");
			deleteButton.setVisible(true);
		}
		else
		{
			deleteButton.setVisible(false);
		}
		
	}
	
}
