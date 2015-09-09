package com.venmo.view;

public enum ArrowAlignment {
    LEFT, CENTER, RIGHT, CUSTOM;

    public static ArrowAlignment getAlignment(int oridinal) {
        for (ArrowAlignment alignment : values()) {
            if (alignment.ordinal() == oridinal) {
                return alignment;
            }
        }
        throw new IllegalArgumentException("wrong oridinal");
    }
}
