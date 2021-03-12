package com.castores.tarificador.entities;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

@Data
public class AdditionalServices implements Serializable {

	/**
	 * Serial ID - para que un programa pueda convertir este objeto en byte
	 */
    private static final long serialVersionUID = -953850582435184274L;

    /**
     * Valor declarado de una cotización
     */
    @NotNull
    @Range(min=1000, max=10000000, message = "El valor declarado debe de ser de minimo $1000")
    @Positive(message = "El valor declarado debe de ser mayor a 0.")
    private Double valorDeclarado;


}
