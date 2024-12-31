package com.devsuperior.dsmeta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmeta.dto.RelatorioDTO;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SumarioVendasPorVendedorDTO;
import com.devsuperior.dsmeta.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<List<RelatorioDTO>> getReport(@RequestParam(required = false) String minDate,
			@RequestParam(required = false) String maxDate, @RequestParam(required = false) String name) {
		List<RelatorioDTO> response =  service.gerarRelatorio(minDate,maxDate,name);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SumarioVendasPorVendedorDTO>> getSummary(@RequestParam(required = false) String minDate,
			@RequestParam(required = false) String maxDate) {
		List<SumarioVendasPorVendedorDTO> response = service.gerarSumario(minDate, maxDate);
		return ResponseEntity.ok(response);
	}
}
