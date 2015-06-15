package com.slugmandrew.imagegallery.server;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.allen_sauer.gwt.log.client.Log;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.slugmandrew.imagegallery.shared.UploadedImage;

@SuppressWarnings("serial")
public class UploadServlet extends HttpServlet
{
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		
		Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(req);
		BlobKey blobKey = blobs.get("image");
		
		if(blobKey == null)
		{
			
		}
		else
		{
			
			ImagesService imagesService = ImagesServiceFactory.getImagesService();
			String imageUrl = imagesService.getServingUrl(blobKey);
			
			// replace for dev server
			String replacedImageUrl = imageUrl.replace("0.0.0.0", "localhost");
			
			UserService userService = UserServiceFactory.getUserService();
			// TODO: Add a better check for whether the user is logged in or not
			// Don't even let the user upload or get here
			User user = userService.getCurrentUser();
			
			Entity uploadedImage = new Entity("UploadedImage");
			uploadedImage.setProperty("blobKey", blobKey);
			uploadedImage.setProperty(UploadedImage.CREATED_AT, new Date());
			uploadedImage.setProperty(UploadedImage.OWNER_ID, user.getUserId());
			
			// Highly unlikely we'll ever search on this property
			uploadedImage.setUnindexedProperty(UploadedImage.SERVING_URL, replacedImageUrl);
			
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			datastore.put(uploadedImage);
			
			Log.info("UploadServlet -> doPost() uploadedImage.getKey(): " + uploadedImage.getKey());
			String keyString = KeyFactory.keyToString(uploadedImage.getKey());
			
			Log.info("UploadServlet -> doPost() keyString: " + keyString);
			
			res.setHeader("Content-Type", "text/html");
			res.getWriter().println(keyString);
			
		}
	}
	
	// @Override
	// protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	// {
	// String uploadedImageKey = req.getParameter("uploadedImageKey");
	//
	// Log.info("UploadServlet -> doGet() uploadedImageKey: " + uploadedImageKey);
	//
	// resp.setHeader("Content-Type", "text/html");
	//
	// // This is a bit hacky, but it'll work. We'll use this key in an Async service to
	// // fetch the image and image information
	// resp.getWriter().println(uploadedImageKey);
	//
	// }
}
