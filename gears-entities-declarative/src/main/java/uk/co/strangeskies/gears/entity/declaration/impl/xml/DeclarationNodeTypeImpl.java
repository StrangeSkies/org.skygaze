package uk.co.strangeskies.gears.entity.declaration.impl.xml;

import java.util.List;

import uk.co.strangeskies.gears.entity.declaration.DeclarationNodeComplexType;
import uk.co.strangeskies.gears.entity.declaration.DeclarationNodeMethodCall;

public class DeclarationNodeTypeImpl<T> implements
		DeclarationNodeComplexType<T> {
	private String name;
	private Class<T> dataClass;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Class<T> getRequiredClass() {
		return dataClass;
	}

	@Override
	public List<DeclarationNodeMethodCall> getMethodCalls() {
		// TODO Auto-generated method stub
		return null;
	}
}
