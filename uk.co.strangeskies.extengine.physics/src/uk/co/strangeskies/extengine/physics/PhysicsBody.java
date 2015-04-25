package uk.co.strangeskies.extengine.physics;

import uk.co.strangeskies.utilities.Copyable;

public class PhysicsBody implements Copyable<PhysicsBody> {
	@Override
	public PhysicsBody copy() {
		return new PhysicsBody();
	}
}
