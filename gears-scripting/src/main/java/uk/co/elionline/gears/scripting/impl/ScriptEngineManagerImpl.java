package uk.co.elionline.gears.scripting.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.SimpleBindings;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

import uk.co.elionline.gears.scripting.ScriptEngineManagerService;
import uk.co.elionline.gears.utilities.collections.HashSetMultiMap;
import uk.co.elionline.gears.utilities.collections.SetMultiMap;

@Component(service = ScriptEngineManagerService.class)
public class ScriptEngineManagerImpl extends ScriptEngineManagerService {
	private Bindings globalScope;

	private final Set<ScriptEngineFactory> scriptEngineFactories;

	private final SetMultiMap<String, ScriptEngineFactory> nameAssociations;
	private final SetMultiMap<String, ScriptEngineFactory> extensionAssociations;
	private final SetMultiMap<String, ScriptEngineFactory> mimeTypeAssociations;

	public ScriptEngineManagerImpl() {
		globalScope = new SimpleBindings();

		scriptEngineFactories = new HashSet<>();

		nameAssociations = new HashSetMultiMap<>();
		extensionAssociations = new HashSetMultiMap<>();
		mimeTypeAssociations = new HashSetMultiMap<>();
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
	public void addScriptEngineFactory(ScriptEngineFactory factory) {
		scriptEngineFactories.add(factory);
	}

	public void removeScriptEngineFactory(ScriptEngineFactory factory) {
		scriptEngineFactories.remove(factory);
	}

	@Override
	public Object get(String key) {
		return globalScope.get(key);
	}

	@Override
	public void put(String key, Object value) {
		globalScope.put(key, value);
	}

	@Override
	public Bindings getBindings() {
		return globalScope;
	}

	@Override
	public ScriptEngine getEngineByExtension(String extension) {
		HashSet<ScriptEngineFactory> engineFactoriesByExtension = new HashSet<>();

		for (ScriptEngineFactory factory : nameAssociations.get(extension)) {
			try {
				ScriptEngine engine = factory.getScriptEngine();
				engine.setBindings(getBindings(), ScriptContext.GLOBAL_SCOPE);
				return engine;
			} catch (Exception exp) {
			}
		}

	}

	@Override
	public ScriptEngine getEngineByMimeType(String mimeType) {
	}

	@Override
	public ScriptEngine getEngineByName(String shortName) {
	}

	@Override
	public List<ScriptEngineFactory> getEngineFactories() {
		return new ArrayList<>(scriptEngineFactories);
	}

	@Override
	public void registerEngineExtension(String extension,
			ScriptEngineFactory factory) {
		extensionAssociations.add(extension, factory);
	}

	@Override
	public void registerEngineMimeType(String type, ScriptEngineFactory factory) {
		mimeTypeAssociations.add(type, factory);
	}

	@Override
	public void registerEngineName(String name, ScriptEngineFactory factory) {
		nameAssociations.add(name, factory);
	}

	@Override
	public void setBindings(Bindings bindings) {
		globalScope = bindings;
	}
}
