package com.castores.tarificador.entities;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;

@Data
public class Ruta implements Serializable {

	/**
	 * Serial ID - para que un programa pueda convertir este objeto en byte
	 */
    private static final long serialVersionUID = -2480061426727474185L;

    @NotNull(message="id no puede faltar ni estar vacío")
    @Size(min=1, message="El id no debe de estar vacio")
    private String id;

    //@NotNull(message="idCliente no puede faltar ni estar vacío")
    private String idCliente;

    @NotNull(message="idOficina no puede faltar ni estar vacío")
    private String idOficina;


}
