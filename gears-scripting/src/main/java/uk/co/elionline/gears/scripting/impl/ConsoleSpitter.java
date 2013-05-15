package uk.co.elionline.gears.scripting.impl;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

@Component(service = ConsoleSpitter.class, immediate = true)
public class ConsoleSpitter {
	private ScriptEngineManager scriptEngineManager;
	private boolean dumping;

	@Reference
	public void bindScriptEngineManager(ScriptEngineManager manager) {
		this.scriptEngineManager = manager;
	}

	public void unbindScriptEngineManager(ScriptEngineManager manager) {
		scriptEngineManager = null;
	}

	public void dump() {
		while (dumping) {
			System.out.println("Dumping scripting engines:");
			for (ScriptEngineFactory factory : scriptEngineManager
					.getEngineFactories()) {
				System.out.println("  #  " + factory.getScriptEngine());
			}

			ScriptEngine engine;

			System.out.println("Testing Javascript:");
			engine = scriptEngineManager.getEngineByName("javascript");
			try {
				System.out.println("  month = "
						+ engine.eval("var date=new Date(); date.getMonth() + 1;"));
			} catch (ScriptException e) {
				e.printStackTrace();
			}

			System.out.println("Testing Lua:");
			engine = scriptEngineManager.getEngineByName("lua");
			try {
				engine.put("x", 25);
				engine.eval("y = math.sqrt(x)");
				System.out.println("  sqrt(25) = " + engine.get("y"));
			} catch (ScriptException e) {
				e.printStackTrace();
			}

			System.out.println("Testing Groovy:");
			engine = scriptEngineManager.getEngineByName("groovy");
			try {
				System.out
						.println("  sum of 1 to 10 = " + engine.eval("(1..10).sum()"));
			} catch (ScriptException e) {
				e.printStackTrace();
			}

			System.out.println("Testing Python:");
			engine = scriptEngineManager.getEngineByName("python");
			try {
				engine.eval("import sys");
				engine.eval("print sys");
				engine.put("a", 42);
				engine.eval("print a");
				engine.eval("x = 2 + 2");
				Object x = engine.get("x");
				System.out.println("x: " + x);
			} catch (ScriptException e) {
				e.printStackTrace();
			}

			System.out.println("Testing Ruby:");
			engine = scriptEngineManager.getEngineByName("ruby");
			try {
				ScriptContext context = engine.getContext();
				context.setAttribute("label", new Integer(4),
						ScriptContext.ENGINE_SCOPE);
				engine.eval("puts 2 + $label", context);
			} catch (ScriptException e) {
				e.printStackTrace();
			}

			try {
				Thread.sleep(2500);
			} catch (InterruptedException e) {
			}
		}
	}

	@Activate
	public synchronized void activate(ComponentContext context) {
		dumping = true;
		new Thread() {
			@Override
			public void run() {
				dump();
			}
		}.start();
	}

	@Deactivate
	public synchronized void deactivate(ComponentContext context) {
		dumping = false;
	}
}
