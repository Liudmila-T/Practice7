package ua.nure.timoshenko.practice7.constants;

public enum XML {
	// elements names
	MEDICINES("Medicines"),
	MEDICINE("Medicine"), NAME("name"),
	PHARM("Pharm"),
	GROUP("Group"),
	ANALOGS("Analogs"),
	VERSIONS("Versions"), NAME_VERSIONS("nameVersions"),
	MANUFACTURER("Manufacturer"), NAME_MANUFACTURER("nameManufacturer"),
	CERTIFICATE("Certificate"), NUMBER("number"), DATE("date"),
	PACKAGE("Package"), TYPE("type"), QUANTITY("quantity"), PRICE("price"),
	DOSAGE("dosage");

	private String value;

	XML(String value) {
		this.value = value;
	}

	public boolean equalsTo(String name) {
		return value.equals(name);
	}

	public String value() {
		return value;
	}
}
