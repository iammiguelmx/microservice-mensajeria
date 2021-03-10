package com.test.tarificador.entities;

import lombok.Data;

import java.io.Serializable;

@Data
public class ObtenerTarifa implements Serializable {

    private String idOrigen;
    private String idDestino;
    private String valorDeclarado;
    private String tasaIva;
    private String quimico;
    private String idMoneda;
    private String ocurre;
    //@Embedded
    //private Packages packages;
    private String cad;
    private String idConvenio;
    private String versionConvenio;
    private String completo;
    private String idUnidad;
    private String mensajeria;
    private String idRemitente;
    private String idOficinaRemitente;
    private  String idOficinaDestinatario;
    private String rfc;
    private String dependecia;
    private String quienPaga;
    private String idOficina;
    private String codigo;
    private String porCodigo;
    private String intClasificacionDoc;
    private String EyE;


}
