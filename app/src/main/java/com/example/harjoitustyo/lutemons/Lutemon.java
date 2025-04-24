package com.example.harjoitustyo.lutemons;

import java.io.Serializable;

// Abstrakti yläluokka kaikille Lutemon-olioille
// Määrittelee Lutemonin perustiedot ja käyttäytymisen pelissä
public abstract class Lutemon implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String name;
    protected String color;
    protected int attack;
    protected int defense;
    protected int experience;
    protected int maxHealth;
    protected int currentHealth;
    protected int image;
    protected int trainingPoints;
    protected String location = "home";
    protected int battlesFought = 0;
    protected int battlesWon = 0;
    protected int trainingDays = 0;
    private String type;

    // Abstrakti metodi, joka nollaa tilastot (toteutus perivissä luokissa)
    public abstract void resetStats();

    // Konstruktori
    // Luo uuden Lutemonin ja alustaa kaikki kentät annetulla tiedolla
    public Lutemon(String name, String color, int attack, int defense, int maxHealth, int image) {
        this.name = name;
        this.color = color;
        this.attack = attack;
        this.defense = defense;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.experience = 0;
        this.trainingPoints = 0;
        this.trainingDays = 0;
        this.image = image;
        this.type = determineTypeFromColor(color);
    }

    // Määrittää Lutemonin tyypin sen värin perusteella
    private String determineTypeFromColor(String color) {
        switch (color.toLowerCase()) {
            case "green": return "grass";
            case "orange": return "fire";
            case "pink": return "fairy";
            case "black": return "shadow";
            case "white": return "normal";
            default: return "unknown";
        }
    }

    // Getterit ja setterit

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getName() { return name; }
    public String getColor() { return color; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getExperience() { return experience; }
    public int getMaxHealth() { return maxHealth; }
    public int getCurrentHealth() { return currentHealth; }
    public int getImage() { return image; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public int getTrainingPoints() { return trainingPoints; }
    public int getTrainingDays() { return trainingDays; }
    public int getBattlesFought() { return battlesFought; }
    public int getBattlesWon() { return battlesWon; }
    public int getFights() { return battlesFought; }
    public int getWins() { return battlesWon; }

    // Kasvattaa Lutemonin kokemuspisteitä
    public void addExperience(int i) {
        experience += i;
    }

    // Palauttaa Lutemonin HP:n maksimiin
    public void resetHealth() {
        currentHealth = maxHealth;
    }

    // Vähentää HP:ta saadun vahingon verran
    public void takeDamage(int damage) {
        currentHealth -= damage;
    }

    // Tarkistaa, onko Lutemon elossa
    public boolean isAlive() {
        return currentHealth > 0;
    }

    // Kasvattaa treenipisteitä
    public void addTrainingPoint(int amount) {
        trainingPoints += amount;
    }

    // Kuluttaa yhden treenipisteen, jos mahdollista
    public boolean useTrainingPoint() {
        if (trainingPoints > 0) {
            trainingPoints--;
            return true;
        }
        return false;
    }

    // Kasvattaa hyökkäystä yhdellä
    public void increaseAttack() {
        this.attack++;
    }

    // Kasvattaa puolustusta yhdellä
    public void increaseDefense() {
        this.defense++;
    }

    // Kasvattaa maksimiterveyttä ja nykyterveyttä yhdellä
    public void increaseMaxHealth() {
        this.maxHealth++;
        this.currentHealth++;
    }

    // Kasvattaa taisteltujen taisteluiden määrää
    public void incrementBattlesFought() {
        battlesFought++;
    }

    // Kasvattaa voitettujen taisteluiden määrää
    public void incrementBattlesWon() {
        battlesWon++;
    }

    // Kasvattaa treenipäivien määrää
    public void incrementTrainingDays() {
        trainingDays++;
    }

    // Kasvattaa voittoja
    public void addWin() {
        battlesWon++;
    }

    // Kasvattaa taisteluita
    public void addFight() {
        battlesFought++;
    }

    // Palauttaa hyökkäyksen tehon kokemuksella vahvistettuna
    public int getEffectiveAttack() {
        return attack + experience;
    }

    // Palauttaa puolustuksen tehon kokemuksella vahvistettuna
    public int getEffectiveDefense() {
        return defense + experience;
    }
}