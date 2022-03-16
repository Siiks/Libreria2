package com.example.libreria.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Autor {
    @Id
    private String dni;
    @NotNull
    private String nombre;
    @NotNull
    private String apellido1;
    private String apellido2;
    private String telefono;
    @Email
    private String email;
}
