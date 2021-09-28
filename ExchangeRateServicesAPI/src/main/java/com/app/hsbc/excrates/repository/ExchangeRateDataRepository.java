/**
 * 
 */
package com.app.hsbc.excrates.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.hsbc.excrates.entity.BaseDataEntity;

/**
 * @author Rejin Chandran R
 *
 */

@Repository
public interface ExchangeRateDataRepository extends JpaRepository<BaseDataEntity, Long>{
	@Query(value = "SELECT * FROM base_data a , exch_rate_data b WHERE a.base_data_id = b.base_data_id and exch_rate_base= ?1", nativeQuery = true)
	public Optional<BaseDataEntity> findLoadedDataByRequestedDate(LocalDate date);

}
