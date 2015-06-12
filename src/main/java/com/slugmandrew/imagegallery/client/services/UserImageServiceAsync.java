package com.slugmandrew.imagegallery.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.slugmandrew.imagegallery.shared.Tag;
import com.slugmandrew.imagegallery.shared.UploadedImage;

public interface UserImageServiceAsync
{
	
	public void getBlobstoreUploadUrl(AsyncCallback<String> callback);
	
	void get(String key, AsyncCallback<UploadedImage> callback);
	
	void getRecentlyUploaded(AsyncCallback<List<UploadedImage>> callback);
	
	void deleteImage(String key, AsyncCallback<Void> callback);
	
	void tagImage(Tag tag, AsyncCallback<String> callback);
	
	void getTagsForImage(UploadedImage image, AsyncCallback<List<Tag>> callback);
	
}
