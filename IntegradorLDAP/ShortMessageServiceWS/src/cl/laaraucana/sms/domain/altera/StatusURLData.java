package cl.laaraucana.sms.domain.altera;

import java.util.ArrayList;

public class StatusURLData {
	private String shortUrl; // short_url
	private String countClick;
	private ArrayList<StatusURLTracker> urlTracker; // url_tracker

	public StatusURLData() {
	}

	public StatusURLData(String shortUrl, String countClick, ArrayList<StatusURLTracker> urlTracker) {
		super();
		this.shortUrl = shortUrl;
		this.countClick = countClick;
		this.urlTracker = urlTracker;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getCountClick() {
		return countClick;
	}

	public void setCountClick(String countClick) {
		this.countClick = countClick;
	}

	public ArrayList<StatusURLTracker> getUrlTracker() {
		return urlTracker;
	}

	public void setUrlTracker(ArrayList<StatusURLTracker> urlTracker) {
		this.urlTracker = urlTracker;
	}

}