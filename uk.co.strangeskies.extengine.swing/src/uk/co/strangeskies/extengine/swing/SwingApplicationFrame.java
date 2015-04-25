package uk.co.strangeskies.extengine.swing;

import javax.swing.JFrame;

public interface SwingApplicationFrame extends SwingApplicationContainer {
	@Override
	public JFrame getContainer();
}
