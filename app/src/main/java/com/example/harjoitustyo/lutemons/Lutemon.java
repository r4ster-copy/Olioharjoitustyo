package com.example.harjoitustyo.lutemons;

import java.io.Serializable;

// Abstract superclass for all Lutemon objects
// Defines basic information and behavior for Lutemons in the game
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

    // Abstract method to reset stats (implementation in subclasses)
    public abstract void resetStats();

    // Constructor
    // Creates a new Lutemon and initializes all fields with the provided values
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

    // Determines the Lutemon's type based on its color
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

    // Getters and setters

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

    // Increases Lutemon's experience points
    public void addExperience(int i) {
        experience += i;
    }

    // Resets Lutemon's health to maximum
    public void resetHealth() {
        currentHealth = maxHealth;
    }

    // Reduces HP by the amount of damage taken
    public void takeDamage(int damage) {
        currentHealth -= damage;
    }

    // Checks if the Lutemon is still alive
    public boolean isAlive() {
        return currentHealth > 0;
    }

    // Increases training points
    public void addTrainingPoint(int amount) {
        trainingPoints += amount;
    }

    // Consumes one training point if available
    public boolean useTrainingPoint() {
        if (trainingPoints > 0) {
            trainingPoints--;
            return true;
        }
        return false;
    }

    // Increases attack by one
    public void increaseAttack() {
        this.attack++;
    }

    // Increases defense by one
    public void increaseDefense() {
        this.defense++;
    }

    // Increases both max health and current health by one
    public void increaseMaxHealth() {
        this.maxHealth++;
        this.currentHealth++;
    }

    // Increases the number of battles fought
    public void incrementBattlesFought() {
        battlesFought++;
    }

    // Increases the number of battles won
    public void incrementBattlesWon() {
        battlesWon++;
    }

    // Increases the number of training days
    public void incrementTrainingDays() {
        trainingDays++;
    }

    // Adds a win
    public void addWin() {
        battlesWon++;
    }

    // Adds a fight
    public void addFight() {
        battlesFought++;
    }

    // Returns the effective attack power boosted by experience
    public int getEffectiveAttack() {
        return attack + experience;
    }

    // Returns the effective defense power boosted by experience
    public int getEffectiveDefense() {
        return defense + experience;
    }
}