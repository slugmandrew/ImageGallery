package com.slugmandrew.imagegallery.client.application.gallery;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class GalleryModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
            bindSingletonPresenterWidget(GalleryPresenter.class, GalleryPresenter.MyView.class, GalleryView.class);
    }
}