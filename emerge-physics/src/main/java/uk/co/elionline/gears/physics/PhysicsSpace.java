package uk.co.elionline.gears.physics;

public abstract class PhysicsSpace {
	private int iterations;
	private int frequency;

	public PhysicsSpace(int frequency, int iterations) {
		setFrequency(frequency);
		setIterations(iterations);
	}

	public void step() {

	}

	public int getIterations() {
		return iterations;
	}

	public void setIterations(int iterations) {
		this.iterations = iterations;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public abstract PhysicsBody createBody();
}
