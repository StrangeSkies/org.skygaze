package uk.co.elionline.gears.input;

import uk.co.elionline.gears.mathematics.expressions.collections.ExpressionBuffer;
import uk.co.elionline.gears.mathematics.expressions.collections.ExpressionResultSettingBuffer;
import uk.co.elionline.gears.mathematics.geometry.Bounds2;
import uk.co.elionline.gears.mathematics.geometry.matrix.builder.MatrixBuilder;
import uk.co.elionline.gears.mathematics.geometry.matrix.vector.Vector2;
import uk.co.elionline.gears.mathematics.values.DoubleValue;
import uk.co.elionline.gears.mathematics.values.IntValue;

public class MouseMovementAdapter {
	public enum MovementType {
		Relative, Absolute
	}

	private final MouseInputController mouseInputController;
	private final WindowManagerInputController windowManagerInputController;

	private final ExpressionBuffer<Vector2<IntValue>, Vector2<IntValue>> mousePosition;
	private final ExpressionBuffer<Vector2<IntValue>, Vector2<IntValue>> position;

	private MovementType movementType;

	private final MatrixBuilder matrixBuilder;

	public MouseMovementAdapter(MouseInputController mouseInputController,
			WindowManagerInputController windowManagerInputController,
			MatrixBuilder matrixBuilder) {
		this.mouseInputController = mouseInputController;
		this.windowManagerInputController = windowManagerInputController;

		this.matrixBuilder = matrixBuilder;

		mousePosition = new ExpressionResultSettingBuffer<>(matrixBuilder.ints()
				.vector2(), matrixBuilder.ints().vector2());
		position = new ExpressionResultSettingBuffer<>(matrixBuilder.ints()
				.vector2(), matrixBuilder.ints().vector2());
		updateMousePosition();

		setMovementType(MovementType.Absolute);
	}

	public ExpressionBuffer<Vector2<IntValue>, Vector2<IntValue>> getPosition() {
		return position;
	}

	public void setMovementType(MovementType movementType) {
		this.movementType = movementType;

		if (movementType == MovementType.Absolute) {
			mouseInputController.setMousePosition(getPosition().getBack().copy());
			mousePosition.getBack().set(getPosition().getBack());
			mousePosition.getFront().set(getPosition().getFront());
		}
	}

	public MovementType getMovementType() {
		return movementType;
	}

	public void reset() {
		Vector2<IntValue> center = windowManagerInputController.getWindowSize()
				.getMultiplied(0.5);

		getPosition().set(center.copy());

		mouseInputController.setMousePosition(center);

		mousePosition.setBack(center.copy());
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
			Vector2<IntValue> delta = getPosition().getBack().getAdded(
					mousePosition.getBack().getNegated());

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

		return matrixBuilder.doubles().vector2().set(delta);
	}

	private void updateMousePosition() {
		mousePosition.push();
		mousePosition.getBack().set(mouseInputController.getMousePosition());
	}
}
