package ch.itenengineering.basicmapping.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "BASIC_MAPPING_COMPANY")
@SecondaryTable(name = "BASIC_MAPPING_COMPANY_LOB")
public class Company implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum CompanyType {
		SMALL, MEDIUM, LARGE
	};

	@Id
	@Column(name = "COMPANY_ID")
	protected int id;

	// Optional kann das Mapping der Attribute der eingebeteten Klasse Name über
	// die AttributOverrides Annotaton eingestellt werden.
	@AttributeOverrides( {
			@AttributeOverride(name = "firstname", column = @Column(name = "FIRST_NAME")),
			@AttributeOverride(name = "lastname", column = @Column(name = "LAST_NAME")) })
	@Embedded
	protected Name name;

	BigDecimal balance;

	@Enumerated(EnumType.STRING)
	@Column(name = "COMPANY_TYPE")
	private CompanyType companyType;

	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date timestamp;

	@Lob
	@Column(table = "BASIC_MAPPING_COMPANY_LOB")
	protected String notes;

	public Company() {
		this.balance = new BigDecimal(1999000.50);
		this.companyType = CompanyType.SMALL;
		this.timestamp = new java.util.Date();
	}

	public Company(int id, Name name) {
		this();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public CompanyType getCompanyType() {
		return companyType;
	}

	public void setCompanyType(CompanyType companyType) {
		this.companyType = companyType;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTimestamp(java.util.Date timestamp) {
		this.timestamp = timestamp;
	}

	public java.util.Date getTimestamp() {
		return timestamp;
	}

	public String toString() {
		StringBuilder buf = new StringBuilder();

		buf.append("\nCompany Id = ");
		buf.append(id);

		buf.append("\nName       = ");
		buf.append(name);

		buf.append("\nType       = ");
		buf.append(companyType);

		buf.append("\nBalance    = ");
		buf.append(balance);

		buf.append("\nNotes      = ");
		buf.append(notes);

		buf.append("\nTimestamp  = ");
		buf.append(timestamp);

		return buf.toString();
	}

} // end of class
