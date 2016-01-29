package com.example.andrew.itstime;

public enum EatTimes {
    //delays
    FIRST_BREAKFAST(20), SECOND_BREAKFAST(160), LUNCH(120), AFTER_LUNCH(120),
    AFTER_TRAIN(185), FIRST_DINNER(55), SECOND_DINNER(120), BEFORE_NIGHT(160);

    private final int i;

    EatTimes(int i) {
        this.i = i;
    }

    public int getVal(){
        return i;
    }
}
