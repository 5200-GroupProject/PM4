package kit.model;

import java.util.Date;

public class HotelsReviews  extends Reviews{
	protected int reviewId;
    protected int hotelId;
    protected float service;
    protected float cleanliness;
    protected float location;
    protected float sleepQuality;

    
	/**
	 * @param reviewId
	 * @param userName
	 * @param createdTime
	 * @param content
	 * @param rating
	 */
	public HotelsReviews(int reviewId, String userName, Date createdTime, String content, double rating, int hotelId, 
			float service, float cleanliness, float location, float sleepQuality) {
		super(reviewId, userName, createdTime, content, rating);
		// TODO Auto-generated constructor stub
		this.hotelId = hotelId;
		this.service = service;
		this.cleanliness = cleanliness;
		this.location = location;
		this.sleepQuality = sleepQuality;
	}
	
	/**
	 * @param userName
	 * @param createdTime
	 * @param content
	 * @param rating
	 */
	public HotelsReviews(String userName, Date createdTime, String content, double rating, int hotelId, 
			float service, float cleanliness, float location, float sleepQuality) {
		super(userName, createdTime, content, rating);
		// TODO Auto-generated constructor stub
		this.hotelId = hotelId;
		this.service = service;
		this.cleanliness = cleanliness;
		this.location = location;
		this.sleepQuality = sleepQuality;
	}
	
	
	/**
	 * @return the reviewId
	 */
	public int getReviewId() {
		return reviewId;
	}
	/**
	 * @param reviewId the reviewId to set
	 */
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	/**
	 * @return the hotelId
	 */
	public int getHotelId() {
		return hotelId;
	}
	/**
	 * @param hotelId the hotelId to set
	 */
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}
	/**
	 * @return the service
	 */
	public float getService() {
		return service;
	}
	/**
	 * @param service the service to set
	 */
	public void setService(float service) {
		this.service = service;
	}
	/**
	 * @return the cleanliness
	 */
	public float getCleanliness() {
		return cleanliness;
	}
	/**
	 * @param cleanliness the cleanliness to set
	 */
	public void setCleanliness(float cleanliness) {
		this.cleanliness = cleanliness;
	}
	/**
	 * @return the location
	 */
	public float getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(float location) {
		this.location = location;
	}
	/**
	 * @return the sleepQuality
	 */
	public float getSleepQuality() {
		return sleepQuality;
	}
	/**
	 * @param sleepQuality the sleepQuality to set
	 */
	public void setSleepQuality(float sleepQuality) {
		this.sleepQuality = sleepQuality;
	}

    
	
    
    

}
