package com.castores.tarificador.entities.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class DataResponse implements Serializable {
	
	private static final long serialVersionUID = -2917444417064594988L;
	
	private String postalCode;
	private String city;

}
