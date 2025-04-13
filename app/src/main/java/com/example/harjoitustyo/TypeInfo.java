package com.example.harjoitustyo;

public class TypeInfo {
    public String name;
    public int iconResId;
    public int[] strongAgainstIcons;
    public String[] strongAgainstNames;
    public int[] weakAgainstIcons;
    public String[] weakAgainstNames;

    public TypeInfo(String name, int iconResId, int[] strongAgainstIcons, String[] strongAgainstNames,
                    int[] weakAgainstIcons, String[] weakAgainstNames) {
        this.name = name;
        this.iconResId = iconResId;
        this.strongAgainstIcons = strongAgainstIcons;
        this.strongAgainstNames = strongAgainstNames;
        this.weakAgainstIcons = weakAgainstIcons;
        this.weakAgainstNames = weakAgainstNames;
    }
}