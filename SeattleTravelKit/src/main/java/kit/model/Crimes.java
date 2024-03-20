package kit.model;

import java.util.Date;

public class Crimes {
    protected String caseNumber;
    protected Date createdDateTime;
    protected String address;
    protected String zipCode;

    public Crimes(String caseNumber, Date createdDateTime, String address, String zipCode) {
        this.caseNumber = caseNumber;
        this.createdDateTime = createdDateTime;
        this.address = address;
        this.zipCode = zipCode;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
