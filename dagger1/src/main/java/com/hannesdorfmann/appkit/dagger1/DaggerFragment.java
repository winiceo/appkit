package com.hannesdorfmann.appkit.dagger1;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import com.hannesdorfmann.fragmentargs.FragmentArgs;
import dagger.ObjectGraph;
import icepick.Icepick;

/**
 * A Fragment class that can be used as base class for each fragment that supports Dagger injection,
 * FragmentArgs and Icepick
 *
 * @author Hannes Dorfmann
 */
public class DaggerFragment extends Fragment implements Injector {

  @Override public ObjectGraph getObjectGraph() {
    Activity act = getActivity();

    if (act == null) {
      throw new NullPointerException("Activity of Fragment is null");
    }

    if (!(act.getApplication() instanceof Injector)) {
      throw new IllegalArgumentException("The application class of your android app must implement "
          + Injector.class.getCanonicalName());
    }

    Injector injector = (Injector) act.getApplication();
    ObjectGraph og = injector.getObjectGraph();

    if (og == null) {
      throw new NullPointerException("Object Graph is null");
    }

    return og;
  }

  @Override
  public void onCreate(Bundle saved) {
    super.onCreate(saved);
    getObjectGraph().inject(this);
    FragmentArgs.inject(this);
    Icepick.restoreInstanceState(this, saved);
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    Icepick.saveInstanceState(this, outState);
  }
}