package com.venmo.view;

import org.robolectric.shadows.ShadowView;

/** Assertions for testing Robolectric Shadow classes. */
public final class Assertions {

    public static ShadowViewAssert assertThat(ShadowView actual) {
        return new ShadowViewAssert(actual);
    }
}
