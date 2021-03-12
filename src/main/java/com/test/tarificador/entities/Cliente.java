package com.test.tarificador.entities;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import javax.validation.constraints.NotNull;

@Data
public class Cliente extends Origen implements Serializable {

	/**
	 * Serial ID - para que un programa pueda convertir este objeto en byte
	 */
    private static final long serialVersionUID = 7022760591514767812L;

    /**
	 * Nombre de cliente
	 */
    private String name;
    
    /**
   	 * Nombre de compañia del cliente
   	 */
    @NotNull(message="company no puede faltar ni estar vacío")
    @Size(min=10, message="El nombre de la compañia debe tener mas de 10 caracteres")
    private String company;

    /**
     * Email del cliente
     */
    @Email(message = "El correo electrónico debe ser válido")
    private String email;

    /**
     * Número telefonico del cliente
     */
    @Size(min = 10, max = 10, message = "El número telefonico debe de tener 10 caracteres")
    private String phone;
    
    
    private String street;
    private String extNumber;
    private String intNumber;
    private String district;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private String reference;

}
