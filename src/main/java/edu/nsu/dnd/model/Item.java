package edu.nsu.dnd.model;

import edu.nsu.dnd.model.enums.TypeOfDamage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class Item {

    private String id;
    private String name;
    private String description;
    private ArrayList<TypeOfDamage> commonDamage;
    private ArrayList<TypeOfDamage> optionalDamage;
    private ArrayList<TypeOfDamage> ifMissDamage;


}
