package com.castores.tarificador.service;

import java.util.Map;

import com.castores.tarificador.entities.Convenio;
import com.castores.tarificador.entities.Mensajeria;

public interface IRateService {

    /**
     * Envio de metodo a Tarificador.jar
     * @param mensajeria
     * @return
     */
    String cotizarPaqueteria(Mensajeria mensajeria);

    /**
     * Validata que tenga credito el cliente.
     * @param idCliente
     * @param idOficina
     * @return
     */
    Double tieneCredito(Integer idCliente, Integer idOficina);
    
   /**
    * Valida que sea de paquete de tipo mensajeria
    * @param paqueteId
    * @return
    */
    boolean validatePaqueteId(Integer paqueteId);
    
    /**
     * Convierte de String a Map de Importes
     * @param s
     * @return
     */
     Map convertStringToMap(String s);
  
     
     /**
      * Obtiene la version y el numero de convenio.
      * @param idCliente
      * @param idIficina
      * @return
      */
     Convenio getConvenio(Integer idCliente,Integer idIficina);
     
     /**
      * Validar que las dimensiones del paquete no pasen los 115 cm.
      */
     boolean validaDimensiones(Double alto,Double ancho,Double largo);
     
     /**
      * Obtener el id del cliente, pasando el nombre.
      * @param nombre
      * @return
      */
     Integer obtenerIdCliente(String nombre);
     
}
