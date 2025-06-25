package com.microservice.metas.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.metas.dto.MetaDto;
import com.microservice.metas.service.MetaService;

@RestController
@RequestMapping("/gpp/metas")
public class MetaRestController {
	
	@Autowired
	MetaService metaService;
	
	@PostMapping("/crearMeta")
	public void crearMeta(@RequestHeader("Authorization") String token, @RequestBody MetaDto meta) {
		metaService.crearMeta(token, meta);
	}

}
