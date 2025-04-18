// Generated by view binder compiler. Do not edit!
package org.amnezia.awg.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import org.amnezia.awg.R;

public final class MainActivityBinding implements ViewBinding {
  @NonNull
  private final CoordinatorLayout rootView;

  @NonNull
  public final FragmentContainerView detailContainer;

  /**
   * This binding is not available in all configurations.
   * <p>
   * Present:
   * <ul>
   *   <li>layout-sw600dp/</li>
   * </ul>
   *
   * Absent:
   * <ul>
   *   <li>layout/</li>
   * </ul>
   */
  @Nullable
  public final FragmentContainerView listFragment;

  @NonNull
  public final CoordinatorLayout mainActivityContainer;

  /**
   * This binding is not available in all configurations.
   * <p>
   * Present:
   * <ul>
   *   <li>layout-sw600dp/</li>
   * </ul>
   *
   * Absent:
   * <ul>
   *   <li>layout/</li>
   * </ul>
   */
  @Nullable
  public final LinearLayout masterDetailWrapper;

  private MainActivityBinding(@NonNull CoordinatorLayout rootView,
      @NonNull FragmentContainerView detailContainer, @Nullable FragmentContainerView listFragment,
      @NonNull CoordinatorLayout mainActivityContainer,
      @Nullable LinearLayout masterDetailWrapper) {
    this.rootView = rootView;
    this.detailContainer = detailContainer;
    this.listFragment = listFragment;
    this.mainActivityContainer = mainActivityContainer;
    this.masterDetailWrapper = masterDetailWrapper;
  }

  @Override
  @NonNull
  public CoordinatorLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static MainActivityBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static MainActivityBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.main_activity, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static MainActivityBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.detail_container;
      FragmentContainerView detailContainer = ViewBindings.findChildViewById(rootView, id);
      if (detailContainer == null) {
        break missingId;
      }

      id = R.id.list_fragment;
      FragmentContainerView listFragment = ViewBindings.findChildViewById(rootView, id);

      CoordinatorLayout mainActivityContainer = (CoordinatorLayout) rootView;

      id = R.id.master_detail_wrapper;
      LinearLayout masterDetailWrapper = ViewBindings.findChildViewById(rootView, id);

      return new MainActivityBinding((CoordinatorLayout) rootView, detailContainer, listFragment,
          mainActivityContainer, masterDetailWrapper);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
