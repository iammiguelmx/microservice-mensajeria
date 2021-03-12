package com.test.tarificador.entities.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class AuthenticationResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;

    private String jwt;


}