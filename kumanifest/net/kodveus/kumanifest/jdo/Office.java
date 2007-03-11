package net.kodveus.kumanifest.jdo;

import net.kodveus.kumanifest.utility.LogHelper;

public class Office extends TemelVeriSinif {

	private static final long serialVersionUID = 1L;

	private Long officeId;

	private String office;

	private String description;

	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public Long getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Long officeId) {
		this.officeId = officeId;
	}

	@Override
	public void prepareMap() {
		try {
			aliasMap.addAlias("Office", "office");
			aliasMap.addAlias("Description", "description");
			aliasMap.addAlias("Code", "code");
		} catch (Exception e) {
			LogHelper.getInstance().exception(e);
		}
	}

	public String toString() {
		return this.office;
	}

	public boolean equals(Object object) {
		try {
			return ((Office) object).getOfficeId().equals(this.officeId);
		} catch (Exception e) {
			return false;
		}
	}
}
