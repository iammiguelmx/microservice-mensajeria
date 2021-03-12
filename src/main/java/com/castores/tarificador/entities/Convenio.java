package com.castores.tarificador.entities;

import java.io.Serializable;

import lombok.Data;

import io.swagger.annotations.ApiModel;

@Data
@ApiModel(value = "Convenio", description = "Modelo de convenio")
public class Convenio implements Serializable {

	/**
	 * Serial ID - para que un programa pueda convertir este objeto en byte
	 */
    private static final long serialVersionUID = -6889283340304609283L;
    
    /**
     * id del convenio
     * @apiNote tabla: clientes.convenios_tyc
     */
    private String idConvenio;

    /**
     * Version del convenio
     * @apiNote tabla: clientes.convenios_tyc
     */
    private String versionConvenio;

}
