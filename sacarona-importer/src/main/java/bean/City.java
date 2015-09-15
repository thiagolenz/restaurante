package bean;

public class City {
	private String name;
	private String code;
	private String countryIso;
	private String provinceAbbreviation;
	private String importerId;
	private String alternativeNames;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCountryIso() {
		return countryIso;
	}
	public void setCountryIso(String countryIso) {
		this.countryIso = countryIso;
	}
	public String getProvinceAbbreviation() {
		return provinceAbbreviation;
	}
	public void setProvinceAbbreviation(String provinceAbbreviation) {
		this.provinceAbbreviation = provinceAbbreviation;
	}
	
	public String getImporterId() {
		return importerId;
	}
	public void setImporterId(String importerId) {
		this.importerId = importerId;
	}
	
	public String getAlternativeNames() {
		return alternativeNames;
	}
	
	public void setAlternativeNames(String alternativeNames) {
		this.alternativeNames = alternativeNames;
	}
	@Override
	public String toString() {
		return "City [name=" + name + ", code=" + code + ", countryIso="
				+ countryIso + ", provinceAbbreviation=" + provinceAbbreviation
				+ ", importerId=" + importerId + "]";
	}
}
