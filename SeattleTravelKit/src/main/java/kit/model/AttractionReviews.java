package kit.model;

import java.util.Date;

public class AttractionReviews extends Reviews {
    protected int reviewId;
    protected int attractionId;
    protected String duration;
    
	/**
	 * @param reviewId
	 * @param userName
	 * @param createdTime
	 * @param content
	 * @param rating
	 */
	public AttractionReviews(int reviewId, String userName, Date createdTime, String content, double rating, int attractionId, String duration) {
		super(reviewId, userName, createdTime, content, rating);
		// TODO Auto-generated constructor stub
		this.attractionId = attractionId;
		this.duration = duration;
		
	}
	
	/**
	 * @param userName
	 * @param createdTime
	 * @param content
	 * @param rating
	 */
	public AttractionReviews(String userName, Date createdTime, String content, double rating, int attractionId, String duration) {
		super(userName, createdTime, content, rating);
		// TODO Auto-generated constructor stub
		this.attractionId = attractionId;
		this.duration = duration;
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
	 * @return the attractionId
	 */
	public int getAttractionId() {
		return attractionId;
	}
	
	/**
	 * @param attractionId the attractionId to set
	 */
	public void setAttractionId(int attractionId) {
		this.attractionId = attractionId;
	}
	
	/**
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}
	
	/**
	 * @param duration the duration to set
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	

}
