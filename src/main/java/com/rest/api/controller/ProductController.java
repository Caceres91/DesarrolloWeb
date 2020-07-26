package com.rest.api.controller;

import com.rest.api.entity.Product;
import com.rest.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(
            value = "/all",
            method = RequestMethod.GET,
            produces = "application/json")
    public ResponseEntity<List<Product>> getPerson(){
        List<Product> products = productRepository.findAll();
        return ResponseEntity.ok(products);
    }

    @RequestMapping(value = "{productId}",
            method = RequestMethod.GET,
            produces = "application/json")
    public ResponseEntity<Product> getPersonById(@PathVariable Integer productId){
        Optional<Product> productOptional = productRepository.findById(productId);
        return productOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @RequestMapping(
            value = "/create",
            method = RequestMethod.POST,
            produces = "application/json")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product product1 = productRepository.save(product);
        return ResponseEntity.ok(product1);
    }

    @RequestMapping(
            value = "/update",
            method = RequestMethod.PUT,
            produces = "application/json")
    public ResponseEntity<Product> updatePerson(@RequestBody Product product){
        Optional<Product> productOptional = productRepository.findById(product.getId());
        if(productOptional.isPresent()) {
            Product updatePersona = productOptional.get();
            updatePersona.setNombre(product.getNombre());
            updatePersona.setDescripcion(product.getDescripcion());
            updatePersona.setPrecio(product.getPrecio());
            productRepository.save(updatePersona);
            return ResponseEntity.ok(updatePersona);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(
            value = "{productId}",
            method = RequestMethod.DELETE,
            produces = "application/json")
    public ResponseEntity<Void> deletePerson(@PathVariable Integer productId){
        productRepository.deleteById(productId);
        return ResponseEntity.ok(null);
    }

}
