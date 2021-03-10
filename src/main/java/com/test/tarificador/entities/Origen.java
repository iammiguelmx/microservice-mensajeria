package com.test.tarificador.entities;

import lombok.Data;
import java.io.Serializable;

@Data
public class Origen extends Ruta implements Serializable {
	
	/**
	 * Serial ID - para que un programa pueda convertir este objeto en byte
	 */
	private static final long serialVersionUID = 1187115669123476678L;

}
