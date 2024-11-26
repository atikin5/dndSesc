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
            if (getMaxHp() <= damage) {
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
            if (getTemporaryHp() >= damage) {
                setTemporaryHp(getTemporaryHp() - damage);
            } else {
                setCurrentHp(getCurrentHp() - damage + getTemporaryHp());
                setTemporaryHp(0);
                if (getCurrentHp() <= 0) {
                    setCurrentHp(0);
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
