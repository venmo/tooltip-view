package com.venmo.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleableRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class TooltipView extends TextView {

    private static final int NOT_PRESENT = Integer.MIN_VALUE;
    private int arrowHeight;
    private int arrowWidth;
    private int cornerRadius;
    private @IdRes int anchoredViewId;
    private @ColorRes int tooltipColor;
    private ArrowLocation arrowLocation;
    private ArrowAlignment arrowAlignment;
    private int alignmentOffset;
    private int arrowPositioning;
    private Paint paint;
    private Path tooltipPath;

    public TooltipView(Context context) {
        super(context);
        init(null, 0);
    }

    public TooltipView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public TooltipView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        Resources res = getResources();
        TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.TooltipView, defStyle, 0);
        try {
            anchoredViewId = a.getResourceId(R.styleable.TooltipView_anchoredView, View.NO_ID);
            tooltipColor = a.getColor(R.styleable.TooltipView_tooltipColor, Color.TRANSPARENT);
            cornerRadius = getDimension(a, R.styleable.TooltipView_cornerRadius,
                    R.dimen.tooltip_default_corner_radius);
            arrowHeight = getDimension(a, R.styleable.TooltipView_arrowHeight,
                    R.dimen.tooltip_default_arrow_height);
            arrowWidth = getDimension(a, R.styleable.TooltipView_arrowWidth,
                    R.dimen.tooltip_default_arrow_width);
            arrowPositioning = a.getInteger(R.styleable.TooltipView_arrowLocation,
                    res.getInteger(R.integer.tooltip_default_arrow_location));
            arrowLocation = arrowPositioning == 0 ? new TopArrowLocation()
                    : new BottomArrowLocation();
            arrowAlignment = ArrowAlignment.getAlignment(
                    a.getInteger(R.styleable.TooltipView_arrowAlignment, res.getInteger(
                            R.integer.tooltip_default_arrow_alignment)));
            alignmentOffset = getDimension(a, R.styleable.TooltipView_arrowAlignmentOffset,
                    R.dimen.tooltip_default_offset);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight() + arrowHeight);
    }

    @Override
    public void invalidate() {
        super.invalidate();
        tooltipPath = null;
        paint = null;
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        if (tooltipPath == null || paint == null) {
            arrowLocation.configureDraw(this, canvas);
        }
        canvas.drawPath(tooltipPath, paint);
        super.onDraw(canvas);
    }

    Paint getTooltipPaint() {
        return paint;
    }

    void setPaint(Paint paint) {
        this.paint = paint;
    }

    Path getTooltipPath() {
        return tooltipPath;
    }

    void setTooltipPath(Path tooltipPath) {
        this.tooltipPath = tooltipPath;
    }

    public int getArrowHeight() {
        return arrowHeight;
    }

    public void setArrowHeight(int arrowHeight) {
        this.arrowHeight = arrowHeight;
        invalidate();
    }

    public void setArrowHeightResource(@DimenRes int resId) {
        arrowHeight = getResources().getDimensionPixelSize(resId);
        invalidate();
    }

    public int getArrowWidth() {
        return arrowWidth;
    }

    public void setArrowWidth(int arrowWidth) {
        this.arrowWidth = arrowWidth;
        invalidate();
    }

    public void setArrowWidthResource(@DimenRes int resId) {
        arrowWidth = getResources().getDimensionPixelSize(resId);
        invalidate();
    }

    public int getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        invalidate();
    }

    public void setCornerRadiusResource(@DimenRes int resId) {
        cornerRadius = getResources().getDimensionPixelSize(resId);
        invalidate();
    }

    public int getAnchoredViewId() {
        return anchoredViewId;
    }

    public void setAnchoredViewId(@IdRes int anchoredViewId) {
        this.anchoredViewId = anchoredViewId;
        invalidate();
    }

    public int getTooltipColor() {
        return tooltipColor;
    }

    public void setTooltipColor(int tooltipColor) {
        this.tooltipColor = tooltipColor;
        invalidate();
    }

    public void setArrowPositioning(int arrowPositioning) {
        this.arrowPositioning = arrowPositioning;
        invalidate();
    }

    public ArrowAlignment getArrowAlignment() {
        return arrowAlignment;
    }

    public void setArrowAlignment(ArrowAlignment arrowAlignment) {
        this.arrowAlignment = arrowAlignment;
        invalidate();
    }

    public int getAlignmentOffset() {
        return alignmentOffset;
    }

    public void setAlignmentOffset(int offset) {
        this.alignmentOffset = offset;
        invalidate();
    }

    public void setAlignmentOffsetResource(@DimenRes int resId) {
        this.alignmentOffset = getResources().getDimensionPixelSize(resId);
        invalidate();
    }

    private int getDimension(TypedArray a, @StyleableRes int styleableId,
            @DimenRes int defaultDimension) {
        int result = a.getDimensionPixelSize(styleableId, NOT_PRESENT);
        if (result == NOT_PRESENT) {
            result = getResources().getDimensionPixelSize(defaultDimension);
        }
        return result;
    }
}
