package com.example.isa.entity;

public class Species {
    private final long ID;
    private final String name;
    private final Family family;

    private final boolean isHallucinogenic;

    public Species(long ID, String name, Family family, boolean isHallucinogenic) {
        this.name = name;
        this.ID = ID;
        this.family = family;
        this.isHallucinogenic = isHallucinogenic;
    }

    public long getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "ID: " + ID + "   NAME: " + name + "   FAMILY: {" + family + "}   IS HALLUCINOGENIC: " + isHallucinogenic;
    }
}
