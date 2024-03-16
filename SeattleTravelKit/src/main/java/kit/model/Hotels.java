package kit.model;

public class Hotels {
	protected int hotelId;
	protected String hotelName;
	protected double rating;
	protected String website;
	protected String phone;
	protected String details;
	protected String address;
	protected int zipCode;
	protected String city;
	
	/**
	 * @param hotelId
	 * @param hotelName
	 * @param rating
	 * @param website
	 * @param phone
	 * @param details
	 * @param address
	 * @param zipCode
	 * @param city
	 */
	public Hotels(int hotelId, String hotelName, double rating, String website, String phone, String details,
			String address, int zipCode, String city) {
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		this.rating = rating;
		this.website = website;
		this.phone = phone;
		this.details = details;
		this.address = address;
		this.zipCode = zipCode;
		this.city = city;
	}

	/**
	 * @param hotelName
	 * @param rating
	 * @param website
	 * @param phone
	 * @param details
	 * @param address
	 * @param zipCode
	 * @param city
	 */
	public Hotels(String hotelName, double rating, String website, String phone, String details, String address,
			int zipCode, String city) {
		this.hotelName = hotelName;
		this.rating = rating;
		this.website = website;
		this.phone = phone;
		this.details = details;
		this.address = address;
		this.zipCode = zipCode;
		this.city = city;
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
	 * @return the hotelName
	 */
	public String getHotelName() {
		return hotelName;
	}

	/**
	 * @param hotelName the hotelName to set
	 */
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
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
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * @param website the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * @param details the details to set
	 */
	public void setDetails(String details) {
		this.details = details;
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
	 * @return the zipCode
	 */
	public int getZipCode() {
		return zipCode;
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	
	
	

}
