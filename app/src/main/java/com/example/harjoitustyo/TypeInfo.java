package com.example.harjoitustyo;

// Sisältää yhden Lutemon-tyypin tiedot ja sen vahvuudet/heikkoudet muihin tyyppeihin
public class TypeInfo {
    public String name; // Tyypin nimi (esim. fire, grass)
    public int iconResId; // Tyypin ikonin resurssi-ID
    public int[] strongAgainstIcons; // Kuvakkeet tyypeistä, joihin tämä tyyppi on vahva
    public String[] strongAgainstNames; // Nimelliset vastineet vahvuuksille
    public int[] weakAgainstIcons; // Kuvakkeet tyypeistä, joihin tämä tyyppi on heikko
    public String[] weakAgainstNames; // Nimelliset vastineet heikkouksille

    // Luo uuden tyyppitiedon annetulla sisällöllä
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