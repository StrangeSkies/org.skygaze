package uk.co.strangeskies.gears.swing;

import javax.swing.JFrame;

public interface SwingApplicationFrame extends SwingApplicationContainer {
	@Override
	public JFrame getContainer();
}
