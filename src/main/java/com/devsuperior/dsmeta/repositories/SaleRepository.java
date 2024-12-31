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
		        s.name, COALESCE(SUM(sa.amount), 0))
		    FROM Seller s
		    LEFT JOIN s.sales sa
		    ON sa.date BETWEEN :minDate AND :maxDate
		    GROUP BY s.name
		""")
		List<SumarioVendasPorVendedorDTO> getSummary(
		    @Param("minDate") LocalDate minDate,
		    @Param("maxDate") LocalDate maxDate
		);

	
}
