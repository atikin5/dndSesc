package edu.nsu.dnd.model;

import edu.nsu.dnd.model.enums.TypeOfDamage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Map;

@Getter
@Setter
public class Item extends MapObject{

    private String name;
    private String description;
    private Boolean handPosition;
    private ArrayList<TypeOfDamage> commonDamage;
    private ArrayList<TypeOfDamage> optionalDamage;
    private ArrayList<TypeOfDamage> ifMissDamage;

    public Item(String type, Position position, String name, String description, Boolean handPosition, ArrayList<TypeOfDamage> commonDamage, ArrayList<TypeOfDamage> optionalDamage, ArrayList<TypeOfDamage> ifMissDamage) {
        super(type, position);
        this.name = name;
        this.description = description;
        this.handPosition = handPosition;
        this.commonDamage = commonDamage;
        this.optionalDamage = optionalDamage;
        this.ifMissDamage = ifMissDamage;
    }

}
