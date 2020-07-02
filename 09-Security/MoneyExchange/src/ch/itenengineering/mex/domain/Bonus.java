package ch.itenengineering.mex.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MEX_BONUS")
public class Bonus implements Serializable {

	private static final long serialVersionUID = 1L;

	private String role;

	private int percent;

	public Bonus() {
		super();
	}

	public Bonus(String role, int percent) {
		super();
		this.role = role;
		this.percent = percent;
	}

	@Id
	@Column(name = "ROLE")
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Column(name = "PERCENT")
	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}
}
