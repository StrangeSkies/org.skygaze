package uk.co.strangeskies.extengine.entity.assembly;

import java.util.Set;

import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.extengine.entity.scheduling.Scheduler;

/**
 * TODO
 * 
 * 
 * 
 * Remember this important decision: resources like
 * Catalogues/ArrangementsBehaviours(scripted)/Patterns etc. will be defined
 * OUTSIDE of jars at some filesystem location.
 * 
 * This will be good for a number of reasons:
 * 
 * - bnd runs OSGi stuff from built jars, which creates a disconnect between the
 * resources in the IDE editors and the jarred resources as the game sees them,
 * making live-updating of media/scripts/level designs from a running game
 * difficult.
 * 
 * (for the last point - bnd does magically recompile and redeploy bundles
 * transparently... but in reality games have a lot of state which would be
 * destroyed by this. It would cause tons of problems.)
 * 
 * - how would it even look to put them in projects? one monolithic "game data"
 * project? Whatever the case, probably a whole bunch of jars with no actual
 * classes in them.
 * 
 * - how would we advertise which resources are in which jars? OSGi also not
 * ideal / universal.
 * 
 * - awkward to piggy-back 'packages' as a scoping/namespace mechanism, since
 * resources typically have no relation to class/package organisation.
 * 
 * - if resources and scripts are outside of jars, that makes simple modding
 * much easier.
 * 
 * - modularity doesn't make that much sense as an approach to organising game
 * data, so why try to force it? The engine is the engine, the data is the
 * data...
 * 
 * - DATA IS DATA NOT CODE
 * 
 * 
 * 
 * TODO
 * 
 * 
 * 
 * So I guess the "standard extengine" way to do this will be to have a special
 * "game data" eclipse project set aside with all the data in.
 * 
 * 
 * 
 * TODO
 * 
 * @author Elias N Vasylenko
 */
public interface Ensemble {
	String getName();

	Package getNamespace();

	Set<Catalogue> getDependencies();

	Set<Scheduler> getSchedulers();

	Set<BehaviourComponent> getGlobalBehaviours();

	Set<Pattern> getPatterns();
}
