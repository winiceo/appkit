package com.hannesdorfmann.appkit.dagger1.mvp.httpkit;

import com.hannesdorfmann.appkit.dagger1.Dagger1Injector;
import com.hannesdorfmann.appkit.mvp.MvpView;
import com.hannesdorfmann.appkit.mvp.httpkit.MvpHttpPresenter;
import com.hannesdorfmann.httpkit.HttpKit;

/**
 * A  presenter supporting dagger1 and httpkit
 *
 * @author Hannes Dorfmann
 */
public class Dagger1MvpHttpPresenter<V extends MvpView<D>, D> extends MvpHttpPresenter<V, D> {

  public Dagger1MvpHttpPresenter(Dagger1Injector injector) {
    if (injector == null) {
      throw new IllegalArgumentException("Injector is null!");
    }

    injector.getObjectGraph().inject(this);
    HttpKit httpKit = injector.getObjectGraph().get(HttpKit.class);
    setHttpKit(httpKit);
  }
}
