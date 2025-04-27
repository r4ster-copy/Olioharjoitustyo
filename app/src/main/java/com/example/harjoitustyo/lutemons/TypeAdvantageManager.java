package com.example.harjoitustyo.lutemons;

import java.util.*;

// Manages type strengths and weaknesses in the game's battle mechanics
public class TypeAdvantageManager {

    private static final Map<String, Set<String>> strengths = new HashMap<>();
    private static final Map<String, Set<String>> weaknesses = new HashMap<>();

    static {
        // Defines which types are strong against which
        strengths.put("fairy", setOf("fire", "normal"));
        strengths.put("fire", setOf("grass", "shadow"));
        strengths.put("grass", setOf("normal", "fairy"));
        strengths.put("normal", setOf("shadow", "fire"));
        strengths.put("shadow", setOf("fairy", "grass"));

        // Derives weaknesses automatically from strengths to keep data consistent
        generateWeaknessesFromStrengths();
    }

    // Creates a set from given strings
    private static Set<String> setOf(String... values) {
        return new HashSet<>(Arrays.asList(values));
    }

    // Generates the weaknesses map by reversing the strengths map
    private static void generateWeaknessesFromStrengths() {
        for (String attacker : strengths.keySet()) {
            for (String target : strengths.get(attacker)) {
                weaknesses.putIfAbsent(target, new HashSet<>());
                weaknesses.get(target).add(attacker);
            }
        }
    }

    // Returns a multiplier that determines the effectiveness of an attack based on attacker and defender types
    public static double getTypeMultiplier(String attackerType, String defenderType) {
        if (strengths.containsKey(attackerType) && strengths.get(attackerType).contains(defenderType)) {
            return 1.25;
        }
        if (weaknesses.containsKey(attackerType) && weaknesses.get(attackerType).contains(defenderType)) {
            return 0.75;
        }
        return 1.0;
    }

    // Returns types that the given type is strong against
    public static String[] getStrengths(String type) {
        Set<String> set = strengths.getOrDefault(type, new HashSet<>());
        return set.toArray(new String[0]);
    }

    // Returns types that the given type is weak against
    public static String[] getWeaknesses(String type) {
        Set<String> set = weaknesses.getOrDefault(type, new HashSet<>());
        return set.toArray(new String[0]);
    }
}