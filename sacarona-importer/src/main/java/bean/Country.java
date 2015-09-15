package bean;

public class Country {
	String iso;
	String nameEnglish;
	String namePortuguese;
	String nameSpanish;
	String un;
	String alternativeNames;
	
	public void setIso(String iso) {
		this.iso = iso;
	}
	public String getIso() {
		return iso;
	}
	
	public String getNameEnglish() {
		return nameEnglish;
	}
	public void setNameEnglish(String nameEnglish) {
		this.nameEnglish = nameEnglish;
	}
	public String getNamePortuguese() {
		return namePortuguese;
	}
	public void setNamePortuguese(String namePortuguese) {
		this.namePortuguese = namePortuguese;
	}
	public String getNameSpanish() {
		return nameSpanish;
	}
	public void setNameSpanish(String nameSpanish) {
		this.nameSpanish = nameSpanish;
	}
	public String getAlternativeNames() {
		return alternativeNames;
	}
	public void setAlternativeNames(String alternativeNames) {
		this.alternativeNames = alternativeNames;
	}
	
	public String getUn() {
		return un;
	}
	
	public void setUn(String un) {
		this.un = un;
	}
	
	public void check () {
		if (namePortuguese == null)
			namePortuguese = nameEnglish;
		if (un == null)
			un = iso;
	}
	
	@Override
	public String toString() {
		return "Country [iso=" + iso + ", nameEnglish=" + nameEnglish
				+ ", namePortuguese=" + namePortuguese + ", nameSpanish="
				+ nameSpanish + ", alternativeNames=" + alternativeNames + "]";
	}
}
