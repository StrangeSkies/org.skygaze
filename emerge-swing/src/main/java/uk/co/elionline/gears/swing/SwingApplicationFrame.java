package uk.co.elionline.gears.swing;

import javax.swing.JFrame;

public interface SwingApplicationFrame extends SwingApplicationContainer {
	@Override
	public JFrame getContainer();
}
