package ch.itenengineering.relations.r6.domain;

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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "R6_STUDENT")
public class R6Student implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	@ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(name = "R6_STUDENT_COURSE", joinColumns = { @JoinColumn(name = "STUDENT_ID") }, inverseJoinColumns = { @JoinColumn(name = "COURSE_ID") })
	private List<R6Course> courseList = new ArrayList<R6Course>();

	public R6Student() {
	}

	public R6Student(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public R6Student(int id, String name, List<R6Course> courseList) {
		this.id = id;
		this.name = name;
		this.courseList = courseList;
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

	public List<R6Course> getCourses() {
		return courseList;
	}

	public void setCourses(List<R6Course> courses) {
		this.courseList = courses;
	}

	public String toString() {
		StringBuilder buf = new StringBuilder();

		buf.append(id);

		buf.append(", ");
		buf.append(name);

		buf.append("\n  Course List:");
		if (courseList != null) {
			for (Iterator<R6Course> iterator = courseList.iterator(); iterator
					.hasNext();) {
				R6Course course = iterator.next();

				buf.append("\n    ");
				buf.append(course.toString());
			}
		} else {
			buf.append("\n    Es sind keine Daten vorhanden.");
		}

		return buf.toString();
	}

} // end of class
