package uk.co.elionline.gears.utilities;

public interface Observer<M> {
	public void notify(M message);
}
