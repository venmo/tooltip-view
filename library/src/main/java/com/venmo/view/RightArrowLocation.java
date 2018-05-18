package com.venmo.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

import static android.graphics.Path.Direction;

class RightArrowLocation implements ArrowLocation {

    @Override
    public void configureDraw(TooltipView view, Canvas canvas) {
        Path tooltipPath = new Path();
        view.setTooltipPath(tooltipPath);
        RectF rectF = new RectF(canvas.getClipBounds());
        rectF.right -= view.getArrowHeight();

        rectF.top += 1;
        rectF.bottom -= 1;
        rectF.left += 1;
        rectF.right -= 1;

        tooltipPath.addRoundRect(rectF, view.getCornerRadius(), view.getCornerRadius(), Direction.CW);

        float middle = ArrowAlignmentHelper.calculateArrowMidPoint(view, rectF, false);
        tooltipPath.moveTo(view.getWidth(), middle);
        int arrowDy = view.getArrowWidth() / 2;
        tooltipPath.lineTo(rectF.right, middle - arrowDy);
        tooltipPath.lineTo(rectF.right, middle + arrowDy);
        tooltipPath.close();

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(view.getTooltipColor());
        view.setPaint(paint);
    }
}
