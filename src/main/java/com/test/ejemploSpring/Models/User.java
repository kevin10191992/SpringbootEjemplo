package com.test.ejemploSpring.Models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@ToString
@Table(name = "[User]")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId", nullable = false)
    public Long Id;
    public String Nombre;
    public String Apellido;
    public int Edad;
    public String FechaNacimiento;
    public String Hash;
}
