package com.slugmandrew.imagegallery.client.application;

import com.slugmandrew.imagegallery.client.application.home.HomeModule;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ApplicationModule extends AbstractPresenterModule
{
	@Override
	protected void configure()
	{
		install(new HomeModule());
		
		bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class,
				ApplicationPresenter.MyProxy.class);
	}
}