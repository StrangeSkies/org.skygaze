package uk.co.strangeskies.extengine.entity.assembly.impl;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import uk.co.strangeskies.extengine.entity.Entity;
import uk.co.strangeskies.extengine.entity.EntityComponentSystem;
import uk.co.strangeskies.extengine.entity.assembly.Pattern;
import uk.co.strangeskies.extengine.entity.assembly.PatternView;
import uk.co.strangeskies.extengine.entity.assembly.StateInitialiser;
import uk.co.strangeskies.extengine.entity.assembly.Variable;
import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.extengine.entity.state.StateComponent;
import uk.co.strangeskies.utilities.collection.FilteredSetDecorator;
import uk.co.strangeskies.utilities.collection.MultiHashMap;
import uk.co.strangeskies.utilities.collection.MultiMap;

public class PatternImpl implements Pattern {
	private final String name;

	private final Set<Pattern> composition;

	private final Set<Pattern> children;
	private final Map<Pattern, Pattern> overriddenChildren;

	private final Set<BehaviourComponent> behaviourComponents;

	private final Set<StateComponent<?, ?>> stateComponents;
	private final MultiMap<StateComponent<?, ?>, ? extends StateInitialiser<?>, ? extends List<?>> statePreparators;

	private final Set<Variable<?>> variables;

	public PatternImpl(String name) {
		this.name = name;

		composition = new FilteredSetDecorator<>(pattern -> {
			if (pattern.getCollapsedCompositionView().getComposition().contains(PatternImpl.this)) {
				throw new IllegalArgumentException("Composition pattern graph cycle detected");
			}
			return true;
		});

		children = new FilteredSetDecorator<>(pattern -> {
			Queue<Pattern> children = new ArrayDeque<>();
			children.add(pattern);

			while (!children.isEmpty()) {
				Pattern child = children.poll();

				if (child.equals(PatternImpl.this)) {
					throw new IllegalArgumentException("Child graph cycle detected");
				}

				children.addAll(child.getCollapsedCompositionView().getChildren());
			}

			return true;
		});
		overriddenChildren = new HashMap<>();

		behaviourComponents = new HashSet<>();

		stateComponents = new HashSet<>();
		statePreparators = new MultiHashMap<>(ArrayList::new);

		variables = new HashSet<>();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Set<Pattern> getChildren() {
		return children;
	}

	@Override
	public Set<Variable<?>> getVariables() {
		return variables;
	}

	@Override
	public Set<BehaviourComponent> getBehaviours() {
		return behaviourComponents;
	}

	@Override
	public Set<StateComponent<?, ?>> getStates() {
		return stateComponents;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <D> Set<StateInitialiser<D>> getInitialisers(final StateComponent<D, ?> state) {
		return (Set<StateInitialiser<D>>) statePreparators.getCollection(state);
	}

	@Override
	public Set<Pattern> getComposition() {
		return composition;
	}

	@Override
	public PatternView overrideChild(PatternView childMatch) {
		Pattern overriddenPattern = getChild(childMatch);
		Pattern overridingPattern = new PatternImpl(overriddenPattern.getName());
		overridingPattern.getComposition().add(overriddenPattern);

		overriddenChildren.put(overriddenPattern, overridingPattern);

		return overridingPattern;
	}

	@Override
	public Set<Pattern> overrideChildren(PatternView childMatch) {
		Set<Pattern> overridingPatterns = new HashSet<>();

		Set<Pattern> children = getChildren(childMatch);
		for (Pattern overriddenPattern : children) {
			Pattern overridingPattern = new PatternImpl(overriddenPattern.getName());
			overridingPattern.getComposition().add(overriddenPattern);

			overriddenChildren.put(overriddenPattern, overridingPattern);
		}

		return overridingPatterns;
	}

	@Override
	public void revertOverrides(PatternView childMatch) {
		overriddenChildren.keySet().removeAll(getChildren(childMatch));
	}

	@Override
	public Pattern getChild(PatternView childMatch) {
		Set<Pattern> children = getChildren(childMatch);
		if (children.size() != 1)
			throw new IllegalArgumentException();

		return children.iterator().next();
	}

	@Override
	public Set<Pattern> getChildren(PatternView childMatch) {
		Set<Pattern> children = new HashSet<>();
		for (Pattern child : this.children)
			if (isComposedFrom(child, childMatch))
				children.add(child);

		return children;
	}

	public boolean isComposedFrom(PatternView base) {
		return isComposedFrom(this, base);
	}

	public static boolean isComposedFrom(PatternView pattern, PatternView base) {
		if (pattern == base) {
			return true;
		}

		Queue<Pattern> compositionPatterns = new ArrayDeque<>();
		compositionPatterns.addAll(pattern.getComposition());

		while (!compositionPatterns.isEmpty()) {
			Pattern compositionPattern = compositionPatterns.poll();
			if (base == compositionPattern) {
				return true;
			}
			compositionPatterns.addAll(compositionPattern.getComposition());
		}

		return false;
	}

	@Override
	public void revertOverrides() {
		overriddenChildren.clear();
	}

	@Override
	public PatternView getCollapsedCompositionView() {
		return new CollapsedCompositionPatternView(this);
	}

	@Override
	public Entity assemble(EntityComponentSystem entityManager) {
		return new AssemblyContextImpl(this, entityManager).assemble();
	}
}
