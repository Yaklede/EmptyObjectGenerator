package com.empty.generator.value;

public class EmptyValue {
    private boolean isNullable;
    private String string;
    private int anInt;
    private long aLong;
    private float aFloat;
    private double aDouble;
    private boolean aBoolean;
    private int arraySize;
    private int innerArraySize;

    private EmptyValue() {
    }

    public EmptyValue(boolean isNullable, String string, int anInt, long aLong, float aFloat, double aDouble, boolean aBoolean, int arraySize, int innerArraySize) {
        this.isNullable = isNullable;
        this.string = string;
        this.anInt = anInt;
        this.aLong = aLong;
        this.aFloat = aFloat;
        this.aDouble = aDouble;
        this.aBoolean = aBoolean;
        this.arraySize = arraySize;
        this.innerArraySize = innerArraySize;
    }

    public boolean isNullable() {
        return isNullable;
    }

    public String getString() {
        return string;
    }

    public int getInt() {
        return anInt;
    }

    public long getLong() {
        return aLong;
    }

    public float getFloat() {
        return aFloat;
    }

    public double getDouble() {
        return aDouble;
    }

    public boolean getBoolean() {
        return aBoolean;
    }

    public int getArraySize() {
        return arraySize;
    }

    public int getInnerArraySize() {
        return innerArraySize;
    }

    public void setNullable(boolean nullable) {
        isNullable = nullable;
    }

    public void setString(String string) {
        this.string = string;
    }

    public void setInt(int anInt) {
        this.anInt = anInt;
    }

    public void setLong(long aLong) {
        this.aLong = aLong;
    }

    public void setFloat(float aFloat) {
        this.aFloat = aFloat;
    }

    public void setDouble(double aDouble) {
        this.aDouble = aDouble;
    }

    public void setArraySize(int arraySize) {
        this.arraySize = arraySize;
    }

    public void setInnerArraySize(int innerArraySize) {
        this.innerArraySize = innerArraySize;
    }
}
