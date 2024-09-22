package edu.nsu.dnd.model;

import java.util.List;

public class Person {

    private String name;
    private int age;
    private List<Position> movement;

    public String getName() {
        movement.get(x).get(y);
        try {
            movement.get(5);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
