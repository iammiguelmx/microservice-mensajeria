package com.castores.tarificador.service.impl;

import com.castores.tarificador.controller.config.CacheConfig;
import com.castores.tarificador.entities.Convenio;
import com.castores.tarificador.entities.Mensajeria;
import com.castores.tarificador.repository.IRateRepository;
import com.castores.tarificador.service.IRateService;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RateServiceImpl implements IRateService {

    @Autowired
    private IRateRepository repository;

    @Cacheable(cacheNames = CacheConfig.USER_CACHE, 
    		 condition = "#mensajeria != null")
    @Override
    public String cotizarPaqueteria(Mensajeria mensajeria) {
        return repository.cotizarPaqueteria(mensajeria);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Double tieneCredito(Integer idCliente, Integer idOficina) {
        return repository.tieneCredito(idCliente,idOficina);
    }

	@Override
	public boolean validatePaqueteId(Integer paqueteId) {
		return repository.validatePaqueteId(paqueteId);
	}

	@Override
	public Map<String,Double> convertStringToMap(String s) {
		return repository.convertStringToMap(s);
	}

	@Override
	@Transactional(readOnly = true)
	public Convenio getConvenio(Integer idCliente, Integer idIficina) {
		return repository.getConvenio(idCliente, idIficina);
	}

	@Override
	public boolean validaDimensiones(Double alto, Double ancho, Double largo) {
		return repository.validaDimensiones(alto, ancho, largo);
	}

	@Override
	public Integer obtenerIdCliente(String nombre) {
		return repository.obtenerIdCliente(nombre);
	}

	
}
