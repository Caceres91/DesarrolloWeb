package com.rest.api.controller;

import com.rest.api.entity.Person;
import com.rest.api.entity.Product;
import com.rest.api.repository.PersonRepository;
import com.rest.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping(
            value = "/all",
            method = RequestMethod.GET,
            produces = "application/json")
    public ResponseEntity<List<Person>> getPerson(){
        List<Person> person = personRepository.findAll();
        return ResponseEntity.ok(person);
    }

    @RequestMapping(
            value = "{personId}",
            method = RequestMethod.GET,
            produces = "application/json")
    public ResponseEntity<Person> getPersonById(@PathVariable Integer personId){
        Optional<Person> productOptional = personRepository.findById(personId);
        return productOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @RequestMapping(
            value = "/create",
            method = RequestMethod.POST,
            produces = "application/json")
    public ResponseEntity<Person> createProduct(@RequestBody Person person){
        Person product1 = personRepository.save(person);
        return ResponseEntity.ok(person);
    }

    @RequestMapping(
            value = "/update",
            method = RequestMethod.PUT,
            produces = "application/json")
    public ResponseEntity<Person> updatePerson(@RequestBody Person person){
        Optional<Person> optionalPerson = personRepository.findById(person.getId());
        if(optionalPerson.isPresent()) {
            Person updatePersona = optionalPerson.get();
            updatePersona.setNombre(person.getNombre());
            updatePersona.setUsername(person.getUsername());
            updatePersona.setApellido(person.getApellido());
            updatePersona.setEmail(person.getEmail());
            updatePersona.setDpi(person.getDpi());
            updatePersona.setTarjeta(person.getTarjeta());
            updatePersona.setCuidad(person.getCuidad());
            updatePersona.setGenero(person.getGenero());
            updatePersona.setTelefono(person.getTelefono());
            personRepository.save(updatePersona);
            return ResponseEntity.ok(updatePersona);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @RequestMapping(
//            value = "/update/password",
//            method = RequestMethod.PUT,
//            produces = "application/json")
//    public ResponseEntity<Person> updatePassword(@RequestBody Person person){
//        Optional<Person> optionalPassword = personRepository.findById(person.getId());
//        if(optionalPassword.isPresent()) {
//            Person updatePassword = optionalPassword.get();
//            updatePassword.setPassword(person.getPassword());
//            return ResponseEntity.ok(updatePassword);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @RequestMapping(
            value = "{personId}",
            method = RequestMethod.DELETE,
            produces = "application/json")
    public ResponseEntity<Void> deletePerson(@PathVariable Integer personId){
        personRepository.deleteById(personId);
        return ResponseEntity.ok(null);
    }

}
