package com.rest.api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String apellido;
    @Column(nullable = false)
    private int telefono;
    @Column(nullable = false)
    private int dpi;
    @Column(nullable = false)
    private String cuidad;
    @Column(nullable = false)
    private String Genero;
    @Column(nullable = false)
    private int tarjeta;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;


}
