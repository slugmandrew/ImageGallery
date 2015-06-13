package com.slugmandrew.imagegallery.client.cell;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ImageCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.slugmandrew.imagegallery.shared.UploadedImage;

public class UploadedImageCell extends AbstractCell<UploadedImage>
{
	private ImageCell cell = new ImageCell();
	
	public void render(com.google.gwt.cell.client.Cell.Context context, UploadedImage value, SafeHtmlBuilder sb)
	{
		if(value != null)
		{
			cell.render(context, value.getServingUrl() + "=s250", sb);
		}
	}
}
