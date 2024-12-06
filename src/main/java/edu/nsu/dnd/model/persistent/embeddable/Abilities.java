package edu.nsu.dnd.model.persistent.embeddable;

import edu.nsu.dnd.model.enums.Ability;
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


    /** TODO exception
     */

    public int getAbilityValue(Ability ability) {
        return switch (ability) {
            case STRENGTH -> strength;
            case DEXTERITY -> dexterity;
            case CONSTITUTION -> constitution;
            case INTELLIGENCE -> intelligence;
            case WISDOM -> wisdom;
            case CHARISMA -> charisma;
        };
    }



    public int getAbilityModifierValue(Ability ability) {
        return switch (ability) {
            case STRENGTH -> (strength - 10) / 2;
            case DEXTERITY -> (dexterity - 10) / 2;
            case CONSTITUTION -> (constitution - 10) / 2;
            case INTELLIGENCE -> (intelligence - 10) / 2;
            case WISDOM -> (wisdom - 10) / 2;
            case CHARISMA -> (charisma - 10) / 2;
        };
    }


}
