package com.slugmandrew.imagegallery.client.dispatch;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

/**
 * Global RPC System
 */
public abstract class AsyncCallbackImpl<T> implements AbstractAsyncCallback<T>, HasHandlers
{
	@Inject
	private static EventBus eventBus;
	
	public AsyncCallbackImpl()
	{
		
	}
	
	@Override
	public void onSuccess(T result)
	{
		// delegate action to onReturn block of this particular call
		onReturn(result);
	}
	
	@Override
	public void onFailure(Throwable caught)
	{
		
	}
	
	@Override
	public void fireEvent(GwtEvent<?> event)
	{
		eventBus.fireEvent(event);
	}
	
}
