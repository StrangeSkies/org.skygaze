package uk.co.strangeskies.extengine.entity.assembly;

import java.util.Set;

/**
 * 
 * @author Elias N Vasylenko
 * 
 */
public interface Assemblage extends AssemblageView {
	public/* @ReadOnly */CollapsedAssemblageView getCollapsedCompositionView();

	public AssemblageView overrideSubassemblage(
	/* @Mutable Assemblage this, */AssemblageView subassemblageMatch);

	public Set<Assemblage> overrideSubassemblages(
	/* @Mutable Assemblage this, */AssemblageView subassemblageMatch);

	public void revertOverrides(
	/* @Mutable Assemblage this, */AssemblageView subassemblageMatch);

	public void revertOverrides(/* @Mutable Assemblage this */);
}
