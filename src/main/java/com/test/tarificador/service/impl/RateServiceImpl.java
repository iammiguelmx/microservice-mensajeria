package com.test.tarificador.service.impl;

import com.test.tarificador.entities.Convenio;
import com.test.tarificador.entities.Mensajeria;
import com.test.tarificador.repository.IRateRepository;
import com.test.tarificador.service.IRateService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RateServiceImpl implements IRateService {

    @Autowired
    private IRateRepository repository;

    @Override
    public String cotizarPaqueteria(Mensajeria mensajeria) {
        return repository.cotizarPaqueteria(mensajeria);
    }

    @Override
    public Double tieneCredito(Integer idCliente, Integer idOficina) {
        return repository.tieneCredito(idCliente,idOficina);
    }

	@Override
	public boolean validatePaqueteId(Integer paqueteId) {
		return repository.validatePaqueteId(paqueteId);
	}

	@Override
	public Map convertStringToMap(String s) {
		return repository.convertStringToMap(s);
	}

	@Override
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
