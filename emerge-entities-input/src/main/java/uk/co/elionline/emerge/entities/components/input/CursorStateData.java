package uk.co.elionline.emerge.entities.components.input;

import uk.co.elionline.emerge.mathematics.geometry.matrices.Vector2;
import uk.co.elionline.emerge.mathematics.values.IntValue;
import uk.co.elionline.emerge.utilities.Copyable;

public class CursorStateData implements Copyable<CursorStateData> {
	private Vector2<IntValue> position;
	private Vector2<IntValue> velocity;

	public CursorStateData() {
	}

	public CursorStateData(CursorStateData that) {
		getPosition().set(that.getPosition());
		getVelocity().set(that.getVelocity());
	}

	@Override
	public CursorStateData copy() {
		return new CursorStateData(this);
	}

	public Vector2<IntValue> getPosition() {
		return position;
	}

	public Vector2<IntValue> getVelocity() {
		return velocity;
	}
}