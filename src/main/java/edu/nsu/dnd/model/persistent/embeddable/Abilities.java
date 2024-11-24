package edu.nsu.dnd.model.persistent.embeddable;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Abilities {

    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    public int getStrengthModifier() {
        return (strength - 10) / 2;
    }

    public int getDexterityModifier() {
        return (dexterity - 10) / 2;
    }

    public int getConstitutionModifier() {
        return (constitution - 10) / 2;
    }

    public int getIntelligenceModifier() {
        return (intelligence - 10) / 2;
    }

    public int getWisdomModifier() {
        return (wisdom - 10) / 2;
    }

    public int getCharismaModifier() {
        return (charisma - 10) / 2;
    }

}
