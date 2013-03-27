package uk.co.elionline.gears.utilities;

public interface Observable<M> {
	public boolean addObserver(Observer<? super M> observer);

	public boolean removeObserver(Observer<? super M> observer);

	public void clearObservers();
}
