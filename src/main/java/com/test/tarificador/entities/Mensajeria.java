package com.test.tarificador.entities;

import lombok.Data;
import javax.persistence.Embedded;
import javax.validation.Valid;


import java.io.Serializable;

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
