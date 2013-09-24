package uk.co.elionline.gears.utilities.osgi;

import java.util.Map;

import org.osgi.framework.Constants;

import uk.co.elionline.gears.utilities.osgi.internal.ServiceWrapperManagerImpl;

/**
 * 
 * @author Elias N Vasylenko
 * 
 * @param <T>
 *          This is the type of the service this wrapper is designed to be
 *          applied to.
 */
public interface ServiceWrapper<T> {
	/**
	 * This method should return an implementation of the service class wrapping
	 * the implementation provided as a parameter. It doesn't matter if a
	 * different object is returned by multiple calls providing an identical
	 * 'service' parameter.
	 * 
	 * Classes published under this service with higher property values for the
	 * key {@link Constants#SERVICE_RANKING} will be applied first. Multiple
	 * service wrappers on the same service class T will stack in the order this
	 * creates. For a class which registers itself under multiple services, this
	 * order will be maintained across each wrapping service. For ServiceWrapper
	 * services with the same ranking, those with the
	 * {@link ServiceWrapper#HIDE_WRAPPED_SERVICE_TYPES} property set to true will
	 * be selected first, otherwise the ordering is arbitrary.
	 * 
	 * Bear in mind that any method calls on the underlying object by the
	 * registering context will not be wrapped, only calls from the service
	 * consumer context will be wrapped. This means, for example, that calls from
	 * the OSGi framework on declarative service objects on deactivation or on
	 * reference binding will be made directly on the underlying service object.
	 * 
	 * @param service
	 *          The service implementation to be wrapped.
	 * @return The wrapping implementation.
	 */
	public T wrapService(/*@ReadOnly*/T service);

	/**
	 * This method will be called before a service wrapped using
	 * {@link ServiceWrapper#wrapService(Object)} is unregistered.
	 * 
	 * @param service
	 */
	public void unwrapService(T service);

	/**
	 * This method will be called directly before the wrapper is applied to a
	 * service through {@link ServiceWrapper#wrapService(Object)}, and then once
	 * again each time any of the properties of the wrapped service change.
	 * 
	 * The map passed as an argument will contain the properties of the service to
	 * be wrapped. Any changes made to this map will be reflected in the
	 * properties of the wrapping service if it succeeds. Typically no changes
	 * will need to be made, though an example of a sensible change would be to
	 * increase the {@link Constants#SERVICE_RANKING} so the wrapping service
	 * takes precedence over the original.
	 * 
	 * Values for the {@link Constants#OBJECTCLASS} and
	 * {@link Constants#SERVICE_ID} keys can not be changed. These values are set
	 * by the Framework when the service is registered in the OSGi environment.
	 * 
	 * {@link ServiceWrapper#wrapService(Object)} will only ever be called to wrap
	 * a service if this method returns 'true' for the properties of that service.
	 * This can act as a filter for the service wrapper, such that it is only
	 * applied when certain conditions are met.
	 * 
	 * @param serviceProperties
	 *          The properties of the service to be wrapped.
	 * @return True if a service can be wrapped with the given properties, false
	 *         otherwise.
	 */
	public boolean wrapServiceProperties(
			Map<String, /*@ReadOnly*/Object> serviceProperties);

	/**
	 * This method should return the target class of services to be wrapped.
	 * 
	 * @return
	 */
	public Class<T> getServiceClass();

	/**
	 * If a property with this key is present on a service wrapper service it
	 * should be of the type {@link HideWrappedServiceTypes}. Be careful when
	 * using the value {@link HideWrappedServiceTypes#Always}, as this will act as
	 * a filter, only registering services for those which are successfully
	 * wrapped. If no services are matched by
	 * {@link ServiceWrapper#wrapServiceProperties(Map)} then none will be
	 * available to any bundles, no matter what service ranking the wrapper has.
	 * The value {@link HideWrappedServiceTypes#WhenWrapped} on the other hand
	 * indicates that services will only be hidden in the case that a valid wrap
	 * will be provided instead, which is often safer.
	 * 
	 * Wrappers which maintain state should normally be careful not set this value
	 * to {@link Boolean#Never}, as they won't be able to guarantee they are not
	 * subverted, with the wrapped service being manipulated without their
	 * knowledge.
	 */
	public static String HIDE_WRAPPED_SERVICE_TYPES = "hide.wrapped.service.types";

	public enum HideWrappedServiceTypes {
		Always, Never, WhenWrapped;
	}

	/**
	 * If a property with this key is present on a {@link ServiceWrapper} service
	 * then the value should be of the type {@link Boolean}. This value then
	 * determines whether a wrapper be applied retroactively to services which
	 * already exist, and therefore may already be in use by other bundles.
	 * 
	 * Wrappers which maintain state should normally not set this value to
	 * {@link Boolean#TRUE}, as they won't generally have any way to determine the
	 * state they should adopt at the point at which they are added, since they
	 * may be added and removed multiple times, through multiple calls to
	 * {@link ServiceWrapper#wrapService(Object)}.
	 * 
	 * If this property is set to {@link Boolean#FALSE} then existing services
	 * will not be wrapped, so the wrapper can be sure that
	 * {@link ServiceWrapper#wrapService(Object)} is only ever called once for any
	 * service, at the point when that services is registered.
	 * 
	 * Implementations of {@link ServiceWrapperManagerImpl} may choose to not
	 * accept or to ignore wrappers with this property set to true, as it may be
	 * necessary to incur a slight overhead over the entire service-framework in
	 * order to support this feature (all services may need to be proxied
	 * preemptively). Managers which are registered as a service themselves should
	 * advertise whether they support this feature with the
	 * {@link ServiceWrapperManager#SUPPORTS_WRAP_EXISTING_SERVICES} property. It
	 * should also be noted that an implementation of
	 * {@link ServiceWrapperManagerImpl} may only be able to wrap services which
	 * were registered after the manager was registered or created itself.
	 */
	public static String WRAP_EXISTING_SERVICES = "wrap.existing.services";
}
