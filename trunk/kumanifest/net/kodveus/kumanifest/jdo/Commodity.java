package net.kodveus.kumanifest.jdo;

public class Commodity extends TemelVeriSinif {

	private static final long serialVersionUID = 1L;

	private Long commodityId;

	private String code;

	private String name;

	private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Long commodityId) {
		this.commodityId = commodityId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void prepareMap() {
		try {
			aliasMap.addAlias("Code", "code");
			aliasMap.addAlias("Name", "name");
			aliasMap.addAlias("Description", "description");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String toString() {
		return code + " " + name;
	}
	
	public boolean equals(Object object) {
		try {
			return ((Commodity) object).getCommodityId().equals(this.getCommodityId());
		} catch (Exception e) {
			return false;
		}
	}
}
