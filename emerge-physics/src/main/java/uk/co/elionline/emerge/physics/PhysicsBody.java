package uk.co.elionline.emerge.physics;

import uk.co.elionline.emerge.utilities.Copyable;

public class PhysicsBody implements Copyable<PhysicsBody> {
	@Override
	public PhysicsBody copy() {
		return new PhysicsBody();
	}
}
