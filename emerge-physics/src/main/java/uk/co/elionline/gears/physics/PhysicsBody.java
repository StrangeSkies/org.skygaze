package uk.co.elionline.gears.physics;

import uk.co.elionline.gears.utilities.Copyable;

public class PhysicsBody implements Copyable<PhysicsBody> {
	@Override
	public PhysicsBody copy() {
		return new PhysicsBody();
	}
}
