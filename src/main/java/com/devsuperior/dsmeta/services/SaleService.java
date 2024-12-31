package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
		LocalDate maxDate = definirMaxDate(maxDateParam);
		LocalDate minDate =definirMinDate(minDateParam, maxDate);
		
		return repository.getSummary(minDate, maxDate);
	}
	
	public Page<RelatorioDTO> gerarRelatorio(String minDateParam, String maxDateParam, String name,Pageable pageable) {
		
		LocalDate maxDate = definirMaxDate(maxDateParam);
		LocalDate minDate = definirMinDate(minDateParam, maxDate);
		
		return repository.gerarReport(minDate, maxDate,name,pageable);		
	}
	
	private LocalDate definirMinDate(String minDateParam, LocalDate maxDate) {
		LocalDate mindate;
	    if (minDateParam == null) {
	        mindate = maxDate.minusYears(1L);
	    } else {
	        mindate = LocalDate.parse(minDateParam, DateTimeFormatter.ISO_LOCAL_DATE);
	    }
		return mindate;
	}

	private LocalDate definirMaxDate(String maxDateParam) {
		LocalDate dataFinal;
	    if (maxDateParam == null) {
	        dataFinal = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
	    } else {
	        dataFinal = LocalDate.parse(maxDateParam, DateTimeFormatter.ISO_LOCAL_DATE);
	    }
		return dataFinal;
	}
	
	
}
