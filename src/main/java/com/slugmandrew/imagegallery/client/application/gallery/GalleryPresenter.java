package com.slugmandrew.imagegallery.client.application.gallery;

import java.util.List;

import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.slugmandrew.imagegallery.client.events.GalleryUpdatedEvent;
import com.slugmandrew.imagegallery.client.events.GalleryUpdatedEventHandler;
import com.slugmandrew.imagegallery.client.services.UserImageServiceAsync;
import com.slugmandrew.imagegallery.shared.UploadedImage;

public class GalleryPresenter extends PresenterWidget<GalleryPresenter.MyView> implements GalleryUiHandlers, GalleryUpdatedEventHandler
{
	interface MyView extends View, HasUiHandlers<GalleryUiHandlers>
	{
		FlexTable getGalleryTable();
		void setData(List<UploadedImage> data);
	}
	
	private UserImageServiceAsync userImageService;
	
	private static final int GALLERY_WIDTH = 8;
	
	@Inject
	GalleryPresenter(EventBus eventBus, MyView view, UserImageServiceAsync userImageService)
	{
		super(eventBus, view);
		
		this.userImageService = userImageService;
		
		getView().setUiHandlers(this);
	}
	
	@Override
	protected void onBind()
	{
		super.onBind();
		
		addRegisteredHandler(GalleryUpdatedEvent.TYPE, this);
	}
	
	@Override
	protected void onReveal()
	{
		super.onReveal();
		
		refreshGallery();
	}
	
	public void refreshGallery()
	{
		userImageService.getRecentlyUploaded(new AsyncCallback<List<UploadedImage>>()
		{
			@Override
			public void onSuccess(List<UploadedImage> images)
			{
				
				getView().setData(images);
				
				
				
				int currentColumn = 0;
				int currentRow = 0;
				for(final UploadedImage image : images)
				{
					
					Image imageWidget = createImageWidget(image);
					
					getView().getGalleryTable().setWidget(currentRow, currentColumn, imageWidget);
					
					currentColumn++;
					
					if(currentColumn >= GALLERY_WIDTH)
					{
						currentColumn = 0;
						currentRow++;
					}
				}
				
			}
			
			@Override
			public void onFailure(Throwable caught)
			{
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	
	
	private Image createImageWidget(final UploadedImage image)
	{
		final Image imageWidget = new Image();
		imageWidget.setUrl(image.getServingUrl() + "=s250");
		final DecoratedPopupPanel simplePopup = new DecoratedPopupPanel(true);
		
		imageWidget.addMouseOverHandler(new MouseOverHandler()
		{
			@Override
			public void onMouseOver(MouseOverEvent event)
			{
				Widget source = (Widget) event.getSource();
				int left = source.getAbsoluteLeft() + 10;
				int top = source.getAbsoluteTop() + source.getOffsetHeight() + 10;
				
				simplePopup.setWidth("150px");
				simplePopup.setWidget(new HTML("Uploaded: " + image.getCreatedAt()));
				simplePopup.show();
				simplePopup.setPopupPosition(left, top);
			}
		});
		
		imageWidget.addMouseOutHandler(new MouseOutHandler()
		{
			
			@Override
			public void onMouseOut(MouseOutEvent event)
			{
				simplePopup.hide();
			}
		});
		
		// imageWidget.addClickHandler(new ClickHandler()
		// {
		// @Override
		// public void onClick(ClickEvent event)
		// {
		// ImageOverlay imageOverlay = new ImageOverlay(image, parent.getLoginInfo());
		// imageOverlay.addGalleryUpdatedEventHandler(PhotoGallery.this);
		//
		// final PopupPanel imagePopup = new PopupPanel(true);
		// imagePopup.setAnimationEnabled(true);
		// imagePopup.setWidget(imageOverlay);
		// imagePopup.setGlassEnabled(true);
		// imagePopup.setAutoHideEnabled(true);
		//
		// imagePopup.center();
		// }
		// });
		
		return imageWidget;
	}
	
	@Override
	public void onGalleryUpdated(GalleryUpdatedEvent event)
	{
		refreshGallery();
	}
	
	
	
	
	
}
