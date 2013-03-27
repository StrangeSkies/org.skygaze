package uk.co.elionline.emerge.utilities.flowcontrol;

import java.util.Collection;

public interface StripedReadWriteLockRelease<K> {
	public boolean releaseLocks(Collection<K> readKeys, Collection<K> writeKeys);

	public boolean releaseLock(K key);

	public boolean downgradeLock(K key);

	public boolean isLockHeldByCurrentThread(K key);

	public boolean releaseReadLocks(Collection<K> readKeys);

	public boolean releaseReadLock(K key);

	public boolean isReadLockHeldByCurrentThread(K key);

	public boolean releaseWriteLocks(Collection<K> writeKeys);

	public boolean releaseWriteLock(K key);

	public boolean isWriteLockHeldByCurrentThread(K key);

	public void wait(K key) throws InterruptedException;

	public void wait(K key, long milliseconds) throws InterruptedException;

	public void wait(K key, long milliseconds, int nanoseconds)
			throws InterruptedException;
}
