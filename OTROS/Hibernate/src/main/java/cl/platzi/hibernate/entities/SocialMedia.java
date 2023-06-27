package cl.platzi.hibernate.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SOCIAL_MEDIA database table.
 * 
 */
@Entity
@Table(name="SOCIAL_MEDIA")
@NamedQuery(name="SocialMedia.findAll", query="SELECT s FROM SocialMedia s")
public class SocialMedia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_SOCIAL_MEDIA")
	private long idSocialMedia;
    @Column(name = "ICON")
	private String icon;
    @Column(name = "NAME")
	private String name;

	//bi-directional many-to-one association to TecherSocialMedia
	@OneToMany(mappedBy="socialMedia")
	private List<TeacherSocialMedia> techerSocialMedias;

	public SocialMedia() {
	}

	public long getIdSocialMedia() {
		return this.idSocialMedia;
	}

	public void setIdSocialMedia(long idSocialMedia) {
		this.idSocialMedia = idSocialMedia;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TeacherSocialMedia> getTecherSocialMedias() {
		return this.techerSocialMedias;
	}

	public void setTecherSocialMedias(List<TeacherSocialMedia> techerSocialMedias) {
		this.techerSocialMedias = techerSocialMedias;
	}

	public TeacherSocialMedia addTecherSocialMedia(TeacherSocialMedia techerSocialMedia) {
		getTecherSocialMedias().add(techerSocialMedia);
		techerSocialMedia.setSocialMedia(this);

		return techerSocialMedia;
	}

	public TeacherSocialMedia removeTecherSocialMedia(TeacherSocialMedia techerSocialMedia) {
		getTecherSocialMedias().remove(techerSocialMedia);
		techerSocialMedia.setSocialMedia(null);

		return techerSocialMedia;
	}

}