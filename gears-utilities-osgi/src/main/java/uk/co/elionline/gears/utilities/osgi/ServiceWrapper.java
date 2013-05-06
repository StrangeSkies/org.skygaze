package uk.co.elionline.gears.utilities.osgi;

import java.util.Map;

import org.osgi.framework.Constants;

public interface ServiceWrapper<T> {
	/**
	 * This method should return an implementation of the service class wrapping
	 * the implementation provided as a parameter. It doesn't matter if a
	 * different service is returned by multiple calls providing an identical
	 * parameter.
	 * 
	 * Classes published under this service with higher property values for the
	 * key {@link Constants#SERVICE_RANKING} will be applied first. Multiple
	 * service wrappers on the same service class T will stack in the order this
	 * creates. For ServiceWrapper services with the same ranking, those with the
	 * {@link ServiceWrapper#HIDE_WRAPPED_SERVICES} property set to true will be
	 * selected first, otherwise the ordering is arbitrary.
	 * 
	 * @param service
	 *          The service implementation to be wrapped.
	 * @param serviceProperties
	 *          The properties of the service to be wrapped. Behaviour is
	 *          undefined for attempts to modify this Map.
	 * @returnThe wrapping implementation, or 'null' if the service can't be
	 *            wrapped.
	 */
	public T wrapService(T service, Map<String, Object> serviceProperties);

	/**
	 * This method will be called when the wrapper is applied to a service, and
	 * then once again each time any of the properties of the wrapped service
	 * change. The map passed as an argument will initially be a copy of the
	 * wrapped service's properties. The Map supports all optional operations and
	 * will be used as properties of the wrapped service after the method returns.
	 * 
	 * The {@link Constants#OBJECTCLASS} and {@link Constants#SERVICE_ID} keys
	 * cannot be modified by this method. These values are set by the Framework
	 * when the service is registered in the OSGi environment.
	 * 
	 * Commonly this method might be used, for example, to increment the
	 * {@link Constants#SERVICE_RANKING} of the wrapped service.
	 * 
	 * @param serviceProperties
	 */
	public void modifyWrappedServiceProperties(
			Map<String, Object> serviceProperties);

	/**
	 * This method should return the class provided by the wrapped service.
	 * 
	 * @return
	 */
	public Class<T> getServiceClass();

	/**
	 * If a property with this key is present on a service wrapper service, and if
	 * the value is set to true, the wrapper will be applied to all existing
	 * wrappable services, not just ones registered after this wrapper service.
	 * The default value is assumed to be true if this key is not present.
	 */
	public static String APPLY_RETROACTIVELY = "Apply-Retroactively";

	/**
	 * If a property with this key is present on a service wrapper service, and if
	 * the value is set to true, wrapped services will be hidden on application of
	 * the wrapper. Services already in use will not be hidden from those bundles
	 * which are using them. The default value is assumed to be false if this key
	 * is not present.
	 */
	public static String HIDE_WRAPPED_SERVICES = "Hide-Wrapped-Services";
}
