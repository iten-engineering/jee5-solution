package ch.itenengineering.mex.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MEX_RATE")
public class Rate implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	CurrencyType from;

	CurrencyType to;

	private double rate;

	public Rate() {
		super();
	}

	public Rate(CurrencyType from, CurrencyType to, double rate) {
		super();
		this.from = from;
		this.to = to;
		this.rate = rate;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RATE_ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "FROM_CURRENCY")
	@Enumerated(EnumType.STRING)
	public CurrencyType getFrom() {
		return from;
	}

	public void setFrom(CurrencyType from) {
		this.from = from;
	}

	@Column(name = "TO_CURRENCY")
	@Enumerated(EnumType.STRING)
	public CurrencyType getTo() {
		return to;
	}

	public void setTo(CurrencyType to) {
		this.to = to;
	}

	@Column(name = "RATE")
	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

} // end of class
