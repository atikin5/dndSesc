package edu.nsu.dnd.model;

import edu.nsu.dnd.model.type.Status;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class InteractiveObject extends MapObject{

    private int healthPoints;
    private int armorClass;
    private Status status;

    public InteractiveObject(int id, String name, Position position, int healthPoints, int armorClass, Status status) {
        super(id, name, position);
        this.healthPoints = healthPoints;
        this.armorClass = armorClass;
        this.status = status;
    }

    public void inflictDamage(int damage) {
        healthPoints -= damage;
        if (healthPoints <= 0) {
            healthPoints = 0;
        }
    }
}
