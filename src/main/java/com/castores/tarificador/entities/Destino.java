package com.castores.tarificador.entities;

import lombok.Data;
import java.io.Serializable;

@Data
public class Destino extends Ruta implements Serializable {

	/**
	 * Serial ID - para que un programa pueda convertir este objeto en byte
	 */
    private static final long serialVersionUID = 5427877995895381544L;
}
