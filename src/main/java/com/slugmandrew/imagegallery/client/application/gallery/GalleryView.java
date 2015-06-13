package com.slugmandrew.imagegallery.client.application.gallery;

import java.util.List;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.slugmandrew.imagegallery.client.cell.UploadedImageCell;
import com.slugmandrew.imagegallery.client.resource.MyCellListResource;
import com.slugmandrew.imagegallery.shared.UploadedImage;

class GalleryView extends ViewWithUiHandlers<GalleryUiHandlers> implements GalleryPresenter.MyView
{
	
	interface Binder extends UiBinder<Widget, GalleryView>
	{
	}
	
	@UiField(provided = true)
	CellList<UploadedImage> table;
	
	@Inject
	GalleryView(Binder uiBinder, UploadedImageCell uploadedImageCell)
	{
		table = new CellList<>(uploadedImageCell, MyCellListResource.INSTANCE);
		
		initWidget(uiBinder.createAndBindUi(this));
		
	}
	
	@Override
	public void setData(List<UploadedImage> data)
	{
		table.setRowData(data);
	}
	
}
