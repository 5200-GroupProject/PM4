package kit.model;

import java.util.Date;

public class RestaurantReviews extends Reviews {
	
	protected int reviewId;
    protected int restaurantId;
    protected double service;
    protected double foodQuality;
    protected String operationTime;
	/**
	 * @param reviewId
	 * @param userName
	 * @param createdTime
	 * @param content
	 * @param rating
	 */
	public RestaurantReviews(int reviewId, String userName, Date createdTime, String content, double rating, int restaurantID,
			double service, double foodQuality, String operationTime) {
		super(reviewId, userName, createdTime, content, rating);
		// TODO Auto-generated constructor stub
		this.restaurantId = restaurantID;
		this.service = service;
		this.foodQuality = foodQuality;
		this.operationTime = operationTime;
		
	}
	/**
	 * @param userName
	 * @param createdTime
	 * @param content
	 * @param rating
	 */
	public RestaurantReviews(String userName, Date createdTime, String content, double rating, int restaurantID,
			double service, double foodQuality, String operationTime) {
		super(userName, createdTime, content, rating);
		// TODO Auto-generated constructor stub
		this.restaurantId = restaurantID;
		this.service = service;
		this.foodQuality = foodQuality;
		this.operationTime = operationTime;
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
	 * @return the restaurantId
	 */
	public int getRestaurantId() {
		return restaurantId;
	}
	/**
	 * @param restaurantId the restaurantId to set
	 */
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	/**
	 * @return the service
	 */
	public double getService() {
		return service;
	}
	/**
	 * @param service the service to set
	 */
	public void setService(double service) {
		this.service = service;
	}
	/**
	 * @return the foodQuality
	 */
	public double getFoodQuality() {
		return foodQuality;
	}
	/**
	 * @param foodQuality the foodQuality to set
	 */
	public void setFoodQuality(double foodQuality) {
		this.foodQuality = foodQuality;
	}
	/**
	 * @return the operationTime
	 */
	public String getOperationTime() {
		return operationTime;
	}
	/**
	 * @param operationTime the operationTime to set
	 */
	public void setOperationTime(String operationTime) {
		this.operationTime = operationTime;
	}
    
	
    

}
