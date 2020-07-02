package ch.itenengineering.relations.r7.domain;

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
@Table(name = "R7_STUDENT")
public class R7Student implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String name;

	@ManyToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "R7_STUDENT_COURSE", joinColumns = { @JoinColumn(name = "STUDENT_ID") }, inverseJoinColumns = { @JoinColumn(name = "COURSE_ID") })
	private List<R7Course> courseList = new ArrayList<R7Course>();

	public R7Student() {
	}

	public R7Student(int id, String name) {
		this.id = id;
		this.name = name;
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

	public List<R7Course> getCourses() {
		return courseList;
	}

	public void setCourses(List<R7Course> courses) {
		this.courseList = courses;
	}

	public String toString() {
		StringBuilder buf = new StringBuilder();

		buf.append(toStringStudentFieldsOnly());

		buf.append("\n  Course List:");
		if (courseList != null) {
			for (Iterator<R7Course> iterator = courseList.iterator(); iterator
					.hasNext();) {
				R7Course course = iterator.next();

				buf.append("\n    ");
				buf.append(course.toStringCourseFieldsOnly());
			}
		} else {
			buf.append("\n    Es sind keine Daten vorhanden.");
		}

		return buf.toString();
	}

	public String toStringStudentFieldsOnly() {
		StringBuilder buf = new StringBuilder();

		buf.append(id);

		buf.append(", ");
		buf.append(name);

		return buf.toString();
	}

} // end of class
