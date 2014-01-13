package uk.co.strangeskies.gears.mathematics.graph.impl;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

import uk.co.strangeskies.gears.mathematics.graph.Graph;
import uk.co.strangeskies.gears.mathematics.graph.GraphTransformer;
import uk.co.strangeskies.gears.utilities.tuples.Pair;

public class GraphImpl<V, E> implements Graph<V, E> {
	private Map<V, Map<V, E>> adjacencies;
	private Map<E, Pair<V, V>> edges;

	public GraphImpl(boolean unmodifiableVertices, boolean unmodifiableEdges,
			Collection<V> vertices, Collection<Pair<V, V>> edges, boolean directed,
			Function<E, Comparator<V>> lowToHighDirection, boolean directionMutable,
			BiFunction<V, V, E> edgeFactory, Function<E, Double> edgeWeight,
			boolean edgeWeightMutable,
			Function<V, ? extends Collection<V>> incomingEdgeGenerator,
			Function<V, ? extends Collection<V>> outgoingEdgeGenerator,
			Function<V, ? extends Collection<V>> edgeGenerator,
			boolean asymmetricEdgeGeneration,
			BiPredicate<? super V, ? super V> edgeGenerationRule,
			BiPredicate<? super V, ? super V> incomingEdgeGenerationRule,
			BiPredicate<? super V, ? super V> outgoingEdgeGenerationRule,
			boolean generateNeighbours, Predicate<Graph<V, E>> constraint) {
	}

	@Override
	public Graph<V, E> copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<V> getVertices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<E> getEdges() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Pair<V, V>, E> edges() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDirected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isWeighted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double weight(E edge) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double weight(V from, V to) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isSimple() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public GraphTransformer<V, E> transform() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E getEdge(V from, V to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pair<V, V> getVertices(E edge) {
		// TODO Auto-generated method stub
		return null;
	}
}
