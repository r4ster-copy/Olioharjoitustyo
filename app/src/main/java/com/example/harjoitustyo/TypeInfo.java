package com.example.harjoitustyo;

// Contains the information of a single Lutemon type and its strengths/weaknesses against other types
public class TypeInfo {
    public String name; // Type name (e.g., fire, grass)
    public int iconResId; // Resource ID of the type's icon
    public int[] strongAgainstIcons; // Icons of types this type is strong against
    public String[] strongAgainstNames; // Names of the types this type is strong against
    public int[] weakAgainstIcons; // Icons of types this type is weak against
    public String[] weakAgainstNames; // Names of the types this type is weak against

    // Creates a new type information object with the provided data
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