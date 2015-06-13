package com.slugmandrew.imagegallery.client.application.imagepopup;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.slugmandrew.imagegallery.shared.UploadedImage;

public class ImagePopupPresenter extends PresenterWidget<ImagePopupPresenter.MyView> implements ImagePopupUiHandlers
{
	interface MyView extends PopupView, HasUiHandlers<ImagePopupUiHandlers>
	{
		void setInfo(UploadedImage image);
	}
	
	@Inject
	ImagePopupPresenter(EventBus eventBus, MyView view)
	{
		super(eventBus, view);
		
		getView().setUiHandlers(this);
	}
	
	public void initialize(UploadedImage uploadedImage)
	{
		getView().setInfo(uploadedImage);
		
	}
	
}
