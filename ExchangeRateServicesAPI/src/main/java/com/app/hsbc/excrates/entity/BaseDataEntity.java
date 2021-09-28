/**
 * 
 */
package com.app.hsbc.excrates.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Rejin Chandran R
 *
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="base_data")
public class BaseDataEntity   {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="base_data_id")
	private long baseDataId;
	@Column(name="exch_rate_date")
	private LocalDate exchrateDate;
	@Column(name="exch_rate_base")
	private String base;
	@Column(name="exch_rate_timestamp")
	private long timesStamp;	

    @OneToMany
    @JoinColumn(name = "exch_rate_id")
    private Set<ExchangeRateDataEntity> exchangeRateData;

}
