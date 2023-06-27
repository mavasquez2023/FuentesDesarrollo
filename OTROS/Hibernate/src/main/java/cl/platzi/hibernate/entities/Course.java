package cl.platzi.hibernate.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the COURSE database table.
 * 
 */
@Entity
@NamedQuery(name="Course.findAll", query="SELECT c FROM Course c")
public class Course implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_COURSE")
	private long idCourse;
    @Column(name="NAME")
	private String name;
    @Column(name="PROJECT")
	private String project;
    @Column(name="THEMES")
	private String themes;

	//bi-directional many-to-one association to Teacher
	@ManyToOne
	@JoinColumn(name="ID_TEACHER")
	private Teacher teacher;

	public Course() {
	}

	public long getIdCourse() {
		return this.idCourse;
	}

	public void setIdCourse(long idCourse) {
		this.idCourse = idCourse;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProject() {
		return this.project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getThemes() {
		return this.themes;
	}

	public void setThemes(String themes) {
		this.themes = themes;
	}

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

}