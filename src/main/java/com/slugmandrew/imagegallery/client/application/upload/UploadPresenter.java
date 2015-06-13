package com.slugmandrew.imagegallery.client.application.upload;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.slugmandrew.imagegallery.client.dispatch.AsyncCallbackImpl;
import com.slugmandrew.imagegallery.client.events.GalleryUpdatedEvent;
import com.slugmandrew.imagegallery.client.services.UserImageServiceAsync;
import com.slugmandrew.imagegallery.client.widgets.ImageOverlay;
import com.slugmandrew.imagegallery.shared.LoginInfo;
import com.slugmandrew.imagegallery.shared.UploadedImage;

public class UploadPresenter extends PresenterWidget<UploadPresenter.MyView> implements UploadUiHandlers
{
	interface MyView extends View, HasUiHandlers<UploadUiHandlers>
	{
		void addFormSubmitCompleteHandler(SubmitCompleteHandler handler); // tested
		void addChangeHandler(ChangeHandler changeHandler); // tested
		
		void setFormMeta(String result);
		void submitAndReset();
	}
	
	private UserImageServiceAsync userImageService;
	private LoginInfo loginInfo;
	
	@Inject
	UploadPresenter(EventBus eventBus, MyView view, UserImageServiceAsync userImageService, LoginInfo loginInfo)
	{
		super(eventBus, view);
		this.userImageService = userImageService;
		this.loginInfo = loginInfo;
		
		getView().setUiHandlers(this);
	}
	
	protected void onBind()
	{
		super.onBind();
		
		getView().addFormSubmitCompleteHandler(submitCompleteHandler);
		getView().addChangeHandler(changeHandler);
		
	};
	
	protected SubmitCompleteHandler submitCompleteHandler = new SubmitCompleteHandler()
	{
		@Override
		public void onSubmitComplete(SubmitCompleteEvent event)
		{
			if(event.getResults() != null)
			{
				String results = event.getResults();
				
				Log.info("submitComplete results: " + results);
				
				Window.alert("Upload Successful!");
				
				// userImageService.get(results, new AsyncCallback<UploadedImage>()
				// {
				// @Override
				// public void onFailure(Throwable caught)
				// {
				// Log.error("UploadPhoto.onSubmitComplete(...) onFailure()", caught);
				// }
				//
				// @Override
				// public void onSuccess(UploadedImage result)
				// {
				// ImageOverlay overlay = new ImageOverlay(result, loginInfo);
				// GalleryUpdatedEvent event = new GalleryUpdatedEvent();
				// fireEvent(event);
				//
				// // TODO: Add something here that says,
				// // hey, upload succeeded
				//
				// final PopupPanel imagePopup = new PopupPanel(true);
				// imagePopup.setAnimationEnabled(true);
				// imagePopup.setWidget(overlay);
				// imagePopup.setGlassEnabled(true);
				// imagePopup.setAutoHideEnabled(true);
				//
				// imagePopup.center();
				//
				// }
				// });
				
			}
			else
			{
				Window.alert("Problem uploading file.");
			}
		}
	};
	
	protected ChangeHandler changeHandler = new ChangeHandler()
	{
		@Override
		public void onChange(ChangeEvent event)
		{
			getUploadUrlAndSubmit();
		}
	};
	
	protected void getUploadUrlAndSubmit()
	{
		userImageService.getBlobstoreUploadUrl(new AsyncCallbackImpl<String>()
		{
			@Override
			public void onReturn(String result)
			{
				result = result.replace("Drew-i7-PC", "localhost");
				
				Log.info("UploadPresenter.getUploadUrlAndSubmit().new AsyncCallbackImpl() {...} -> onReturn() result: " + result);
				
				// now set the extra form stuff
				getView().setFormMeta(result);
				
				// submit the form to complete the upload
				getView().submitAndReset();
				
			}
		});
		
	}
	
}
