package com.app.kujacustomerapp;


import android.app.Application;

//import com.example.baseproject.di.component.DaggerAppComponent;


import com.app.kujacustomerapp.di.component.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;


public class KujaCustomerApp extends DaggerApplication {

    public static Application instance;

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.factory().create(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
