package uk.co.elionline.gears.contexts;

import javax.swing.JFrame;

import uk.co.elionline.gears.input.BasicInputController;
import uk.co.elionline.gears.rendering.Renderer2D;
import uk.co.elionline.gears.swing.input.SwingInputController;
import uk.co.elionline.gears.swing.rendering.SwingComponentRenderer;

public class Engine {
	private ContextStack contextStack;
	private BasicInputController inputController;
	private JFrame frame;
	private Renderer2D renderer;

	// private Renderer render;

	private void initialise(String title) {
		// create our window
		frame = new JFrame("Test2");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);

		// create our rendering context and set it to be the content of our window
		SwingComponentRenderer renderer = new SwingComponentRenderer();
		this.renderer = renderer;
		frame.getContentPane().add(renderer.getComponent());
		renderer.initialise();
		renderer.requestSize(800, 600);
		renderer.getComponent().setFocusable(false);

		frame.setLocationRelativeTo(null);
		frame.pack();

		// set up the input pane
		inputController = new SwingInputController(frame);
	}

	public Engine(final String title) {
		initialise(title);
	}

	public void run(final Context firstContext) {
		contextStack = new ContextStack(renderer, inputController);
		new Thread(new Runnable() {
			@Override
			public void run() {
				contextStack.push(firstContext);
			}
		}).run();
	}

	public void end() {
		frame.dispose();
	}

	public JFrame getFrame() {
		return frame;
	}
}
