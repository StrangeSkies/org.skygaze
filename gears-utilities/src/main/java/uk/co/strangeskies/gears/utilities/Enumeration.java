package uk.co.strangeskies.gears.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Enumeration<S extends Enumeration<S>> implements Self<S> {
	private static class EnumerationType<T extends Enumeration<T>> {
		private final List<T> instances;

		public EnumerationType() {
			instances = new ArrayList<>();
		}

		public List<T> getInstances() {
			return instances;
		}
	}

	private static final Map<Class<?>, EnumerationType<?>> ENUM_TYPES = new HashMap<>();

	private final String name;
	private final int ordinal;

	protected Enumeration(String name) {
		this.name = name;
		ordinal = addInstance(getThis());
	}

	public String name() {
		return name;
	}

	public int ordinal() {
		return ordinal;
	}

	@Override
	public String toString() {
		return name();
	};

	@Override
	public S copy() {
		return getThis();
	}

	@SuppressWarnings("unchecked")
	private static <T extends Enumeration<T>> int addInstance(T instance) {
		List<T> enumerationConstants = getEnumerationType(
				((Class<T>) instance.getClass())).getInstances();

		if (enumerationConstants.stream().anyMatch(
				e -> e.name().equals(instance.name())))
			throw new IllegalArgumentException();

		int ordinal = enumerationConstants.size();
		enumerationConstants.add(instance);

		return ordinal;
	}

	private static <T extends Enumeration<T>> EnumerationType<T> getEnumerationType(
			Class<T> type) {
		@SuppressWarnings("unchecked")
		EnumerationType<T> enumType = (EnumerationType<T>) ENUM_TYPES.get(type);
		if (enumType == null)
			ENUM_TYPES.put(type, new EnumerationType<T>());
		return enumType;
	}

	public static <T extends Enumeration<T>> List<T> getConstants(
			Class<T> enumerationClass) {
		return Collections.unmodifiableList(getEnumerationType(enumerationClass)
				.getInstances());
	}

	public static <T extends Enumeration<T>> T valueOf(Class<T> enumerationClass,
			String name) {
		return getConstants(enumerationClass).stream()
				.filter(e -> e.name().equals(name)).findAny().get();
	}
}
