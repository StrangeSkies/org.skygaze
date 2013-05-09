package uk.co.elionline.gears.scripting.impl;

import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = ConsoleSpitter.class, immediate = true)
public class ConsoleSpitter {
	private ScriptEngineManager scriptEngineManager;

	@Reference
	public void bindScriptEngineManager(ScriptEngineManager manager) {
		this.scriptEngineManager = manager;
	}

	public void unbindScriptEngineManager(ScriptEngineManager manager) {
		scriptEngineManager = null;
	}

	@Activate
	public void activate(ComponentContext context) {
		System.out.println("Dumping scripting engines:");
		for (ScriptEngineFactory factory : scriptEngineManager.getEngineFactories()) {
			System.out.println(factory.getEngineName());
			System.out.println(factory.getEngineVersion());
			System.out.println(factory.getLanguageName());
			System.out.println(factory.getLanguageVersion());
		}
	}
}
