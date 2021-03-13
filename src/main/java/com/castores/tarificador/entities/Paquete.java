package com.castores.tarificador.entities;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Positive;
import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class Paquete implements Serializable {

	/**
	 * Serial ID - para que un programa pueda convertir este objeto en byte
	 */
    private static final long serialVersionUID = 2422908149291474699L;

    /**
     * Cantidad de piezas a cotizar
     */
    @Range(min=1, max=1, message = "Sólo se puede cotizar 1 producto para mensajeria")
    @Positive(message = "La cantidad debe ser mayor a 1.")
    private Integer cantidad;
    
    /**
     * IdEmpaque, lo toma de la tabla: clientes.empaques
     */
    @Positive(message = "El idEmpaque debe de ser mayor a 0.")
    private Integer idEmpaque;
    
    /**
     * Contenido de paquete
     */
    @NotNull(message="contenido no puede faltar ni estar vacío")
    @Size(min=5, message="El contenido debe tener mas de 5 caracteres")
    private String contenido;
    
    /**
     * Peso en Kilogramos. (Posteriomente hace la conversion a toneldas)
     */
    @Positive(message = "El peso del paquete debe de ser mayor a 0.")
    @Range(min=1, max=10, message = "El peso no debe de exceder los 10 Kilogramos")
    private Double peso;
    
    /**
     * Alto del paquete en Centrimetros. 
     */
    @Positive(message = "La altura del paquete debe de ser mayor a 0.")
    private Double alto;
    
    /***
     * Ancho de paquete en Centrimetros.
     */
    @Positive(message = "El ancho del paquete debe de ser mayor a 0.")
    private Double ancho;
    
    /***
     * Largo de paquete en Centrimetros.
     */
    @Positive(message = "El largo del paquete debe de ser mayor a 0.")
    private Double largo;

  
 
}