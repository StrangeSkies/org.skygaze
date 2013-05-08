package uk.co.elionline.gears.utilities.osgi.internal;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uk.co.elionline.gears.utilities.osgi.ServiceWrapper;

public class CompoundWrappedServiceSet<T> {
	private final Set<List<ServiceWrapper<T>>> serviceWrappers;

	public CompoundWrappedServiceSet() {
		serviceWrappers = new HashSet<>();
	}

	public void add(ServiceWrapper<T> serviceWrapper, int priority,
			boolean hidesPrevious) {

	}

	public void remove(ServiceWrapper<T> serviceWrapper) {

	}
}
