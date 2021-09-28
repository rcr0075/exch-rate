/**
 * 
 */
package com.app.hsbc.excrates.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Rejin Chandran R
 *
 */

@Entity
@Table(name="exch_rate_data")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateDataEntity {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="exch_rate_id")
	private long exchangeRateDataId;
	@Column(name="currency_code")
	private String currencyCode;
	@Column(name="exch_rate")
	private BigDecimal exchangeRate;
	/*@OneToMany
	private List<BaseDataEntity> baseData;*/
	 @ManyToOne
	    @JoinColumn(name = "base_data_id")
	    private BaseDataEntity baseData;

}
