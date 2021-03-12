package com.castores.tarificador.repository.impl;

import com.castores.tarificador.control.Facturacion;
import com.castores.tarificador.entities.Convenio;
import com.castores.tarificador.entities.Mensajeria;
import com.castores.tarificador.repository.IRateRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

@Repository
public class RateRepositoryImpl implements IRateRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Value("${query.tarificador.consultaCredito}")
	private String consultaCredito;
	
	@Value("${query.tarificador.getPaquetesMensajeria}")
	private String paquetesValidosMsj;
	
	@Value("${query.tarificafor.getConvenio}")
	private String consultarConvenio;
	
	@Value("${valor.dimensiones.mensajeria}")
	private Double valorDimensionesMensajeria;
	
	@Value("${valores.paquetes.mensajeria.ids}")
	private int idPaquetesMensajeria[];
	
	@Value("${query.tarificador.getIdClienteOrigen}")
	private String obteneridClienteOrigen;
	
    /**
     *
     * @param mensajeria
     * @return list cotizaciones
     * 
     */
    @Override
    public String cotizarPaqueteria(Mensajeria mensajeria) {
    	Double toneladas=(mensajeria.getPackages().getPeso())/1000;
    	Double mst3=(mensajeria.getPackages().getAlto()*mensajeria.getPackages().getAncho()*mensajeria.getPackages().getLargo())/1000;
    	String cadenaContenido =  mensajeria.getPackages().getCantidad()+"<>"+mensajeria.getPackages().getIdEmpaque()+"<>"+
        		mensajeria.getPackages().getContenido()+"<>"+toneladas+"<>0.0010<>";
    	Convenio convenio =new Convenio();
    	convenio = getConvenio(Integer.parseInt(mensajeria.getOrigen().getIdCliente()),Integer.parseInt(mensajeria.getOrigen().getIdOficina()));
    	
  
        return Facturacion.obtieneTarifas(
        		mensajeria.getOrigen().getId(),
                mensajeria.getDestino().getId(),
                String.valueOf(mensajeria.getAdditionalServices().getValorDeclarado()),
                "16.00","1", "1","false",cadenaContenido,
                convenio.getIdConvenio(), convenio.getVersionConvenio(), "false",
                "0","true",mensajeria.getOrigen().getIdCliente(), mensajeria.getOrigen().getIdOficina(),
                "0","0", "0", "false", "1", "1104","false", "0","8","0");
    }


    @SuppressWarnings("deprecation")
	@Override
    public Double tieneCredito(Integer idCliente, Integer idOficina) {
        try {
            return jdbcTemplate.queryForObject(consultaCredito,new Object[]{idCliente, idOficina}, Double.class);
        } catch (EmptyResultDataAccessException e){
            return 0.0;
        }
    }

	@Override
	public boolean validatePaqueteId(Integer paqueteId) {
		boolean contains = Arrays.stream(idPaquetesMensajeria).anyMatch(paqueteId::equals);
		return contains;
	}

	/**
	 * Convierte de String a una List<String>
	 * Convierte de List<String> a List<Double>
	 */
	@Override
	public Map convertStringToMap(String s) {
        List<String> auxList = new ArrayList<>(Arrays.asList(s.split(",")));
        List<Double> listDouble = auxList.stream().map(a -> Double.parseDouble(a)).collect(Collectors.toList());	
        Map<String,Double> map=new HashMap<>();
        map.put("Flete",listDouble.get(0));
        map.put("Seguro",listDouble.get(1));
        map.put("IVA",listDouble.get(11));
        map.put("Subtotal",listDouble.get(10));
        map.put("Total",listDouble.get(14));
		return map;
	}


	@Override
	public Convenio getConvenio(Integer idCliente, Integer idIficina) {
	        Convenio convenio =new Convenio();
	        SqlRowSet rs = null; //Objeto en el cual se regresa la respuesta
	        try {
	            String query
	               ="SELECT idconvenio,versionconv FROM clientes.convenios_tyc  "
	                    + "WHERE idcliente="+idCliente+" AND idoficinacliente="+idIficina+" AND estatus IN (1,9);";
	            rs = jdbcTemplate.queryForRowSet(query);
	            while (rs.next()) {
	                //Asignamos al objeto sus datos correspondientes
	              convenio.setIdConvenio(rs.getString(1));
	              convenio.setVersionConvenio(rs.getString(2));
	            }
	        } catch (Exception e) {
	            
	        }
	        return convenio;
	 }


	@Override
	public boolean validaDimensiones(Double alto, Double ancho, Double largo) {
		Double total = alto+ancho+largo;
		return (total>valorDimensionesMensajeria)?false:true;
	}


	/**
	 * Obtener el idCliente
	 */
	@Override
	public Integer obtenerIdCliente(String nombre) {
		 return jdbcTemplate.queryForObject(
	                obteneridClienteOrigen,
	                new Object[]{nombre},
	                Integer.class
	        );
	}
	

}
