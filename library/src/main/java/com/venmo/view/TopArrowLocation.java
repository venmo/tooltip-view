package com.venmo.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

import static android.graphics.Path.Direction;

class TopArrowLocation implements ArrowLocation {

    @Override
    public void configureDraw(TooltipView view, Canvas canvas) {
        Path tooltipPath = new Path();
        view.setTooltipPath(tooltipPath);
        RectF rectF = new RectF(canvas.getClipBounds());
        rectF.top += view.getArrowHeight();

        tooltipPath.addRoundRect(rectF, view.getCornerRadius(), view.getCornerRadius(),
                Direction.CW);

        float middle = ArrowAlignmentHelper.calculateArrowMidPoint(view, rectF,true);

        tooltipPath.moveTo(middle, 0f);
        int arrowDx = view.getArrowWidth() / 2;
        tooltipPath.lineTo(middle - arrowDx, rectF.top);
        tooltipPath.lineTo(middle + arrowDx, rectF.top);
        tooltipPath.close();

        view.setPaint(new Paint(Paint.ANTI_ALIAS_FLAG));
        view.getTooltipPaint().setColor(view.getTooltipColor());
    }
}
