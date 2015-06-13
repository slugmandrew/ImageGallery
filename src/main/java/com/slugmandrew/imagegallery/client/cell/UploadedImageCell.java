package com.slugmandrew.imagegallery.client.cell;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ImageCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.slugmandrew.imagegallery.client.events.ViewImageEvent;
import com.slugmandrew.imagegallery.shared.UploadedImage;

public class UploadedImageCell extends AbstractCell<UploadedImage> implements HasHandlers
{
	private final ImageCell cell = new ImageCell();
	private final DecoratedPopupPanel simplePopup = new DecoratedPopupPanel(true);
	private final EventBus eventBus;
	
	@Inject
	public UploadedImageCell(EventBus eventBus)
	{
		super("mouseover", "mouseout", "click");
		
		this.eventBus = eventBus;
	}
	
	@Override
	public void onBrowserEvent(Context context, Element parent, UploadedImage value, NativeEvent event, ValueUpdater<UploadedImage> valueUpdater)
	{
		super.onBrowserEvent(context, parent, value, event, valueUpdater);
		
		if("mouseover".equals(event.getType()))
		{
			int left = parent.getAbsoluteLeft() + 10;
			int top = parent.getAbsoluteTop() + parent.getOffsetHeight();
			
			simplePopup.setWidth("150px");
			simplePopup.setWidget(new HTML("Uploaded: " + value.getCreatedAt()));
			simplePopup.show();
			simplePopup.setPopupPosition(left, top);
			
		}
		
		if("mouseout".equals(event.getType()))
		{
			simplePopup.hide();
		}
		
		if("click".equals(event.getType()))
		{
			fireEvent(new ViewImageEvent(value));
		}
		
	}
	
	public void render(Context context, UploadedImage value, SafeHtmlBuilder sb)
	{
		if(value != null)
		{
			cell.render(context, value.getServingUrl() + "=s250", sb);
		}
	}

	@Override
	public void fireEvent(GwtEvent<?> event)
	{
		eventBus.fireEvent(event);
	}
}
