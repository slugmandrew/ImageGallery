package com.slugmandrew.imagegallery.client.dispatch;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AbstractAsyncCallback<T> extends AsyncCallback<T>
{
	// this method is not overridden by AsyncCallbackImpl, but is called from onSuccess (where the global success code is executed)
	void onReturn(T result);
}
