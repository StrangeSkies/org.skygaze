package uk.co.elionline.emerge.rendering;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.net.URL;

import javax.imageio.ImageIO;

public class Sprite {
	/** The image to be drawn for this sprite */
	private final Image image;

	/**
	 * Create a new sprite based on an image
	 * 
	 * @param image
	 *          The image that is this sprite
	 */
	public Sprite(String resource) {
		URL url = this.getClass().getClassLoader().getResource(resource);
		Image image = null;
		try {
			image = ImageIO.read(url);
		} catch (Exception e) {
		}

		GraphicsConfiguration gc = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration();
		this.image = gc.createCompatibleImage(image.getWidth(null),
				image.getHeight(null), Transparency.TRANSLUCENT);
		this.image.getGraphics().drawImage(image, 0, 0, null);
	}

	public Image getImage() {
		return image;
	}

	/**
	 * Get the width of the drawn sprite
	 * 
	 * @return The width in pixels of this sprite
	 */
	public int getWidth() {
		return image.getWidth(null);
	}

	/**
	 * Get the height of the drawn sprite
	 * 
	 * @return The height in pixels of this sprite
	 */
	public int getHeight() {
		return image.getHeight(null);
	}
}
