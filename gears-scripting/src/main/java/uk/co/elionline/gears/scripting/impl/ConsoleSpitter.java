package uk.co.elionline.gears.scripting.impl;

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

			System.out.println("Testing javascript:");
			engine = scriptEngineManager.getEngineByName("javascript");
			try {
				System.out.println("  "
						+ engine.eval("var date=new Date(); date.getMonth() + 1;"));
			} catch (ScriptException e) {
				e.printStackTrace();
			}

			System.out.println("Testing clojure:");
			engine = scriptEngineManager.getEngineByName("clojure");
			try {
				System.out
						.println("  "
								+ engine
										.eval("import re, collections"
												+ "def words(text): return re.findall('[a-z]+', text.lower())"
												+ "def train(features):"
												+ "model = collections.defaultdict(lambda: 1)"
												+ "for f in features:"
												+ "model[f] += 1"
												+ "return model"
												+ "NWORDS = train(words(file('big.txt').read()))"
												+ "alphabet = 'abcdefghijklmnopqrstuvwxyz'"
												+ "def edits1(word):"
												+ "   splits     = [(word[:i], word[i:]) for i in range(len(word) + 1)]"
												+ "   deletes    = [a + b[1:] for a, b in splits if b]"
												+ "   transposes = [a + b[1] + b[0] + b[2:] for a, b in splits if len(b)>1]"
												+ "   replaces   = [a + c + b[1:] for a, b in splits for c in alphabet if b]"
												+ "   inserts    = [a + c + b     for a, b in splits for c in alphabet]"
												+ "   return set(deletes + transposes + replaces + inserts)"
												+ "def known_edits2(word):"
												+ "   return set(e2 for e1 in edits1(word) for e2 in edits1(e1) if e2 in NWORDS)"
												+ "def known(words): return set(w for w in words if w in NWORDS)"
												+ "def correct(word):"
												+ "    candidates = known([word]) or known(edits1(word)) or known_edits2(word) or [word]"
												+ "    return max(candidates, key=NWORDS.get) correct('speling')"));
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
