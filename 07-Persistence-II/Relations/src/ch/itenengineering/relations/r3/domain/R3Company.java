package ch.itenengineering.relations.r3.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * Entität Firma
 * <ul>
 * <li>Eine Firma kann 0..n Adressen enthalten.</li>
 * <li>Mit fetch=EAGER werden beim Lesen einer Firma auch alle dazugehörigen
 * Adressen selektiert.</li>
 * <li>Mit JoinTable wird die Verbindungstabelle definiert. Der Name ergibt
 * sich aus den beiden Tabellennamen. Die Attribute aus den jeweiligen
 * Primärschlüsseln.</li>
 * <li>Mit OrderBy kann die Liste sortiert werden.</li>
 * <li>Die Firma ist Besitzer der Beziehung.</li>
 * </ul>
 */
@Entity
@Table(name = "R3_COMPANY")
public class R3Company implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	private String description;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "R3_COMPANY_ADDRESS", joinColumns = { @JoinColumn(name = "COMPANY_ID") }, inverseJoinColumns = { @JoinColumn(name = "ADDRESS_ID") })
	@OrderBy("street ASC")
	private List<R3Address> addressList = new ArrayList<R3Address>();

	public R3Company() {
	}

	public R3Company(int id, String name, String description,
			List<R3Address> addressList) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.addressList = addressList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public List<R3Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<R3Address> addressList) {
		this.addressList = addressList;
	}

	public String toString() {
		StringBuilder buf = new StringBuilder();

		buf.append(id);

		buf.append(", ");
		buf.append(name);

		buf.append(", ");
		buf.append(description);

		buf.append("\n  Address List:");
		if (addressList != null) {
			for (Iterator<R3Address> iterator = addressList.iterator(); iterator
					.hasNext();) {
				R3Address address = iterator.next();

				buf.append("\n    ");
				buf.append(address.toString());
			}
		} else {
			buf.append("\n    Es sind keine Daten vorhanden.");
		}

		return buf.toString();
	}

} // end of class
