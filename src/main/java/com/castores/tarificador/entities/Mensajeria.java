package com.castores.tarificador.entities;

import lombok.Data;


import javax.persistence.Embedded;
import javax.validation.Valid;

import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;


@Cacheable(value = "mensajeria")
@Data
public class Mensajeria implements Serializable {

	/**
	 * Serial ID - para que un programa pueda convertir este objeto en byte
	 */
    private static final long serialVersionUID = -7627419861141112060L;

    @Valid
    @Embedded
    private Cliente origen;

    @Valid
    @Embedded
    private Cliente destino;

    @Valid
    @Embedded
    private AdditionalServices additionalServices;

    @Valid
    @Embedded
    private Paquete packages;
    

}
