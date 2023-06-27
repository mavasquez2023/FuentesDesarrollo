package cl.platzi.hibernate.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TECHER_SOCIAL_MEDIA database table.
 * 
 */
@Entity
@Table(name="TECHER_SOCIAL_MEDIA")
@NamedQuery(name="TeacherSocialMedia.findAll", query="SELECT t FROM TeacherSocialMedia t")
public class TeacherSocialMedia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_TEACHER_SOCIAL_MEDIA")
	private long idTeacherSocialMedia;
    @Column(name = "NICKNAME")
	private String nickname;

	//bi-directional many-to-one association to SocialMedia
	@ManyToOne
	@JoinColumn(name="ID_SOCIAL_MEDIA")
	private SocialMedia socialMedia;

	//bi-directional many-to-one association to Teacher
	@ManyToOne
	@JoinColumn(name="ID_TECHER")
	private Teacher teacher;

	public TeacherSocialMedia() {
	}

	public long getIdTeacherSocialMedia() {
		return this.idTeacherSocialMedia;
	}

	public void setIdTeacherSocialMedia(long idTeacherSocialMedia) {
		this.idTeacherSocialMedia = idTeacherSocialMedia;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public SocialMedia getSocialMedia() {
		return this.socialMedia;
	}

	public void setSocialMedia(SocialMedia socialMedia) {
		this.socialMedia = socialMedia;
	}

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

}