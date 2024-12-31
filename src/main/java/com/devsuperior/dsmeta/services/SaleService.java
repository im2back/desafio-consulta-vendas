package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.RelatorioDTO;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SumarioVendasPorVendedorDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public List<SumarioVendasPorVendedorDTO> gerarSumario(String minDateParam, String maxDateParam) {
		LocalDate minDate =null;
		LocalDate maxDate = null;
		
		maxDate = definirMaxDate(maxDateParam);
		minDate = definirMinDate(minDateParam, maxDate);
		
		return repository.getSummary(minDate, maxDate);
	}
	
	private LocalDate definirMinDate(String minDate, LocalDate maxDate) {
		LocalDate dataInicial;
		// Define a dataInicial como um ano antes da dataFinal se for nula
	    if (minDate == null) {
	        dataInicial = maxDate.minusYears(1L);
	    } else {
	        dataInicial = LocalDate.parse(minDate, DateTimeFormatter.ISO_LOCAL_DATE);
	    }
		return dataInicial;
	}

	private LocalDate definirMaxDate(String maxDate) {
		LocalDate dataFinal;
		// Define a dataFinal como a data atual se for nula
	    if (maxDate == null) {
	        dataFinal = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
	    } else {
	        dataFinal = LocalDate.parse(maxDate, DateTimeFormatter.ISO_LOCAL_DATE);
	    }
		return dataFinal;
	}

	public List<RelatorioDTO> gerarRelatorio(String minDate, String maxDate, String name) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
