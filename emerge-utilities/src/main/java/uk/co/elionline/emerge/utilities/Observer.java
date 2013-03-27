package uk.co.elionline.emerge.utilities;

public interface Observer<M> {
	public void notify(M message);
}
