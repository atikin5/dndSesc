package edu.nsu.dnd.model;

import edu.nsu.dnd.model.enums.Resistance;
import edu.nsu.dnd.model.enums.Size;
import edu.nsu.dnd.model.enums.TypeOfDamage;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class InteractiveObject extends MapObject{


    private int currentHealthPoints;
    private int maxHealthPoints;
    private int armorClass;
    private Map<TypeOfDamage, Resistance> resistances;
    private Size size;

    public InteractiveObject(String id, String name, Position position, int healthPoints, int armorClass, Map<TypeOfDamage, Resistance> resistances) {
        super(id, name, position);
        this.currentHealthPoints = healthPoints;
        this.armorClass = armorClass;
        this.resistances = resistances;
    }

    public void inflictDamage(int damage) {
        currentHealthPoints -= damage;
        if (currentHealthPoints <= 0) {
            currentHealthPoints = 0;
        }
    }
}
