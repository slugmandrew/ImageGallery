package com.slugmandrew.imagegallery.client.application.upload;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class UploadModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
            bindSingletonPresenterWidget(UploadPresenter.class, UploadPresenter.MyView.class, UploadView.class);
    }
}