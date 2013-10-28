package uk.co.strangeskies.gears.entity.declaration;

import java.util.List;

public interface DeclarationNodeComplexType<T> {
	public String getName();

	public Class<T> getRequiredClass();

	public List<DeclarationNodeMethodCall> getMethodCalls();

	public String getBuildMethod();
}
