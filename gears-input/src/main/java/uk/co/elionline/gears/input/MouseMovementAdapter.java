package uk.co.elionline.gears.input;

import uk.co.elionline.gears.mathematics.expressions.collections.ExpressionBuffer;
import uk.co.elionline.gears.mathematics.expressions.collections.ExpressionResultSettingBuffer;
import uk.co.elionline.gears.mathematics.geometry.Bounds2;
import uk.co.elionline.gears.mathematics.geometry.matrix.Vector2;
import uk.co.elionline.gears.mathematics.values.DoubleValue;
import uk.co.elionline.gears.mathematics.values.DoubleValueFactory;
import uk.co.elionline.gears.mathematics.values.IntValue;
import uk.co.elionline.gears.mathematics.values.IntValueFactory;

public class MouseMovementAdapter {
	public enum MovementType {
		Relative, Absolute
	}

	private final MouseInputController mouseInputController;
	private final WindowManagerInputController windowManagerInputController;

	private final ExpressionBuffer<Vector2<IntValue>, Vector2<IntValue>> mousePosition;
	private final ExpressionBuffer<Vector2<IntValue>, Vector2<IntValue>> position;

	private MovementType movementType;

	public MouseMovementAdapter(MouseInputController mouseInputController,
			WindowManagerInputController windowManagerInputController) {
		this.mouseInputController = mouseInputController;
		this.windowManagerInputController = windowManagerInputController;

		mousePosition = new ExpressionResultSettingBuffer<>(new Vector2<>(
				IntValueFactory.instance()), new Vector2<>(IntValueFactory.instance()));
		position = new ExpressionResultSettingBuffer<>(new Vector2<>(
				IntValueFactory.instance()), new Vector2<>(IntValueFactory.instance()));
		updateMousePosition();

		setMovementType(MovementType.Absolute);
	}

	public ExpressionBuffer<Vector2<IntValue>, Vector2<IntValue>> getPosition() {
		return position;
	}

	public void setMovementType(MovementType movementType) {
		this.movementType = movementType;

		if (movementType == MovementType.Absolute) {
			mouseInputController.setMousePosition(new Vector2<IntValue>(
					IntValueFactory.instance(), getPosition().getBack()));
			Vector2<IntValue> a = mousePosition.getBack();
			Vector2<IntValue> b = getPosition().getBack();
			a.set(b);
			mousePosition.getFront().setData(getPosition().getFront());
		}
	}

	public MovementType getMovementType() {
		return movementType;
	}

	public void reset() {
		Vector2<IntValue> center = windowManagerInputController.getWindowSize()
				.getMultiplied(0.5);

		getPosition()
				.set(new Vector2<IntValue>(IntValueFactory.instance(), center));

		mouseInputController.setMousePosition(center);

		mousePosition.setBack(new Vector2<IntValue>(center));
		mousePosition.push();
	}

	public void prepare() {
		updateMousePosition();

		getPosition().push();

		if (movementType == MovementType.Absolute) {
			getPosition().getBack().set(mousePosition.getBack());
		} else {
			getPosition().getBack().add(getDelta());
		}
	}

	public void finalise() {
		if (movementType == MovementType.Absolute
				&& !getPosition().equals(mousePosition)) {
			Vector2<IntValue> delta = new Vector2<IntValue>(
					IntValueFactory.instance(), getPosition().getBack())
					.add(mousePosition.getBack().getNegated());

			mousePosition.getBack().set(getPosition().getBack());
			mousePosition.getFront().set(getPosition().getFront());

			mouseInputController.moveMousePosition(delta);
		}
	}

	private Vector2<DoubleValue> getDelta() {
		Vector2<IntValue> center = windowManagerInputController.getWindowSize()
				.getMultiplied(0.5);

		Vector2<IntValue> delta = mousePosition.getBack().getSubtracted(
				mousePosition.getFront());

		Bounds2<IntValue> bounds = new Bounds2<IntValue>(
				windowManagerInputController.getWindowSize().getMultiplied(0.25),
				windowManagerInputController.getWindowSize().getMultiplied(0.75));

		if (!bounds.getRangeX().contains(mousePosition.getBack().getX())) {
			mousePosition.getBack().getX().setValue(center.getX());
			mouseInputController.setMousePosition(mousePosition.getBack());
		}
		if (!bounds.getRangeY().contains(mousePosition.getBack().getY())) {
			mousePosition.getBack().getY().setValue(center.getY());
			mouseInputController.setMousePosition(mousePosition.getBack());
		}

		return new Vector2<DoubleValue>(DoubleValueFactory.instance(), delta);
	}

	private void updateMousePosition() {
		mousePosition.push();
		mousePosition.getBack().set(mouseInputController.getMousePosition());
	}
}
