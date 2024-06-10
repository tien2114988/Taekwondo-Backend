package com.taekwondo.enums;

import lombok.Getter;

@Getter
public enum Level {
    CAP_8("Cấp 8") , CAP_7("Cấp 7"), CAP_6("Cấp 6"), CAP_5("Cấp 5"),
    CAP_4("Cấp 4"), CAP_3("Cấp 3"), CAP_2("Cấp 2"), CAP_1("Cấp 1"),
    DANG_1("1 Đẳng"), DANG_2("2 Đẳng"), DANG_3("3 Đẳng");

    private final String displayName;

    private Level(String displayName) {
        this.displayName = displayName;
    }

    public Level next() {
        // No bounds checking required here, because the last instance overrides
        return values()[ordinal() + 1];
    }
}
