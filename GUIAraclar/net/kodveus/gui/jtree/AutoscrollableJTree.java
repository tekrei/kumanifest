package net.kodveus.gui.jtree;

import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.dnd.Autoscroll;

import javax.swing.JTree;

public class AutoscrollableJTree extends JTree implements Autoscroll {
	private static final long serialVersionUID = 1L;

	public AutoscrollableJTree() {
		super();
	}

	public void autoscroll(Point p) {
		int realrow = getRowForLocation(p.x, p.y);
		Rectangle outer = getBounds();
		realrow = (((p.y + outer.y) <= 12) ? ((realrow < 1) ? 0 : (realrow - 1))
				: ((realrow < (getRowCount() - 1)) ? (realrow + 1) : realrow));
		scrollRowToVisible(realrow);
	}

	public Insets getAutoscrollInsets() {
		Rectangle outer = getBounds();
		Rectangle inner = getParent().getBounds();

		return new Insets(inner.y - outer.y + 12, inner.x - outer.x + 12,
				outer.height - inner.height - inner.y + outer.y + 12,
				outer.width - inner.width - inner.x + outer.x + 12);
	}
}