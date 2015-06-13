package com.slugmandrew.imagegallery.client.events;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.slugmandrew.imagegallery.shared.UploadedImage;

public class ViewImageEvent extends GwtEvent<ViewImageEvent.ViewImageHandler>
{
	private static Type<ViewImageHandler> TYPE = new Type<ViewImageHandler>();
	
	public interface ViewImageHandler extends EventHandler
	{
		void onViewImage(ViewImageEvent event);
	}
	
	private final UploadedImage image;
	
	public ViewImageEvent(final UploadedImage image)
	{
		this.image = image;
	}
	
	public static Type<ViewImageHandler> getType()
	{
		return TYPE;
	}
	
	@Override
	protected void dispatch(final ViewImageHandler handler)
	{
		handler.onViewImage(this);
	}
	
	@Override
	public Type<ViewImageHandler> getAssociatedType()
	{
		return TYPE;
	}
	
	public UploadedImage getImage()
	{
		return image;
	}
}
