package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devsuperior.dsmeta.dto.SumarioVendasPorVendedorDTO;
import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	
	
	@Query("""
		    SELECT new com.devsuperior.dsmeta.dto.SumarioVendasPorVendedorDTO(
		        s.name, SUM(sa.amount))
		    FROM Sale sa
		    JOIN sa.seller s
		    WHERE sa.date BETWEEN :minDate AND :maxDate
		    GROUP BY s.name
		""")
		List<SumarioVendasPorVendedorDTO> getSummary(
		    @Param("minDate") LocalDate minDate, 
		    @Param("maxDate") LocalDate maxDate
		);


}
