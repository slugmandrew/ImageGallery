package com.slugmandrew.imagegallery.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.slugmandrew.imagegallery.shared.Tag;
import com.slugmandrew.imagegallery.shared.UploadedImage;

@RemoteServiceRelativePath("images")
public interface UserImageService extends RemoteService
{
	
	public String getBlobstoreUploadUrl();
	public UploadedImage get(String key);
	public List<UploadedImage> getRecentlyUploaded();
	public void deleteImage(String key);
	public String tagImage(Tag tag);
	public List<Tag> getTagsForImage(UploadedImage image);
	
}
