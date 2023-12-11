package it.itsvil.employee2.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name ="Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="id_employee")
    private Long n_idEmployee;

    @Column(name="nomeEmployee")
    private String t_nomeEmployee;

    @Column(name="cognomeEmployee")
    private String t_cognomeEmployee;

    @Column(name= "dataNascita")
    private LocalDate d_dataNascita;

    @Column (name = "username")
    private String t_username;

    @Column (name = "password")
    private String t_password;
}
