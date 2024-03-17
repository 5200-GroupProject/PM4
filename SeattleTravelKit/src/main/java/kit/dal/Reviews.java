package kit.model;

import java.util.Date;

public class Reviews {
	
	 protected int reviewId; 
	 protected String userName;
	 protected Date createdTime;
	 protected String content;
	 protected double rating;
	/**
	 * @param reviewId
	 * @param userName
	 * @param createdTime
	 * @param content
	 * @param rating
	 */
	public Reviews(int reviewId, String userName, Date createdTime, String content, double rating) {
		this.reviewId = reviewId;
		this.userName = userName;
		this.createdTime = createdTime;
		this.content = content;
		this.rating = rating;
	}
	/**
	 * @param userName
	 * @param createdTime
	 * @param content
	 * @param rating
	 */
	public Reviews(String userName, Date createdTime, String content, double rating) {
		this.userName = userName;
		this.createdTime = createdTime;
		this.content = content;
		this.rating = rating;
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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the createdTime
	 */
	public Date getCreatedTime() {
		return createdTime;
	}
	/**
	 * @param createdTime the createdTime to set
	 */
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the rating
	 */
	public double getRating() {
		return rating;
	}
	/**
	 * @param rating the rating to set
	 */
	public void setRating(double rating) {
		this.rating = rating;
	}
	 
	 

}
