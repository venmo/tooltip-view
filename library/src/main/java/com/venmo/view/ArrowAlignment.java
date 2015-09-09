package com.venmo.view;

public enum ArrowAlignment {
    LEFT, CENTER, RIGHT, CUSTOM;

    public static ArrowAlignment getAlignment(int ordinal) {
        for (ArrowAlignment alignment : values()) {
            if (alignment.ordinal() == ordinal) {
                return alignment;
            }
        }
        throw new IllegalArgumentException("wrong ordinal");
    }
}
