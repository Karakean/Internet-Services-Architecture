package com.example.isa.entity;

public class Family {
    private final long ID;
    private final String name;

    private final int classificationYear;

    public Family(long ID, String name, int classificationYear) {
        this.ID = ID;
        this.name = name;
        this.classificationYear = classificationYear;
    }

    public long getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "ID: " + ID + "   NAME: " + name + "   CLASSIFICATION YEAR: " + classificationYear;
    }
}
