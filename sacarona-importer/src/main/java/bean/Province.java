package bean;

public class Province {
	String abbreviation;
	String name;
	String countryIso;
	String provinceId;
	
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountryIso() {
		return countryIso;
	}
	public void setCountryIso(String countryIso) {
		this.countryIso = countryIso;
	}
	
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	@Override
	public String toString() {
		return "Province [abbreviation=" + abbreviation + ", name=" + name
				+ ", countryIso=" + countryIso + ", provinceId=" + provinceId
				+ "]";
	}
	
}
