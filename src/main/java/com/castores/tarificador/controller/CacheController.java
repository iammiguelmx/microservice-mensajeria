package com.castores.tarificador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/caches")
public class CacheController {
	
	@Autowired
	private CacheManager cacheManager;

	@PatchMapping("/{name}")
	public void evictCache(@PathVariable String name) {
		cacheManager.getCache(name).clear();
	}


}