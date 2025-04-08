package com.malabiga.TheDrone.condition;

public class Model {
    public enum WeightCategory{
        LIGHTWEIGHT,
        MIDDLEWEIGHT,
        CRUISERWEIGHT,
        HEAVYWEIGHT
    }

    public enum State {
        IDLE,
        LOADING,
        LOADED,
        DELIVERING,
        DELIVERED,
        RETURNING
    }
}