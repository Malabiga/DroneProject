package com.malabiga.TheDrone.data;

public class DataPackage {

    /* MODEL */
    public enum ModelCategory{
        LIGHTWEIGHT,
        MIDDLEWEIGHT,
        CRUISERWEIGHT,
        HEAVYWEIGHT
    }

    /* STATE */
    public enum StateCategory {
        IDLE,
        LOADING,
        LOADED,
        DELIVERING,
        DELIVERED,
        RETURNING
    }
}