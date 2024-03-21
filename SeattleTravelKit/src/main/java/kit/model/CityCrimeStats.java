package kit.model;

public class CityCrimeStats {
	private String zipCode;
	private int crimeCount;
	
	public CityCrimeStats(String zipCode, int crimeCount) {
	    this.zipCode = zipCode;
	    this.crimeCount = crimeCount;
	}
	  
    public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public int getCrimeCount() {
		return crimeCount;
	}

	public void setCrimeCount(int crimeCount) {
		this.crimeCount = crimeCount;
	}

  
}
