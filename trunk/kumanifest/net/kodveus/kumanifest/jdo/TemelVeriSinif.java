package net.kodveus.kumanifest.jdo;

import net.kodveus.gui.araclar.VeriSinif;

/**
 * Bu sinifin amaci tum veri siniflarimizda olmasi gereken status sahasini
 * tanimlamaktir. Status sahasi sinifin barindirdigi kayitin durum bilgisini
 * tutmaktadir.
 * 
 * @author emre
 * 
 */
public abstract class TemelVeriSinif extends VeriSinif {

	private static final long serialVersionUID = 1L;

	private Long status;

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	@Override
	public void prepareMap() {
	}

	@Override
	public abstract boolean equals(Object obj);

	@Override
	public abstract String toString();

}
