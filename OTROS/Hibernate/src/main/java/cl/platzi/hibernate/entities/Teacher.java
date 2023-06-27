package cl.platzi.hibernate.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TEACHER database table.
 * 
 */
@Entity
@NamedQuery(name="Teacher.findAll", query="SELECT t FROM Teacher t")
public class Teacher implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_TEACHER")
	private long idTeacher;
	@Column(name="AVATAR")
	private String avatar;
	@Column(name="NAME")
	private String name;

	//bi-directional many-to-one association to Course
	@OneToMany(mappedBy="teacher")
	private List<Course> courses;

	//bi-directional many-to-one association to TecherSocialMedia
	@OneToMany(mappedBy="teacher")
	private List<TeacherSocialMedia> techerSocialMedias;

	public Teacher() {
	}

	public long getIdTeacher() {
		return this.idTeacher;
	}

	public void setIdTeacher(long idTeacher) {
		this.idTeacher = idTeacher;
	}

	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Course> getCourses() {
		return this.courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Course addCours(Course cours) {
		getCourses().add(cours);
		cours.setTeacher(this);

		return cours;
	}

	public Course removeCours(Course cours) {
		getCourses().remove(cours);
		cours.setTeacher(null);

		return cours;
	}

	public List<TeacherSocialMedia> getTecherSocialMedias() {
		return this.techerSocialMedias;
	}

	public void setTecherSocialMedias(List<TeacherSocialMedia> techerSocialMedias) {
		this.techerSocialMedias = techerSocialMedias;
	}

	public TeacherSocialMedia addTecherSocialMedia(TeacherSocialMedia techerSocialMedia) {
		getTecherSocialMedias().add(techerSocialMedia);
		techerSocialMedia.setTeacher(this);

		return techerSocialMedia;
	}

	public TeacherSocialMedia removeTecherSocialMedia(TeacherSocialMedia techerSocialMedia) {
		getTecherSocialMedias().remove(techerSocialMedia);
		techerSocialMedia.setTeacher(null);

		return techerSocialMedia;
	}

}