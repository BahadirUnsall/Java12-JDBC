package org.bahadir.service;

import org.bahadir.entity.Person;
import org.bahadir.repository.PersonRepository;

public class PersonService {
    private final PersonRepository personRepository;


    public PersonService() {
        this.personRepository = new PersonRepository();
    }

    public void register(Person person){
        personRepository.createPerson(person);
    }
    public void update(int id){
        personRepository.updatePerson(id);
    }
    public void delete(int id){
        personRepository.deletePersonById(id);
    }
    public void findById(int id){
        personRepository.findPersonById(id);
    }
    public void getAllPersons(){
        personRepository.getAllPersons();
    }
}
