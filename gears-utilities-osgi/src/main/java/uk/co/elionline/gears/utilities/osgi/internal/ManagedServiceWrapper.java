package uk.co.elionline.gears.utilities.osgi.internal;

import java.util.Map;

import uk.co.elionline.gears.utilities.Decorator;
import uk.co.elionline.gears.utilities.osgi.ServiceWrapper;

class ManagedServiceWrapper<T> extends Decorator<ServiceWrapper<T>> implements
		ServiceWrapper<T>, Comparable<ManagedServiceWrapper<?>> {
	private final Integer serviceRanking;
	private final HideServices hideServices;

	public ManagedServiceWrapper(ServiceWrapper<T> serviceWrapper,
			Integer serviceRanking, HideServices hideServices) {
		super(serviceWrapper);

		this.serviceRanking = serviceRanking;
		this.hideServices = hideServices;
	}

	public Integer getServiceRanking() {
		return serviceRanking;
	}

	public HideServices getHideServices() {
		return hideServices;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof ManagedServiceWrapper))
			return false;

		return getComponent().equals(
				((ManagedServiceWrapper<?>) obj).getComponent());
	}

	@Override
	public int compareTo(ManagedServiceWrapper<?> o) {
		int compare = serviceRanking.compareTo(o.getServiceRanking());

		if (compare == 0) {

		}

		return compare;
	}

	@Override
	public T wrapService(T service) {
		return getComponent().wrapService(service);
	}

	@Override
	public void unwrapService(T service) {
		getComponent().unwrapService(service);
	}

	@Override
	public boolean wrapServiceProperties(Map<String, Object> serviceProperties) {
		return getComponent().wrapServiceProperties(serviceProperties);
	}

	@Override
	public Class<T> getServiceClass() {
		return getComponent().getServiceClass();
	}
}
