package com.slugmandrew.imagegallery.server;

import java.util.Date;
import java.util.List;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.slugmandrew.imagegallery.client.services.UserImageService;
import com.slugmandrew.imagegallery.shared.Tag;
import com.slugmandrew.imagegallery.shared.UploadedImage;

@SuppressWarnings("serial")
public class UserImageServiceImpl extends RemoteServiceServlet implements
		UserImageService
{
	
	@Override
	public String getBlobstoreUploadUrl()
	{
		BlobstoreService blobstoreService = BlobstoreServiceFactory
				.getBlobstoreService();
		return blobstoreService.createUploadUrl("/upload");
	}
	
	@Override
	public UploadedImage get(String key)
	{
		UploadedImageDao dao = new UploadedImageDao();
		UploadedImage image = dao.get(key);
		return image;
	}
	
	@Override
	public List<UploadedImage> getRecentlyUploaded()
	{
		UploadedImageDao dao = new UploadedImageDao();
		List<UploadedImage> images = dao.getRecent();
		return images;
	}
	
	@Override
	public void deleteImage(String key)
	{
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		UploadedImageDao dao = new UploadedImageDao();
		UploadedImage image = dao.get(key);
		if(image.getOwnerId().equals(user.getUserId()))
		{
			dao.delete(key);
		}
	}
	
	public String tagImage(Tag tag)
	{
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		TagDao dao = new TagDao();
		
		// TODO: Do validation here of x, y, ImageId
		
		tag.setTaggerId(user.getUserId());
		tag.setCreatedAt(new Date());
		
		String key = dao.put(tag);
		return key;
	}
	
	@Override
	public List<Tag> getTagsForImage(UploadedImage image)
	{
		TagDao dao = new TagDao();
		List<Tag> tags = dao.getForImage(image);
		return tags;
	}
	
}
