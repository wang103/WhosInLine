package edu.uiuc.whosinline.data;

public class Venue {
	
	private int id;
	private String name;
	private String imagePath;
	private int imageResource;
	private String type;
	private int rating;
	private float distance;
	private int waitMinutes;
	private String address;
	
	public Venue(int id, String name, String imagePath, int imageResource,
			String type, int rating, float distance, int waitMinutes,
			String address) {
		this.id = id;
		this.name = name;
		this.imagePath = imagePath;
		this.imageResource = imageResource;
		this.type = type;
		this.rating = rating;
		this.distance = distance;
		this.waitMinutes = waitMinutes;
		this.address = address;
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
	
	public int getImageResource() {
		return imageResource;
	}
	
	public void setImageResource(int imageResource) {
		this.imageResource = imageResource;
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
	
	public float getDistance() {
		return distance;
	}
	
	public void setDistance(float distance) {
		this.distance = distance;
	}
	
	public int getWaitMinutes() {
		return waitMinutes;
	}
	
	public void setWaitMinutes(int waitMinutes) {
		this.waitMinutes = waitMinutes;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
}