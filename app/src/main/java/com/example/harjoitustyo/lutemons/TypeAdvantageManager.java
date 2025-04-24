package com.example.harjoitustyo.lutemons;

import java.util.*;

// Hallitsee tyyppien vahvuus- ja heikkoussuhteita pelin taistelumekaniikassa
public class TypeAdvantageManager {

    private static final Map<String, Set<String>> strengths = new HashMap<>();
    private static final Map<String, Set<String>> weaknesses = new HashMap<>();

    static {
        // Määritetään, mitkä tyypit ovat vahvoja mitäkin vastaan
        strengths.put("fairy", setOf("fire", "normal"));
        strengths.put("fire", setOf("grass", "shadow"));
        strengths.put("grass", setOf("normal", "fairy"));
        strengths.put("normal", setOf("shadow", "fire"));
        strengths.put("shadow", setOf("fairy", "grass"));

        // Johdetaan heikkoudet automaattisesti vahvuuksien perusteella, jotta tiedot pysyvät synkassa
        generateWeaknessesFromStrengths();
    }

    // Luo joukko annetuista merkkijonoista
    private static Set<String> setOf(String... values) {
        return new HashSet<>(Arrays.asList(values));
    }

    // Luo heikkouksien kartan kääntämällä vahvuuksien kartta
    private static void generateWeaknessesFromStrengths() {
        for (String attacker : strengths.keySet()) {
            for (String target : strengths.get(attacker)) {
                weaknesses.putIfAbsent(target, new HashSet<>());
                weaknesses.get(target).add(attacker);
            }
        }
    }

    // Palauttaa kertoimen, joka määrittää hyökkäyksen tehon hyökkääjän ja puolustajan tyypin perusteella
    public static double getTypeMultiplier(String attackerType, String defenderType) {
        if (strengths.containsKey(attackerType) && strengths.get(attackerType).contains(defenderType)) {
            return 1.25;
        }
        if (weaknesses.containsKey(attackerType) && weaknesses.get(attackerType).contains(defenderType)) {
            return 0.75;
        }
        return 1.0;
    }

    // Palauttaa tyypit, joita vastaan annettu tyyppi on vahva
    public static String[] getStrengths(String type) {
        Set<String> set = strengths.getOrDefault(type, new HashSet<>());
        return set.toArray(new String[0]);
    }

    // Palauttaa tyypit, joita vastaan annettu tyyppi on heikko
    public static String[] getWeaknesses(String type) {
        Set<String> set = weaknesses.getOrDefault(type, new HashSet<>());
        return set.toArray(new String[0]);
    }
}