package com.slugmandrew.imagegallery.client.application.upload;

import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.slugmandrew.imagegallery.shared.LoginInfo;

class UploadView extends ViewWithUiHandlers<UploadUiHandlers> implements UploadPresenter.MyView
{
	interface Binder extends UiBinder<Widget, UploadView>
	{
	}
	
	@UiField
	FormPanel uploadForm;
	
	@UiField
	FileUpload uploadField;
	
	@Inject
	UploadView(Binder uiBinder)
	{
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	
	@Override
	public void setFormMeta(String url)
	{
		// set the form action to the newly created blobstore upload URL
		uploadForm.setAction(url);
		
		// set the other form stuff to enable submission
		uploadForm.setEncoding(FormPanel.ENCODING_MULTIPART);
		uploadForm.setMethod(FormPanel.METHOD_POST);
		
	}
	
	@Override
	public void submitAndReset()
	{
		uploadForm.submit();
		uploadForm.reset();
	}

	@Override
	public void addFormSubmitCompleteHandler(SubmitCompleteHandler handler)
	{
		uploadForm.addSubmitCompleteHandler(handler);
	}
	
	@Override
	public void addChangeHandler(ChangeHandler changeHandler)
	{
		uploadField.addChangeHandler(changeHandler);
	}
	
	
	
}
