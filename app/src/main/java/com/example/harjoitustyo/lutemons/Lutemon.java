package com.example.harjoitustyo.lutemons;

public abstract class Lutemon {
    protected String name;
    protected String color;
    protected int attack;
    protected int defense;
    protected int experience;
    protected int maxHealth;
    protected int currentHealth;
    protected int image;
    protected int trainingPoints;
    public abstract void resetStats();
    protected String location = "home";
    protected int battlesFought = 0;
    protected int battlesWon = 0;
    protected int trainingDays = 0;
    private String type;

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

    private String determineTypeFromColor(String color) {
        switch (color.toLowerCase()) {
            case "green":
                return "grass";
            case "orange":
                return "fire";
            case "pink":
                return "fairy";
            case "black":
                return "shadow";
            case "white":
                return "normal";
            default:
                return "unknown";
        }
    }

    public String getType() {
        return type;
    }

    public String getName() {

        return name;
    }

    public String getColor() {

        return color;
    }

    public int getAttack() {

        return attack;
    }

    public int getDefense() {

        return defense;
    }

    public int getExperience() {

        return experience;
    }

    public int getMaxHealth() {

        return maxHealth;
    }

    public int getCurrentHealth() {

        return currentHealth;
    }

    public int getImage() {

        return image;
    }

    public void addExperience(int i) {

        experience+= i;
    }

    public void resetHealth() {

        currentHealth = maxHealth;
    }

    public void takeDamage(int damage) {

        currentHealth -= damage;
    }

    public boolean isAlive() {

        return currentHealth > 0;
    }

    public String getLocation() {

        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTrainingPoints() {
        return trainingPoints;
    }

    public void addTrainingPoint(int amount) {
        trainingPoints += amount;
    }

    public boolean useTrainingPoint() {
        if (trainingPoints > 0) {
            trainingPoints--;
            return true;
        }
        return false;
    }

    public void increaseAttack() {
        this.attack++;
    }

    public void increaseDefense() {
        this.defense++;
    }

    public void increaseMaxHealth() {
        this.maxHealth++;
        this.currentHealth++;
    }

    public void incrementBattlesFought() {
        battlesFought++;
    }

    public void incrementBattlesWon() {
        battlesWon++;
    }

    public void incrementTrainingDays() {
        trainingDays++;
    }

    public int getBattlesFought() {
        return battlesFought;
    }

    public int getBattlesWon() {
        return battlesWon;
    }

    public int getTrainingDays() {
        return trainingDays;
    }
    public void addWin() {
        battlesWon++;
    }
    public void addFight() {
        battlesFought++;
    }
    public int getWins() {
        return battlesWon;
    }
    public int getFights() {
        return battlesFought;
    }

    public int getEffectiveAttack() {
        return attack + experience;
    }

    public int getEffectiveDefense() {
        return defense + experience;
    }

    public void setType(String type) {
        this.type = type;
    }

}
