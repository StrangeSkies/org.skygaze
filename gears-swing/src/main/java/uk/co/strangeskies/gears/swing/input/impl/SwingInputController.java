package uk.co.strangeskies.gears.swing.input.impl;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import javax.swing.JFrame;

import org.osgi.service.component.annotations.Component;

import uk.co.strangeskies.gears.input.BasicInputController;
import uk.co.strangeskies.gears.input.KeyboardInputController;
import uk.co.strangeskies.gears.input.MouseInputController;
import uk.co.strangeskies.gears.input.WindowManagerInputController;
import uk.co.strangeskies.gears.mathematics.expression.buffer.DoubleBuffer;
import uk.co.strangeskies.gears.mathematics.expression.buffer.FunctionBuffer;
import uk.co.strangeskies.gears.mathematics.geometry.matrix.building.MatrixBuilder;
import uk.co.strangeskies.gears.mathematics.geometry.matrix.vector.Vector2;
import uk.co.strangeskies.gears.mathematics.values.IntValue;

@Component(service = { MouseInputController.class,
		KeyboardInputController.class, WindowManagerInputController.class })
public class SwingInputController implements BasicInputController,
		MouseListener, WindowListener, KeyListener {
	private final FunctionBuffer<HashSet<Integer>, HashSet<Integer>> keysDown;
	private final FunctionBuffer<ArrayList<Integer>, ArrayList<Integer>> keysPressed;

	private final FunctionBuffer<HashSet<Integer>, HashSet<Integer>> buttonsDown;
	private final FunctionBuffer<ArrayList<Integer>, ArrayList<Integer>> buttonsPressed;

	private JFrame frame;

	private final DoubleBuffer<Boolean, Boolean> windowClosing;
	private final DoubleBuffer<Boolean, Boolean> windowMinimising;
	private final DoubleBuffer<Boolean, Boolean> windowRestoring;
	private final DoubleBuffer<Boolean, Boolean> windowMinimised;

	private Vector2<IntValue> mousePosition;
	private final DoubleBuffer<Boolean, Boolean> mouseInside;
	private final DoubleBuffer<Boolean, Boolean> mouseEntering;
	private final DoubleBuffer<Boolean, Boolean> mouseExiting;

	private final KeyRepeatSuppressor keyRepeatSuppressor;

	private final Robot robot;

	private MatrixBuilder matrices;

	@SuppressWarnings("unchecked")
	public SwingInputController() {
		keysDown = new FunctionBuffer<>(new HashSet<Integer>(),
				c -> (HashSet<Integer>) c.clone());
		keysPressed = new FunctionBuffer<>(new ArrayList<Integer>(),
				c -> (ArrayList<Integer>) c.clone());

		buttonsDown = new FunctionBuffer<>(new HashSet<Integer>(),
				c -> (HashSet<Integer>) c.clone());
		buttonsPressed = new FunctionBuffer<>(new ArrayList<Integer>(),
				c -> (ArrayList<Integer>) c.clone());

		windowClosing = new FunctionBuffer<>(false, Function.identity());
		windowMinimising = new FunctionBuffer<>(false, Function.identity());
		windowRestoring = new FunctionBuffer<>(false, Function.identity());
		windowMinimised = new FunctionBuffer<>(false, Function.identity());

		mouseInside = new FunctionBuffer<>(false, Function.identity());
		mouseEntering = new FunctionBuffer<>(false, Function.identity());
		mouseExiting = new FunctionBuffer<>(false, Function.identity());

		mousePosition = getActualMousePosition();

		try {
			robot = new Robot();
			keyRepeatSuppressor = new KeyRepeatSuppressor(true, true, false, robot);
		} catch (AWTException e) {
			throw new IllegalStateException(e);
		}

		keyRepeatSuppressor.addKeyListener(this);
	}

	public void setFrame(JFrame frame) {
		if (this.frame != null) {
			keyRepeatSuppressor.removeFrom(this.frame);
			this.frame.removeMouseListener(this);
			this.frame.removeWindowListener(this);
		}

		frame.requestFocusInWindow();

		frame.addMouseListener(this);
		frame.addWindowListener(this);
		keyRepeatSuppressor.installTo(frame);

		this.frame = frame;
	}

	public void setMatrixBuilder(MatrixBuilder matrices) {
		this.matrices = matrices;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		keysDown.getBack().add(keyCode);
		keysDown.invalidateBack();

		keysPressed.getBack().add(keyCode);
		keysPressed.invalidateBack();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();

		keysDown.getBack().remove(keyCode);
		keysDown.invalidateBack();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		mouseInside.setBack(true);
		if (mouseExiting.getBack()) {
			mouseExiting.setBack(false);
		} else {
			mouseEntering.setBack(true);
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		mouseInside.setBack(false);
		if (mouseEntering.getBack()) {
			mouseEntering.setBack(false);
		} else {
			mouseExiting.setBack(true);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (!buttonsDown.getBack().contains(e.getButton())) {
			buttonsDown.getBack().add(e.getButton());
			buttonsDown.invalidateBack();
		}
		buttonsPressed.getBack().add(e.getButton());
		buttonsPressed.invalidateBack();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		buttonsDown.getBack().remove(e.getButton());
		buttonsDown.invalidateBack();
	}

	@Override
	public void buffer() {
		keysDown.push();
		buttonsDown.push();

		keysPressed.push();
		keysPressed.getBack().clear();
		keysPressed.invalidateBack();

		buttonsPressed.push();
		buttonsPressed.getBack().clear();
		buttonsPressed.invalidateBack();

		windowClosing.push();
		windowClosing.setBack(false);

		windowMinimised.push();

		windowMinimising.push();
		windowMinimising.setBack(false);

		windowRestoring.push();
		windowRestoring.setBack(false);

		mouseInside.push();
		mouseEntering.push();
		mouseExiting.push();

		mousePosition = getActualMousePosition();
	}

	private Vector2<IntValue> getActualMousePosition() {
		Vector2<IntValue> actualMousePosition = matrices.ints().vector2()
				.setData(MouseInfo.getPointerInfo().getLocation());
		actualMousePosition.subtract(getWindowPosition());

		return actualMousePosition;
	}

	public boolean isWindowClosed() {
		return windowClosing.getFront();
	}

	@Override
	public List<Integer> getKeysPressed() {
		return keysPressed.getFront();
	}

	@Override
	public Set<Integer> getKeysDown() {
		return keysDown.getFront();
	}

	@Override
	public List<Integer> getMouseButtonsPressed() {
		return buttonsPressed.getFront();
	}

	@Override
	public Set<Integer> getMouseButtonsDown() {
		return buttonsDown.getFront();
	}

	@Override
	public Vector2<IntValue> getMousePosition() {
		if (mousePosition == null) {
			return null;
		}
		return mousePosition.copy();
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		windowClosing.setBack(true);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		windowRestoring.setBack(true);
		windowMinimised.setBack(false);
	}

	@Override
	public void windowIconified(WindowEvent e) {
		windowMinimising.setBack(true);
		windowMinimised.setBack(true);
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public boolean isWindowMinimising() {
		return windowMinimising.getFront();
	}

	@Override
	public boolean isWindowRestoring() {
		return windowRestoring.getFront();
	}

	@Override
	public boolean isWindowMinimised() {
		return windowMinimised.getFront();
	}

	@Override
	public boolean isWindowClosing() {
		return windowClosing.getFront();
	}

	@Override
	public void setMousePosition(Vector2<IntValue> mousePosition) {
		this.mousePosition.set(mousePosition);

		mousePosition = mousePosition.getAdded(getWindowPosition());
		robot.mouseMove(mousePosition.getX().intValue(), mousePosition.getY()
				.intValue());
	}

	@Override
	public void moveMousePosition(Vector2<IntValue> mousePositionDelta) {
		Vector2<IntValue> mousePosition = getActualMousePosition();
		mousePosition = mousePosition.add(mousePositionDelta).add(
				getWindowPosition());
		robot.mouseMove(mousePosition.getX().intValue(), mousePosition.getY()
				.intValue());
	}

	@Override
	public boolean isMouseInside() {
		return mouseInside.getFront();
	}

	@Override
	public boolean isMouseExiting() {
		return mouseExiting.getFront();
	}

	@Override
	public boolean isMouseEntering() {
		return mouseEntering.getFront();
	}

	@Override
	public Vector2<IntValue> getWindowSize() {
		return matrices
				.ints()
				.vector2()
				.setData(frame.getContentPane().getWidth(),
						frame.getContentPane().getHeight());
	}

	@Override
	public Vector2<IntValue> getWindowPosition() {
		return matrices.ints().vector2()
				.setData(frame.getContentPane().getLocationOnScreen());
	}
}
