package kit.model;

public class Restaurants {
    protected int restaurantId;
    protected String restaurantName;
    protected String address;
    protected double rating;
    protected String area;
    protected String category;
    protected String service;
    protected int zipCode;
	/**
	 * @param restaurantId
	 * @param restaurantName
	 * @param address
	 * @param rating
	 * @param area
	 * @param cactegory
	 * @param service
	 * @param zipCode
	 */
	public Restaurants(int restaurantId, String restaurantName, String address, double rating, String area,
			String category, String service, int zipCode) {
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.address = address;
		this.rating = rating;
		this.area = area;
		this.category = category;
		this.service = service;
		this.zipCode = zipCode;
	}
	/**
	 * @param restaurantName
	 * @param address
	 * @param rating
	 * @param area
	 * @param category
	 * @param service
	 * @param zipCode
	 */
	public Restaurants(String restaurantName, String address, double rating, String area, String category,
			String service, int zipCode) {
		this.restaurantName = restaurantName;
		this.address = address;
		this.rating = rating;
		this.area = area;
		this.category = category;
		this.service = service;
		this.zipCode = zipCode;
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
	 * @return the restaurantName
	 */
	public String getRestaurantName() {
		return restaurantName;
	}
	/**
	 * @param restaurantName the restaurantName to set
	 */
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
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
	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}
	/**
	 * @param area the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setAtegory(String category) {
		this.category = category;
	}
	/**
	 * @return the service
	 */
	public String getService() {
		return service;
	}
	/**
	 * @param service the service to set
	 */
	public void setService(String service) {
		this.service = service;
	}
	/**
	 * @return the zipCode
	 */
	public int getZipCode() {
		return zipCode;
	}
	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
    
    

}
