package uk.co.strangeskies.extengine.entity.assembly;

import java.util.Set;

/**
 * 
 * @author Elias N Vasylenko
 * 
 */
public interface Assemblage extends AssemblageView {
	/* @ReadOnly */CollapsedAssemblageView getCollapsedCompositionView();

	AssemblageView overrideSubassemblage(/* @Mutable Assemblage this, */AssemblageView subassemblageMatch);

	Set<Assemblage> overrideSubassemblages(/* @Mutable Assemblage this, */AssemblageView subassemblageMatch);

	void revertOverrides(/* @Mutable Assemblage this, */AssemblageView subassemblageMatch);

	void revertOverrides(/* @Mutable Assemblage this */);
}
