package ch.itenengineering.bmt.company.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "BMT_COMPANY")
public class Company implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "COMPANY_ID")
	private int companyId;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	@Version
	@Column(name = "Version")
	private int version;

	public Company() {
	}

	public Company(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append("companyId=");
		sb.append(companyId);

		sb.append(", name=");
		sb.append(name);

		sb.append(", decription=");
		sb.append(description);

		sb.append(", version=");
		sb.append(version);

		return sb.toString();
	}

	public int getCompanyId() {
		return companyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getVersion() {
		return version;
	}

} // end of class
