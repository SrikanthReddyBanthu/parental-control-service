package com.bskyb.internettv.parental_control_service.services;

public enum ParentalControlLevels {

    L_U("U", 0),
    L_PG("PG", 1),
    L_12("12", 2),
    L_15("15", 3),
    L_18("18", 4);

    private final String parentalControlLevel;
    private final int parentalControlLevelRank;

    ParentalControlLevels(final String parentalControlLevel, final int parentalControlLevelRank) {

        this.parentalControlLevel = parentalControlLevel;
        this.parentalControlLevelRank = parentalControlLevelRank;
    }

    public String getParentalControlLevel() {
        return parentalControlLevel;
    }

    public int getParentalControlLevelRank() {
        return parentalControlLevelRank;
    }
}
