package uk.co.elionline.emerge.swing;

import javax.swing.JFrame;

public interface SwingApplicationFrame extends SwingApplicationContainer {
	@Override
	public JFrame getContainer();
}
