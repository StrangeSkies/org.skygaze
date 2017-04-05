package uk.co.strangeskies.extengine.entity.assembly;

import java.util.Set;

/**
 * 
 * @author Elias N Vasylenko
 * 
 */
public interface Pattern extends PatternView {
	/* @ReadOnly */PatternView getCollapsedCompositionView();

	PatternView overrideChild(/* @Mutable Pattern this, */PatternView childMatch);

	Set<Pattern> overrideChildren(/* @Mutable Pattern this, */PatternView childrenMatch);

	void revertOverrides(/* @Mutable Pattern this, */PatternView childrenMatch);

	void revertOverrides(/* @Mutable Pattern this */);
}
