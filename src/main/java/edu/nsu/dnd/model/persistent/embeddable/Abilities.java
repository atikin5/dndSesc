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
        switch (ability) {
            case STRENGTH:
                return strength;
            case DEXTERITY:
                return dexterity;
            case CONSTITUTION:
                return constitution;
            case INTELLIGENCE:
                return intelligence;
            case WISDOM:
                return wisdom;
            case CHARISMA:
                return charisma;
        }
        return 0;
    }



    public int getAbilityModifierValue(Ability ability) {
        switch (ability) {
            case STRENGTH:
                return (strength - 10) / 2;
            case DEXTERITY:
                return (dexterity - 10) / 2;
            case CONSTITUTION:
                return (constitution - 10) / 2;
            case INTELLIGENCE:
                return (intelligence - 10) / 2;
            case WISDOM:
                return (wisdom - 10) / 2;
            case CHARISMA:
                return (charisma - 10) / 2;
        }
        return 0;
    }


}
