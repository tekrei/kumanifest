package net.kodveus.kumanifest.interfaces;

import java.util.ArrayList;

import net.kodveus.gui.araclar.VeriSinif;

public interface OperationInterface {

	public abstract long create(VeriSinif vs);

	public abstract boolean delete(VeriSinif vs);

	// TODO public abstract boolean delete(Long id);

	public abstract boolean update(VeriSinif vs);

	@SuppressWarnings("unchecked")
	public abstract ArrayList ara(VeriSinif vs);

	public abstract VeriSinif get(Long id);

	public abstract VeriSinif next(Long id);

	public abstract VeriSinif previous(Long id);
}
