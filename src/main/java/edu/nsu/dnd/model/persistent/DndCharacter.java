package edu.nsu.dnd.model.persistent;

import edu.nsu.dnd.model.enums.*;
import edu.nsu.dnd.model.persistent.base.Alive;
import edu.nsu.dnd.model.persistent.embeddable.CharacterClass;
import edu.nsu.dnd.model.persistent.embeddable.CharacterDescription;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dnd_character")
@Getter
@Setter
public class DndCharacter extends Alive {

    @ElementCollection
    private List<CharacterClass> characterClasses = new ArrayList<>();
    private CharacterDescription characterDescription;

    @Transient
    private int reviveSuccesses = 0;

    @Transient
    private int reviveFailures = 0;

    @Override
    public void inflictDamage(int damage, boolean critical) {
        if (getConditions().contains(Condition.UNCONSCIOUS)) {
            if (getMaxHealthPoints() <= damage) {
                setOperational(false);
            }
            else {
                if (critical) {
                    reviveFailures += 2;
                }
                else {
                    reviveFailures += 1;
                }
                if (reviveFailures >= 3) {
                    setOperational(false);
                }
            }
        }
        if (isOperational() && !getConditions().contains(Condition.UNCONSCIOUS)) {
            if (getTemporaryHealthPoints() >= damage) {
                setTemporaryHealthPoints(getTemporaryHealthPoints() - damage);
            } else {
                setCurrentHealthPoints(getCurrentHealthPoints() - damage + getTemporaryHealthPoints());
                setTemporaryHealthPoints(0);
                if (getCurrentHealthPoints() <= 0) {
                    setCurrentHealthPoints(0);
                    getConditions().add(Condition.UNCONSCIOUS);
                }
            }
        }
    }

    public void deathSavingTrow(Boolean critical) {
        if (critical == null) {
            reviveFailures += 2;
            if (reviveFailures >= 3) {
                setOperational(false);
            }
        }
        else {
            if (critical) {
                reviveSuccesses += 2;
            }
            else {
                reviveSuccesses += 1;
            }
        }
    }


}
