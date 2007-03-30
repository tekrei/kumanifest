package net.kodveus.gui.araclar;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

/**
 * Resim yakalama ve saklama islemleri icin kullanilabilir
 * @author emre
 *
 */
public class SwingAraclar {

	public static void captureScreen(String fileName) throws Exception {
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize();
		Rectangle screenRectangle = new Rectangle(screenSize);
		Robot robot = new Robot();
		BufferedImage image = robot.createScreenCapture(screenRectangle);
		writeImage(image, fileName);
	}

	private static void writeImage(RenderedImage image, String fileName)
			throws IOException {
		ImageIO.write(image, getImageFormatName(fileName), new File(fileName));
	}

	private static String getImageFormatName(String fileName) {
		if (fileName.endsWith(".png"))
			return "png";
		throw new IllegalArgumentException(fileName);
	}

	public static void captureBounds(Rectangle bounds, String fileName)
			throws Exception {
		Robot robot = new Robot();
		BufferedImage image = robot.createScreenCapture(bounds);
		writeImage(image, fileName);
	}

	public static void captureJComponent(JComponent component, String fileName)
			throws IOException {
		Dimension size = component.getSize();
		BufferedImage image = new BufferedImage(size.width, size.height,
				BufferedImage.TYPE_INT_RGB);
		component.paint(image.getGraphics());
		writeImage(image, fileName);
	}

	public static void captureFrame(JFrame frame, String fileName)
			throws Exception {
		Robot robot = new Robot();
		BufferedImage image = robot.createScreenCapture(frame.getBounds());
		writeImage(image, fileName);
	}

	public static Action createCaptureAction(JFrame frame, String fileName) {
		return createCaptureAction(frame.getRootPane(), frame, fileName);
	}

	private static Action createCaptureAction(final JComponent component,
			final Component boundsComponent, final String fileName) {
		Action captureAction = new AbstractAction() {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent event) {
				try {
					int x = boundsComponent.getLocationOnScreen().x;
					int y = boundsComponent.getLocationOnScreen().y;
					int width = boundsComponent.getBounds().width;
					int height = boundsComponent.getBounds().height;
					Rectangle bounds = new Rectangle(x, y, width, height);
					captureBounds(bounds, fileName);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		String actionCommand = "capture";
		String actionLabel = "Capture";
		KeyStroke keyStroke = KeyStroke.getKeyStroke("F12");
		captureAction.putValue(Action.ACTION_COMMAND_KEY, actionCommand);
		captureAction.putValue(Action.NAME, actionLabel);
		captureAction.putValue(Action.ACCELERATOR_KEY, keyStroke);
		component.getActionMap().put(actionCommand, captureAction);
		component.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
				.put(keyStroke, actionCommand);
		return captureAction;
	}

	public static Action createCaptureAction(JComponent component,
			String fileName) {
		return createCaptureAction(component, component, fileName);
	}

	public static Action createCaptureAction(JDialog dialog, String fileName) {
		return createCaptureAction(dialog.getRootPane(), dialog, fileName);
	}
}
