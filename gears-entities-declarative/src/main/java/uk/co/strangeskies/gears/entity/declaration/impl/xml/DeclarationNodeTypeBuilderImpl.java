package uk.co.strangeskies.gears.entity.declaration.impl.xml;

import javax.script.ScriptEngineManager;

import uk.co.strangeskies.gears.entity.declaration.DeclarationNodeComplexType;
import uk.co.strangeskies.gears.entity.declaration.DeclarationNodeComplexTypeBuilder;

public class DeclarationNodeTypeBuilderImpl<T> implements
		DeclarationNodeComplexTypeBuilder<T> {
	private ScriptEngineManager scriptEngineManager;

	private Class<T> dataClass;

	@Override
	public DeclarationNodeTypeBuilderImpl<T> name(String name) {
		return this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <U extends T> DeclarationNodeTypeBuilderImpl<U> dataClass(
			Class<U> dataClass) {
		this.dataClass = (Class<T>) dataClass;
		return (DeclarationNodeTypeBuilderImpl<U>) this;
	}

	@Override
	public DeclarationNodeTypeBuilderImpl<T> methodCall(String methodName,
			DeclarationNodeComplexType<?>... parameters) {
		return this;
	}

	@Override
	public DeclarationNodeComplexType<T> create() {
		return new DeclarationNodeTypeImpl<>();
	}
}

class Script {
	private final String language;
	private final String script;

	public Script(String language, String script) {
		this.language = language;
		this.script = script;
	}

	public String getLanguage() {
		return language;
	}

	public String getScript() {
		return script;
	}
}
