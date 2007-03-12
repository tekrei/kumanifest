/* Gemi tasimaciligi yukleme, bosaltma, manifesto takip programi.
 * Copyright (C) 2006  Kod ve Us
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package net.kodveus.kumanifest.utility;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import net.kodveus.gui.araclar.VeriSinif;
import net.kodveus.gui.jtree.AutoscrollableJTree;
import net.kodveus.kumanifest.MainFrame;
import net.kodveus.kumanifest.jdo.BL;
import net.kodveus.kumanifest.jdo.Office;
import net.kodveus.kumanifest.jdo.Vessel;
import net.kodveus.kumanifest.jdo.Voyage;
import net.kodveus.kumanifest.operation.BLOperation;
import net.kodveus.kumanifest.operation.OfficeOperation;
import net.kodveus.kumanifest.operation.VesselOperation;
import net.kodveus.kumanifest.operation.VoyageOperation;

public class TreeHelper implements TreeSelectionListener {

	private static TreeHelper instance;

	private AutoscrollableJTree tree;

	private DefaultMutableTreeNode zeroRoot;

	private DefaultMutableTreeNode importRoot;

	private DefaultMutableTreeNode exportRoot;

	private DefaultTreeModel treeModel;

	private DefaultMutableTreeNode selectedNode;

	private MainFrame _anaPencere;

	private Long voyageId = null;

	private Long blId = null;

	private TreeHelper() {
	}

	public static TreeHelper getInstance() {
		if (instance == null) {
			instance = new TreeHelper();
		}
		return instance;
	}

	public JTree getTree(MainFrame anaPencere) {
		if (tree == null) {
			// Agacta baslangicta sadece IMPORT ve EXPORT olacak
			tree = new AutoscrollableJTree();
			tree.getSelectionModel().setSelectionMode(
					TreeSelectionModel.SINGLE_TREE_SELECTION);
			tree.setAutoscrolls(true);
			tree.addTreeSelectionListener(this);
			tree.setModel(getTreeModel());
			this._anaPencere = anaPencere;
		}
		return tree;
	}

	private DefaultTreeModel getTreeModel() {
		if (treeModel == null) {
			zeroRoot = new DefaultMutableTreeNode("Kumanifest");
			importRoot = new DefaultMutableTreeNode("IMPORT");
			exportRoot = new DefaultMutableTreeNode("EXPORT");
			zeroRoot.add(importRoot);
			zeroRoot.add(exportRoot);
			treeModel = new DefaultTreeModel(zeroRoot);
		}
		return treeModel;
	}

	public void valueChanged(TreeSelectionEvent e) {
		try {
			updateLeafs();
		} catch (Exception ex) {
			LogHelper.getInstance().exception(ex);
		}
		tree.validate();
		tree.repaint();
	}

	private void expandLeaf(DefaultMutableTreeNode root) {
		Enumeration<?> enumeration = root.depthFirstEnumeration();

		for (; enumeration.hasMoreElements();) {
			TreePath t = new TreePath(((DefaultMutableTreeNode) enumeration
					.nextElement()).getPath());
			tree.expandPath(t);
		}
	}

	private void collapseLeaf(DefaultMutableTreeNode root) {
		Enumeration<?> enumeration = root.depthFirstEnumeration();

		for (; enumeration.hasMoreElements();) {
			TreePath t = new TreePath(((DefaultMutableTreeNode) enumeration
					.nextElement()).getPath());
			tree.collapsePath(t);
		}
	}

	private void updateLeafs() {
		// Bu metodun icerisinde tiklanan dalin alt dallarini yuklemeliyiz
		try {
			selectedNode = (DefaultMutableTreeNode) tree
					.getLastSelectedPathComponent();
			Object userObject = selectedNode.getUserObject();
			_anaPencere.loadBL(null);
			MenuHelper.getInstance().setRaporlar(false);
			if (userObject instanceof Office) {
				updateOfficeLeaf(selectedNode);
			} else if (userObject instanceof Vessel) {
				updateVesselLeaf(selectedNode);
			} else if (userObject instanceof Voyage) {
				voyageId = ((Voyage) userObject).getVoyageId();
				updateVoyageLeaf(selectedNode);
			} else if (userObject instanceof BL) {
				blId = ((BL) userObject).getBlId();
				_anaPencere.loadBL(blId);
				MenuHelper.getInstance().setRaporlar(true);
			} else {
				if (userObject.toString().equals("EXPORT")) {
					updateTypeLeaf(exportRoot);
				} else if (userObject.toString().equals("IMPORT")) {
					updateTypeLeaf(importRoot);
				}
			}
		} catch (NullPointerException e) {
			LogHelper.getInstance().finest("NPE");
		}
	}

	private void updateTypeLeaf(DefaultMutableTreeNode root) {
		// tipi type olan vesseller yuklenecek
		ArrayList<Office> al = OfficeOperation.getInstance().ara(new Office());
		// Bu arrayi dolasip agaca dal olarak ekleyecegiz
		root.removeAllChildren();
		for (Office office : al) {
			root.add(new DefaultMutableTreeNode(office));
		}
		expandLeaf(root);
	}

	private void updateOfficeLeaf(DefaultMutableTreeNode root) {
		// Secilen Office'in Vessel'lerini dolduracagiz
		// Ofis ve Tipini ayarlamamiz gerekli

		ArrayList<Vessel> al = VesselOperation.getInstance().getAgacVerisi(
				((Office) root.getUserObject()).getOfficeId(),
				getIsExport(root));
		// Bu arrayi dolasip agaca dal olarak ekleyecegiz
		root.removeAllChildren();
		for (Vessel v : al) {
			root.add(new DefaultMutableTreeNode(v));
		}
		expandLeaf(root);
	}

	private void updateVesselLeaf(DefaultMutableTreeNode root) {
		// Secilen Vessel'in Voyage'larini dolduracagiz
		Long officeId = ((Office) ((DefaultMutableTreeNode) root.getParent())
				.getUserObject()).getOfficeId();
		Long vesselId = ((Vessel) root.getUserObject()).getVesselId();
		ArrayList<Voyage> al = VoyageOperation.getInstance().getAgacVerisi(
				officeId, vesselId, getIsExport(root));
		// Bu arrayi dolasip agaca dal olarak ekleyecegiz
		root.removeAllChildren();
		for (Voyage v : al) {
			root.add(new DefaultMutableTreeNode(v));
		}
		expandLeaf(root);
	}

	private void updateVoyageLeaf(DefaultMutableTreeNode root) {
		// Secilen Voyage'in BL'lerini dolduracagiz
		BL bl = new BL();
		/*
		 * Long officeId =
		 * ((Office)((DefaultMutableTreeNode)root.getParent().getParent()).getUserObject()).getOfficeId();
		 * Long vesselId =
		 * ((Vessel)((DefaultMutableTreeNode)root.getParent()).getUserObject()).getVesselId();
		 * Long voyageId = ((Voyage)root.getUserObject()).getVoyageId();
		 */
		bl.setVoyage((Voyage) root.getUserObject());
		ArrayList<BL> al = BLOperation.getInstance().ara(bl);
		// Bu arrayi dolasip agaca dal olarak ekleyecegiz
		root.removeAllChildren();
		for (BL b : al) {
			root.add(new DefaultMutableTreeNode(b));
		}
		expandLeaf(root);
	}

	private int getIsExport(DefaultMutableTreeNode node) {
		if (node.getUserObject() != null
				&& node.getUserObject() instanceof VeriSinif) {
			return getIsExport((DefaultMutableTreeNode) node.getParent());
		}
		if (node.toString().equals("EXPORT")) {
			return 1;
		} else if (node.toString().equals("IMPORT")) {
			return 0;
		}
		// Bu duruma dusmesini beklemiyoruz
		return -1;
	}

	public Long getVoyageId() {
		return voyageId;
	}

	public Long getBLId() {
		return blId;
	}

	public void updateTree() {
		TreeNode node;
		if (selectedNode.getUserObject() instanceof BL) {
			node = (TreeNode) selectedNode.getParent();
		} else {
			node = selectedNode;
		}

		((DefaultMutableTreeNode) node).removeAllChildren();

		((DefaultTreeModel) tree.getModel()).reload();

		TreePath path = new TreePath(((DefaultTreeModel) tree.getModel())
				.getPathToRoot(node));
		tree.setSelectionPath(path);
	}
}