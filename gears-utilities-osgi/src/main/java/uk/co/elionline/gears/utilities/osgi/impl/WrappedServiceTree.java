package uk.co.elionline.gears.utilities.osgi.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import uk.co.elionline.gears.utilities.osgi.ServiceWrapper.HideServices;

public class WrappedServiceTree extends WrappedService {
	private final ServiceReference<?> serviceReference;

	private final Set<CompoundWrappedService> wrappedServices;

	private boolean visible;

	WrappedServiceTree(ServiceReference<?> serviceReference,
			Collection<? extends ManagedServiceWrapper<?>> serviceWrappers) {
		super(serviceReference.getBundle().getBundleContext()
				.getService(serviceReference), getProperties(serviceReference));
		this.serviceReference = serviceReference;

		wrappedServices = new HashSet<>();

		visible = true;

		Set<WrappedService> workingSet = new HashSet<>();
		workingSet.add(this);

		SortedSet<ManagedServiceWrapper<?>> orderedServiceWrappers = new TreeSet<>(
				new ManagedServiceWrapperComparator());
		orderedServiceWrappers.addAll(serviceWrappers);

		CompoundWrappedService wrappingService;
		for (ManagedServiceWrapper<?> serviceWrapper : orderedServiceWrappers)
			for (WrappedService wrappedService : new HashSet<>(workingSet))
				if (wrappedService.isVisible()
						&& (wrappingService = wrappedService.wrap(serviceWrapper)) != null)
					workingSet.add(wrappingService);
	}

	public void register() {
		BundleContext context = serviceReference.getBundle().getBundleContext();
		String[] classNames = getClassNames(serviceReference);

		Set<WrappedService> workingSet = new HashSet<>();
		workingSet.add(this);

		for (WrappedService wrappedService : new HashSet<>(workingSet)) {
			workingSet.addAll(wrappedService.getWrappedServices());
			workingSet.remove(wrappedService);

			if (wrappedService.isVisible())
				context.registerService(classNames, wrappedService.getService(),
						wrappedService.getProperties());
		}
	}

	public void unregister() {
		// TODO Auto-generated method stub

	}

	public void updateRegistrations() {
		// TODO Auto-generated method stub

	}

	public CompoundWrappedService wrap(ManagedServiceWrapper<?> serviceWrapper) {
		Set<Class<?>> classes = getClasses(serviceReference);

		if (!classes.contains(serviceWrapper.getServiceClass()))
			throw new IllegalArgumentException();

		Hashtable<String, Object> wrappingProperties = getProperties();
		if (serviceWrapper.wrapServiceProperties(wrappingProperties)) {
			Object wrappingService = wrapService(getService(), serviceWrapper,
					classes);

			if (serviceWrapper.getHideServices() == HideServices.WHEN_WRAPPED
					|| serviceWrapper.getHideServices() == HideServices.SILENTLY)
				visible = false;

			CompoundWrappedService compoundWrappedService = new CompoundWrappedService(
					wrappingService, wrappingProperties, classes, this, serviceWrapper);
			wrappedServices.add(compoundWrappedService);
			return compoundWrappedService;
		}

		if (serviceWrapper.getHideServices() == HideServices.ALWAYS)
			visible = false;

		return null;
	}

	@Override
	public boolean isVisible() {
		return visible;
	}

	private <T> Object wrapService(final Object service,
			ManagedServiceWrapper<T> serviceWrapper, Set<Class<?>> classes) {
		@SuppressWarnings("unchecked")
		final T wrappingService = serviceWrapper.wrapService((T) service);
		final Class<T> wrapperClass = serviceWrapper.getServiceClass();

		List<Class<?>> orderedServiceClasses = new ArrayList<>(classes);
		orderedServiceClasses.remove(wrapperClass);
		orderedServiceClasses.add(0, wrapperClass);

		return Proxy.newProxyInstance(null,
				orderedServiceClasses.toArray(new Class<?>[0]),
				new InvocationHandler() {
					@Override
					public Object invoke(Object object, Method method, Object[] args)
							throws Throwable {
						Class<?> declaringClass = method.getDeclaringClass();
						/*
						 * TODO this ^ isn't good enough. Need to be able to change
						 * preference to 'wrapperClass' when more than one interface
						 * contains a method.
						 * 
						 * WILL REPLACE WITH commons-proxy
						 */

						if (declaringClass == Object.class
								|| declaringClass == wrapperClass) {
							return method.invoke(wrappingService, args);
						} else {
							return method.invoke(service, args);
						}
					}
				});
	}

	@Override
	public Set<CompoundWrappedService> getWrappedServices() {
		return wrappedServices;
	}

	private static Hashtable<String, Object> getProperties(
			ServiceReference<?> serviceReference) {
		Hashtable<String, Object> properties = new Hashtable<>();
		for (String propertyKey : serviceReference.getPropertyKeys()) {
			properties.put(propertyKey, serviceReference.getProperty(propertyKey));
		}
		properties.put(ServiceWrapperManagerImpl.class.getName(), true);

		return properties;
	}
}
