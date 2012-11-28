package com.example.service;
import java.util.List;

import com.example.model.Person;
import com.force.api.Identity;

public interface PersonService {
    
    public void addPerson(Person person);
    public List<Person> listPeople();
    public void removePerson(String id);
    public Identity getUserLoggedIn();
}
