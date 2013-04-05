package edu.uiuc.whosinline.data;

public class Venue {
	private int id;
	private String name;
	private String imagePath;
	private String type;
	private int rating;
	private int waitMinutes;
	
	public Venue(int id, String name, String imagePath, String type,
			int rating, int waitMinutes) {
		this.id = id;
		this.name = name;
		this.imagePath = imagePath;
		this.type = type;
		this.rating = rating;
		this.waitMinutes = waitMinutes;
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
	
	public String getImagePath() {
		return imagePath;
	}
	
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public int getRating() {
		return rating;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public int getWaitMinutes() {
		return waitMinutes;
	}
	
	public void setWaitMinutes(int waitMinutes) {
		this.waitMinutes = waitMinutes;
	}
}