package com.venmo.view;

import android.graphics.RectF;
import android.view.View;

public final class ArrowAlignmentHelper {

    public static float calculateArrowMidPoint(TooltipView view, RectF rectF, boolean isHorizontal) {
        int offset = view.getAlignmentOffset();
        float middle = 0f;

        float length = isHorizontal ? rectF.width() : rectF.height();
        switch (view.getArrowAlignment()) {
            case START:
                middle = offset == 0 ? length / 4 : offset;
                break;
            case CENTER:
                middle = length / 2;
                if (offset > 0)
                    throw new IllegalArgumentException(
                            "Offsets are not support when the tooltip arrow is anchored in the middle of the view.");
                break;
            case END:
                middle = length;
                middle -= (offset == 0 ? length / 4 : offset);
                break;
            case ANCHORED_VIEW:
                middle = length / 2;
                if (view.getAnchoredViewId() != View.NO_ID) {
                    View anchoredView = ((View) view.getParent())
                            .findViewById(view.getAnchoredViewId());
                    if (isHorizontal) {
                        middle += anchoredView.getX() + anchoredView.getWidth() / 2 - view.getX()
                                - view.getWidth() / 2;
                    } else {
                        middle += anchoredView.getY() + anchoredView.getHeight() / 2 - view.getY()
                                - view.getHeight() / 2;
                    }
                }
                break;
        }
        return middle;
    }
}
