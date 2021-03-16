package com.castores.tarificador.controller;

import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.castores.tarificador.entities.Mensajeria;
import com.castores.tarificador.entities.dto.DataResponse;
import com.castores.tarificador.service.IRateService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/mensajeria")
@Api(tags = "Rate")
@Slf4j
/**
 * Endpoints: 
 * test: http://localhost:8080/api/cotizar/mensajeria
 * pdn:  http://localhost:{PORT}/api/cotizar/mensajeria
 * @author Miguel Angel Camacho Campos
 *
 */
public class RateController {
	
    @Autowired
    private IRateService iRateService;

   
    @PostMapping("/cotizar")
    public ResponseEntity<?> paqueteria(@Valid @RequestBody Mensajeria mensajeria) {
    	Map<String, Object> params = new HashMap<>();
    	DataResponse to = new DataResponse();
    	DataResponse from = new DataResponse();	
        String importes=null;
        Map  mapFrom, mapError, mapData = new HashMap<String,String>();
        Map mapImportes = new HashMap<String,Double>();
        
        //Si regresa 0.0 no se encontro un registro de este cliente con un credito
        Double saldoCredito=iRateService.tieneCredito(Integer.parseInt(mensajeria.getOrigen().getIdCliente()),Integer.parseInt(mensajeria.getOrigen().getIdOficina()));
        log.info("tieneCredito: ->" + saldoCredito);
        
        //Validate paqueteID mensajería.
        boolean isValid = iRateService.validatePaqueteId(mensajeria.getPackages().getIdEmpaque()); 
        log.info("validatePaqueteId: ->" + isValid);
        
        //No soberpasa las dimensiones de mensajeria
        boolean dimensionesMensajeria =  iRateService.validaDimensiones(mensajeria.getPackages().getAlto(),mensajeria.getPackages().getAncho(),mensajeria.getPackages().getLargo());
        log.info("dimensionesMensajeria: ->" + dimensionesMensajeria);
        
        if (isValid && dimensionesMensajeria && saldoCredito!= 0.0) {
        	try {
	            importes = iRateService.cotizarPaqueteria(mensajeria);
	            mapImportes = iRateService.convertStringToMap(importes);
        	} catch (Exception e) {
        		params.put("message", "Se produjo un error al realizar la cotización.");
    			params.put("error", e.getMessage().concat(": ").concat(e.getMessage()));
    			return new ResponseEntity<>(params, HttpStatus.INTERNAL_SERVER_ERROR);
        	}
	            //From 
	            to.setCity(mensajeria.getOrigen().getCity());
	            to.setPostalCode(mensajeria.getOrigen().getPostalCode());
	            //To
	            from.setCity(mensajeria.getDestino().getCity());
	            from.setPostalCode(mensajeria.getDestino().getPostalCode());
	            

	            mapData.put("from", from);
	            mapData.put("to", to);
	            mapData.put("rate_details", mapImportes);
        } else {
        	//Response with entity error.
        	if (!isValid) {
        		params.put("message", "Parametros invalidos para cotización.");
    			params.put("error", "No es un paquete de tipo Mensajeria.");
    			return new ResponseEntity<>(params, HttpStatus.BAD_REQUEST);
        	}else if (!dimensionesMensajeria) {
        		params.put("message", "Sobrepasa las dimensiones.");
    			params.put("error", "Las dimensiones del paquete sobrepasan los 115 cm.");
    			return new ResponseEntity<>(params, HttpStatus.BAD_REQUEST);
        	}else {
        		params.put("message", "Credito detalles.");
    			params.put("error", "El cliente Id no tiene credito o su limite es menos a 0.");
    			return new ResponseEntity<>(params, HttpStatus.BAD_REQUEST);
        	}
        }
        return new ResponseEntity(mapData,HttpStatus.OK);
    }

}
