package uk.co.elionline.gears.entity.components.input;

import uk.co.elionline.gears.mathematics.geometry.matrix.Vector2;
import uk.co.elionline.gears.mathematics.values.IntValue;
import uk.co.elionline.gears.utilities.Copyable;

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
