package com.venmo.view;

import org.robolectric.shadows.ShadowView;

public class ShadowViewAssert extends AbstractShadowViewAssert<ShadowViewAssert, ShadowView> {
    public ShadowViewAssert(ShadowView actual) {
        super(actual, ShadowViewAssert.class);
    }
}
