package org.bahadir.controller;

import org.bahadir.entity.Person;
import org.bahadir.service.PersonService;

public class PersonController {
    private final PersonService personService;

    public PersonController() {
        this.personService = new PersonService();
    }

    public void register(Person person){
        personService.register(person);
    }
    public void update(int id){
        personService.update(id);
    }
    public void delete(int id){
        personService.delete(id);
    }
    public void findById(int id){
        personService.findById(id);
    }
    public void getAllPersons(){
        personService.getAllPersons();
    }
}
