package com.test.tarificador.entities.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class ResponseRate implements Serializable {

    private static final long serialVersionUID = -3586638117657049973L;

    private Map mapImportes;

}
