package com.slugmandrew.imagegallery.client.widgets;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.slugmandrew.imagegallery.client.events.GalleryUpdatedEvent;
import com.slugmandrew.imagegallery.client.events.GalleryUpdatedEventHandler;
import com.slugmandrew.imagegallery.client.services.UserImageService;
import com.slugmandrew.imagegallery.client.services.UserImageServiceAsync;
import com.slugmandrew.imagegallery.shared.LoginInfo;
import com.slugmandrew.imagegallery.shared.UploadedImage;

public class UploadPhoto extends Composite implements HasHandlers
{
	private static Binder uiBinder = GWT.create(Binder.class);
	
	UserImageServiceAsync userImageService = GWT.create(UserImageService.class);
	
	private HandlerManager handlerManager;
	
	interface Binder extends UiBinder<Widget, UploadPhoto>
	{
	}
	
	@UiField
	Button uploadButton;
	
	@UiField
	FormPanel uploadForm;
	
	@UiField
	FileUpload uploadField;
	
	LoginInfo loginInfo;
	
	public UploadPhoto(final LoginInfo loginInfo)
	{
		handlerManager = new HandlerManager(this);
		
		this.loginInfo = loginInfo;
		
		initWidget(uiBinder.createAndBindUi(this));
		
		uploadButton.setText("Loading...");
		uploadButton.setEnabled(false);
		
		uploadField.setName("image");
		
		startNewBlobstoreSession();
		
		uploadForm.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler()
		{
			@Override
			public void onSubmitComplete(SubmitCompleteEvent event)
			{
				uploadForm.reset();
				
				startNewBlobstoreSession();
				
				String key = event.getResults();
				
				Log.info("UploadPhoto.UploadPhoto(...).new SubmitCompleteHandler() {...} -> onSubmitComplete() event: " + event);
				Log.info("UploadPhoto.UploadPhoto(...).new SubmitCompleteHandler() {...} -> onSubmitComplete() key: " + key);
				
				userImageService.get(key, new AsyncCallback<UploadedImage>()
				{
					@Override
					public void onFailure(Throwable caught)
					{
						Log.error("UploadPhoto.onSubmitComplete(...) onFailure()", caught);
					}
					
					@Override
					public void onSuccess(UploadedImage result)
					{
						ImageOverlay overlay = new ImageOverlay(result, loginInfo);
						GalleryUpdatedEvent event = new GalleryUpdatedEvent();
						fireEvent(event);
						
						// TODO: Add something here that says,
						// hey, upload succeeded
						
						final PopupPanel imagePopup = new PopupPanel(true);
						imagePopup.setAnimationEnabled(true);
						imagePopup.setWidget(overlay);
						imagePopup.setGlassEnabled(true);
						imagePopup.setAutoHideEnabled(true);
						
						imagePopup.center();
						
					}
				});
				
			}
		});
	}
	
	private void startNewBlobstoreSession()
	{
		userImageService.getBlobstoreUploadUrl(new AsyncCallback<String>()
		{
			@Override
			public void onSuccess(String result)
			{
				result = result.replace("Drew-i7-PC", "localhost");
				
				Log.info("UploadPhoto.startNewBlobstoreSession().new AsyncCallback() {...} -> onSuccess() result: " + result);
				
				uploadForm.setAction(result);
				uploadForm.setEncoding(FormPanel.ENCODING_MULTIPART);
				uploadForm.setMethod(FormPanel.METHOD_POST);
				
				uploadButton.setText("Upload");
				uploadButton.setEnabled(true);
				
			}
			
			@Override
			public void onFailure(Throwable caught)
			{
				Log.error("UploadPhoto.startNewBlobstoreSession().new AsyncCallback() {...} -> onFailure()", caught);
			}
		});
	}
	
	@UiHandler("uploadButton")
	void onSubmit(ClickEvent e)
	{
		uploadForm.submit();
	}
	
	@Override
	public void fireEvent(GwtEvent<?> event)
	{
		handlerManager.fireEvent(event);
	}
	
	public HandlerRegistration addGalleryUpdatedEventHandler(GalleryUpdatedEventHandler handler)
	{
		return handlerManager.addHandler(GalleryUpdatedEvent.TYPE, handler);
	}
}
