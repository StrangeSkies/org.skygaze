package uk.co.elionline.gears.utilities.osgi.internal;

import uk.co.elionline.gears.utilities.osgi.ServiceWrapper;
import uk.co.elionline.gears.utilities.osgi.ServiceWrapper.HideServices;

class ManagedServiceWrapper<T> implements Comparable<ManagedServiceWrapper<?>> {
	private final ServiceWrapper<T> serviceWrapper;
	private final Integer serviceRanking;
	private final HideServices hideServices;

	public ManagedServiceWrapper(ServiceWrapper<T> serviceWrapper,
			Integer serviceRanking, HideServices hideServices) {
		this.serviceWrapper = serviceWrapper;
		this.serviceRanking = serviceRanking;
		this.hideServices = hideServices;
	}

	public ServiceWrapper<T> getServiceWrapper() {
		return serviceWrapper;
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

		return serviceWrapper
				.equals(((ManagedServiceWrapper<?>) obj).serviceWrapper);
	}

	@Override
	public int compareTo(ManagedServiceWrapper<?> o) {
		int compare = serviceRanking.compareTo(o.getServiceRanking());

		if (compare == 0) {

		}

		return compare;
	}
}
