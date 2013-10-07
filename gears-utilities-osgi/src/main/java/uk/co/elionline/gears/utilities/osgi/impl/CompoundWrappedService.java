package uk.co.elionline.gears.utilities.osgi.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import uk.co.elionline.gears.utilities.osgi.ServiceWrapper.HideServices;

class CompoundWrappedService {
	private final Object service;
	private final Hashtable<String, Object> properties;
	private final Set<Class<?>> classes;

	private final CompoundWrappedService parent;

	private final ManagedServiceWrapper<?> wrapper;

	private boolean visible;

	public CompoundWrappedService(Object service, Map<String, Object> properties,
			Set<Class<?>> classes) {
		this(service, properties, classes, null, null);
	}

	private CompoundWrappedService(Object service,
			Map<String, Object> properties, Set<Class<?>> classes,
			CompoundWrappedService wrappedService,
			ManagedServiceWrapper<?> serviceWrapper) {
		this.service = service;
		this.properties = new Hashtable<>(properties);
		this.properties.put(ServiceWrapperManagerImpl.class.getName(), true);
		this.classes = classes;

		parent = wrappedService;
		wrapper = serviceWrapper;

		visible = true;
	}

	public CompoundWrappedService getParent() {
		return parent;
	}

	public ManagedServiceWrapper<?> getWrapper() {
		return wrapper;
	}

	public Object getService() {
		return service;
	}

	public Hashtable<String, Object> getProperties() {
		return properties;
	}

	public CompoundWrappedService wrap(ManagedServiceWrapper<?> serviceWrapper) {
		if (!classes.contains(serviceWrapper.getServiceClass()))
			throw new IllegalArgumentException();

		Hashtable<String, Object> wrappingProperties = getProperties();
		if (serviceWrapper.wrapServiceProperties(wrappingProperties)) {
			Object wrappingService = wrapService(getService(), serviceWrapper);

			if (serviceWrapper.getHideServices() == HideServices.WHEN_WRAPPED
					|| serviceWrapper.getHideServices() == HideServices.SILENTLY)
				visible = false;

			return new CompoundWrappedService(wrappingService, wrappingProperties,
					classes, this, serviceWrapper);
		}

		if (serviceWrapper.getHideServices() == HideServices.ALWAYS)
			visible = false;

		return null;
	}

	public boolean isVisible() {
		return visible;
	}

	private <T> Object wrapService(final Object service,
			ManagedServiceWrapper<T> serviceWrapper) {
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
}
