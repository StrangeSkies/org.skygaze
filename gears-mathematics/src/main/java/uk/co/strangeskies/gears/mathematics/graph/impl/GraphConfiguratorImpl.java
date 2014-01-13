package uk.co.strangeskies.gears.mathematics.graph.impl;

import java.util.Collection;
import java.util.Comparator;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

import uk.co.strangeskies.gears.mathematics.expressions.Expression;
import uk.co.strangeskies.gears.mathematics.graph.Graph;
import uk.co.strangeskies.gears.mathematics.graph.build.GraphConfigurator;
import uk.co.strangeskies.gears.utilities.functions.Functions;
import uk.co.strangeskies.gears.utilities.tuples.Pair;

public class GraphConfiguratorImpl<V, E> implements GraphConfigurator<V, E> {
	private boolean unmodifiableVertices;
	private boolean unmodifiableEdges;
	private Collection<V> vertices;
	private Collection<Pair<V, V>> edges;
	private boolean directed;
	private boolean acyclic;
	private boolean multigraph;
	private Function<E, Comparator<V>> lowToHighDirection;
	private BiFunction<V, V, E> edgeFactory;
	private BiFunction<V, V, Set<E>> edgeMultiFactory;
	private Function<E, Double> edgeWeight;
	private boolean edgeWeightMutable;
	private Function<V, Collection<? super V>> incomingEdgeGenerator;
	private Function<V, Collection<? super V>> outgoingEdgeGenerator;
	private Function<V, Collection<? super V>> edgeGenerator;
	private boolean asymmetricEdgeGeneration;
	private BiPredicate<V, V> edgeRule;
	private BiPredicate<V, V> incomingEdgeRule;
	private BiPredicate<V, V> outgoingEdgeRule;
	private boolean generateNeighbours;
	private Predicate<Graph<V, E>> constraint;

	public static GraphConfigurator<Object, Object> configure() {
		return new GraphConfiguratorImpl<>().edgeFactory(() -> new Object());
	}

	@Override
	public Graph<V, E> create() {
		if (multigraph)
			return null;
		else
			return null;
	}

	@Override
	public GraphConfigurator<V, E> unmodifiableStructure() {
		return unmodifiableVertices().unmodifiableEdges();
	}

	@Override
	public GraphConfigurator<V, E> unmodifiableVertices() {
		unmodifiableVertices = true;
		return this;
	}

	@Override
	public GraphConfigurator<V, E> unmodifiableEdges() {
		unmodifiableEdges = true;
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <W extends V> GraphConfigurator<W, E> vertices(Collection<W> vertices) {
		this.vertices = (Collection<V>) vertices;
		return (GraphConfigurator<W, E>) this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <W extends Expression<? extends V>> GraphConfigurator<W, E> verticesAsExpressions(
			Collection<W> vertices) {
		this.vertices = (Collection<V>) vertices;

		edgeFactory = expressionForward(edgeFactory);
		edgeMultiFactory = expressionForward(edgeMultiFactory);

		incomingEdgeGenerator = expressionForward(incomingEdgeGenerator);
		outgoingEdgeGenerator = expressionForward(outgoingEdgeGenerator);
		edgeGenerator = expressionForward(edgeGenerator);

		return (GraphConfigurator<W, E>) this;
	}

	@SuppressWarnings("unchecked")
	private <W, T> BiFunction<W, W, T> expressionForward(
			BiFunction<W, W, T> function) {
		return function == null ? null : (BiFunction<W, W, T>) Functions
				.<Expression<? extends W>, W, T> compose(v -> v.getValue(), function);
	}

	@SuppressWarnings("unchecked")
	private <W, T> Function<W, T> expressionForward(Function<W, T> function) {
		return function == null ? null : (Function<W, T>) function
				.<Expression<? extends W>> compose(v -> v.getValue());
	}

	@Override
	public GraphConfigurator<V, E> edges(Collection<Pair<V, V>> edges) {
		this.edges = edges;
		return this;
	}

	@Override
	public GraphConfigurator<V, E> edgeRule(
			Function<V, Collection<? super V>> neighbours) {
		edgeGenerator = neighbours;
		return unmodifiableEdges();
	}

	@Override
	public GraphConfigurator<V, E> edgeRule(
			Function<V, Collection<? super V>> fromNeighbours,
			Function<V, Collection<? super V>> toNeighbours) {
		incomingEdgeGenerator = fromNeighbours;
		outgoingEdgeGenerator = toNeighbours;
		return unmodifiableEdges().directed();
	}

	@Override
	public GraphConfigurator<V, E> edgeRule(BiPredicate<V, V> neighbours) {
		edgeRule = neighbours;
		return unmodifiableEdges();
	}

	@Override
	public GraphConfigurator<V, E> edgeRule(BiPredicate<V, V> fromNeighbours,
			BiPredicate<V, V> toNeighbours) {
		incomingEdgeRule = fromNeighbours;
		outgoingEdgeRule = toNeighbours;
		return unmodifiableEdges().directed();
	}

	@Override
	public GraphConfigurator<V, E> edgeRuleAsymmetric() {
		asymmetricEdgeGeneration = true;
		return this;
	}

	@Override
	public GraphConfigurator<V, E> directed() {
		directed = true;
		if (lowToHighDirection == null)
			direction((v, w) -> 0);
		return this;
	}

	@Override
	public GraphConfigurator<V, E> acyclic() {
		acyclic = true;
		return this;
	}

	@Override
	public GraphConfigurator<V, E> multigraph() {
		multigraph = true;
		return this;
	}

	@Override
	public GraphConfigurator<V, E> direction(Function<E, Comparator<V>> lowToHigh) {
		lowToHighDirection = lowToHigh;
		return directed();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <F extends E> GraphConfigurator<V, F> edgeFactory(
			BiFunction<V, V, F> factory) {
		edgeMultiFactory = null;
		edgeFactory = (BiFunction<V, V, E>) factory;
		return (GraphConfigurator<V, F>) this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <F extends E> GraphConfigurator<V, F> edgeMultiFactory(
			BiFunction<V, V, Set<F>> factory) {
		edgeFactory = null;
		edgeMultiFactory = (BiFunction<V, V, Set<E>>) (Object) factory;
		return (GraphConfigurator<V, F>) multigraph();
	}

	@Override
	public GraphConfigurator<V, E> edgeWeight(Function<E, Double> weight,
			boolean mutable) {
		edgeWeight = weight;
		edgeWeightMutable = mutable;
		return this;
	}

	@Override
	public GraphConfigurator<V, E> generateNeighbours() {
		generateNeighbours = true;
		return this;
	}

	@Override
	public GraphConfigurator<V, E> constraint(Predicate<Graph<V, E>> constraint) {
		this.constraint = constraint;
		return this;
	}
}
