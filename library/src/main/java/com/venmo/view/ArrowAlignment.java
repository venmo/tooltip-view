package com.venmo.view;

public enum ArrowAlignment {
    START(0), CENTER(1), END(2), CUSTOM(3);


    private final int value;

    ArrowAlignment(int value) {
        this.value = value;
    }


    public static ArrowAlignment getAlignment(int value) {
        for (ArrowAlignment alignment : values()) {
            if (value == alignment.getValue()) {
                return alignment;
            }
        }
        throw new IllegalArgumentException("wrong ordinal");
    }

    public int getValue() {
        return value;
    }
}
