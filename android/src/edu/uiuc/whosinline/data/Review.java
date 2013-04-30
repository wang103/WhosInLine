package edu.uiuc.whosinline.data;

public class Review {
	
	private int id;
	private String venueName;
	private String title;
	private String content;
	private float rating;		// 1.0 ~ 5.0
	private String userName;
	
	public Review(int id, String venueName, String title, String content,
			float rating, String userName) {
		this.id = id;
		this.venueName = venueName;
		this.title = title;
		this.content = content;
		this.rating = rating;
		this.userName = userName;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getVenueName() {
		return venueName;
	}
	
	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
}