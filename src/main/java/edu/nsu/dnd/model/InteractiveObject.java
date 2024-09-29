package edu.nsu.dnd.model;

import edu.nsu.dnd.model.type.Status;

public class InteractiveObject extends MapObject{

    private int healthPoints;
    private int armorClass;
    private Status status;

    public InteractiveObject(int id, String name, int healthPoints, int armorClass) {
        super(id, name);
        this.healthPoints = healthPoints;
        this.armorClass = armorClass;

    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }

    public void inflictDamage(int damage) {
        healthPoints -= damage;
        if (healthPoints <= 0) {
            healthPoints = 0;
        }
    }
}
