package com.venmo.view;

import org.assertj.core.api.AbstractAssert;
import org.robolectric.shadows.ShadowView;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AbstractShadowViewAssert<S extends AbstractShadowViewAssert<S, A>, A extends ShadowView>
        extends AbstractAssert<S, A> {

    protected AbstractShadowViewAssert(A actual, Class<S> selfType) {
        super(actual, selfType);
    }

    public S wasInvalidated() {
        isNotNull();
        assertThat(actual.wasInvalidated())
                .overridingErrorMessage("Expected view to be invalidated but was not")
                .isTrue();
        return myself;
    }
}
