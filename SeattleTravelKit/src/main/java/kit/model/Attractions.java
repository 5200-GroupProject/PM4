package kit.model;

public class Attractions {
	protected int attractionId;
	protected String attractionsName;
	protected int phone;
	protected String website;
    protected int zipCode;
    protected String area;
	/**
	 * @param attractionId
	 * @param attractionsName
	 * @param phone
	 * @param website
	 * @param zipCode
	 * @param area
	 */
	public Attractions(int attractionId, String attractionsName, int phone, String website, int zipCode,
			String area) {
		this.attractionId = attractionId;
		this.attractionsName = attractionsName;
		this.phone = phone;
		this.website = website;
		this.zipCode = zipCode;
		this.area = area;
	}
	
	
	
	
	/**
	 * @param attractionsName
	 * @param phone
	 * @param website
	 * @param zipCode
	 * @param area
	 */
	public Attractions(String attractionsName, int phone, String website, int zipCode, String area) {
		this.attractionsName = attractionsName;
		this.phone = phone;
		this.website = website;
		this.zipCode = zipCode;
		this.area = area;
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
	 * @return the attractionsName
	 */
	public String getAttractionsName() {
		return attractionsName;
	}
	/**
	 * @param attractionsName the attractionsName to set
	 */
	public void setAttractionsName(String attractionsName) {
		this.attractionsName = attractionsName;
	}
	/**
	 * @return the phone
	 */
	public int getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(int phone) {
		this.phone = phone;
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
    
    

}
