package com.venmo.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowView;

import static com.venmo.view.Assertions.assertThat;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,
        sdk = 21,
        manifest = "build/intermediates/manifests/androidTest/debug/AndroidManifest.xml")
public class TooltipViewTest {

    private static final @IdRes int sampleId = 234;

    private TooltipView createView() {
        Context context = Robolectric.buildActivity(Activity.class).create().get();
        return new TooltipView(context);
    }

    @Test
    public void settersInvalidateView() {
        TooltipView view = createView();
        ShadowView shadow = shadowOf(view);
        shadow.clearWasInvalidated();

        view.setAnchoredViewId(sampleId);
        assertThat(shadow).wasInvalidated();
        shadow.clearWasInvalidated();

        view.setArrowHeight(123);
        assertThat(shadow).wasInvalidated();
        shadow.clearWasInvalidated();

        view.setArrowHeightResource(R.dimen.tooltip_default_arrow_height);
        assertThat(shadow).wasInvalidated();
        shadow.clearWasInvalidated();

        view.setArrowWidth(123);
        assertThat(shadow).wasInvalidated();
        shadow.clearWasInvalidated();

        view.setArrowWidthResource(R.dimen.tooltip_default_arrow_width);
        assertThat(shadow).wasInvalidated();
        shadow.clearWasInvalidated();

        view.setCornerRadius(123);
        assertThat(shadow).wasInvalidated();
        shadow.clearWasInvalidated();

        view.setCornerRadiusResource(R.dimen.tooltip_default_corner_radius);
        assertThat(shadow).wasInvalidated();
        shadow.clearWasInvalidated();

        view.setTooltipColor(123);
        assertThat(shadow).wasInvalidated();
        shadow.clearWasInvalidated();
    }
}
