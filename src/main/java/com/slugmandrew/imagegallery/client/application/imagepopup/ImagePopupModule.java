package com.slugmandrew.imagegallery.client.application.imagepopup;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ImagePopupModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
            bindSingletonPresenterWidget(ImagePopupPresenter.class, ImagePopupPresenter.MyView.class, ImagePopupView.class);
    }
}