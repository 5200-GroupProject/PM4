package kit.model;

import java.util.Date;

public class Crimes {
	protected String caseNumber;
    protected Date createdDateTime;
    protected String address;
    protected int zipCode;
    
	/**
	 * @param caseNumber
	 * @param createdDateTime
	 * @param address
	 * @param zipCode
	 */
	public Crimes(String caseNumber, Date createdDateTime, String address, int zipCode) {
		this.caseNumber = caseNumber;
		this.createdDateTime = createdDateTime;
		this.address = address;
		this.zipCode = zipCode;
	}

	/**
	 * @return the caseNumber
	 */
	public String getCaseNumber() {
		return caseNumber;
	}

	/**
	 * @param caseNumber the caseNumber to set
	 */
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	/**
	 * @return the createdDateTime
	 */
	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	/**
	 * @param createdDateTime the createdDateTime to set
	 */
	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
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
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
    
    
	
    
    
}
