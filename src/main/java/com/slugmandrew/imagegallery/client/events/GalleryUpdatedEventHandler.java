package com.slugmandrew.imagegallery.client.events;

import com.google.gwt.event.shared.EventHandler;

public interface GalleryUpdatedEventHandler extends EventHandler
{
	
	void onGalleryUpdated(GalleryUpdatedEvent event);
	
}
