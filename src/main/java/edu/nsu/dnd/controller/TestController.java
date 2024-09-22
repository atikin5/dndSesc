package edu.nsu.dnd.controller;

import edu.nsu.dnd.model.Person;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/name")
public class TestController {

    @GetMapping("/hello")
    public String helloGet() {
        return "Hello";
    }

    @GetMapping("/hello/{name}/{age}")
    public Person helloGet(@PathVariable String name, @PathVariable int age) {
        Person person = new Person();
        person.setName(name);
        person.setAge(age);
        return person;
    }

    @PostMapping("/hello")
    @ResponseStatus(HttpStatus.CREATED)
    public String helloPost(@RequestParam String title, @RequestBody String name) {
        if (name == null || name.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name is empty");
        }
        if (title == null || title.isEmpty()) {
            return "Hello " + name;
        } else {
            return "Hello " + title + " " + name;
        }
    }

    @PostMapping("/hello/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public String helloPost(@PathVariable String name) {
        return "Hello " + name;
    }

    @PostMapping("/hello/Bill")
    @ResponseStatus(HttpStatus.CREATED)
    public String helloPost() {
        return "Hello Bill static";
    }

    @PostMapping("/hello/person")
    public String helloPost(@RequestBody Person person) {
        return "Hello " + person.getName() + " of age " + person.getAge();
    }
}
