package com.slugmandrew.imagegallery.client.application.gallery;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

class GalleryView extends ViewWithUiHandlers<GalleryUiHandlers> implements GalleryPresenter.MyView
{
	interface Binder extends UiBinder<Widget, GalleryView>
	{
	}
	
	@UiField
	FlexTable galleryTable;
	
	@Inject
	GalleryView(Binder uiBinder)
	{
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public FlexTable getGalleryTable()
	{
		return galleryTable;
	}
	
}
