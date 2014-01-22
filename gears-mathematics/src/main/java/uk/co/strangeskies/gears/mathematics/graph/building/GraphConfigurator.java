package uk.co.strangeskies.gears.mathematics.graph.building;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

import uk.co.strangeskies.gears.mathematics.expressions.Expression;
import uk.co.strangeskies.gears.mathematics.graph.Graph;
import uk.co.strangeskies.gears.mathematics.graph.impl.EdgeVertices;
import uk.co.strangeskies.gears.utilities.factory.Factory;

public interface GraphConfigurator<V, E> extends Factory<Graph<V, E>> {
	public GraphConfigurator<V, E> unmodifiableStructure();

	public GraphConfigurator<V, E> unmodifiableVertices();

	public GraphConfigurator<V, E> unmodifiableEdges();

	public <W extends V> GraphConfigurator<W, E> vertices(Collection<W> vertices);

	public default <W extends V> GraphConfigurator<W, E> vertices(
			@SuppressWarnings("unchecked") W... vertices) {
		return vertices(Arrays.asList(vertices));
	}

	public <W extends Expression<? extends V>> GraphConfigurator<W, E> verticesAsExpressions(
			Collection<W> vertices);

	public default <W extends Expression<? extends V>> GraphConfigurator<W, E> verticesAsExpressions(
			@SuppressWarnings("unchecked") W... vertices) {
		return verticesAsExpressions(Arrays.asList(vertices));
	}

	public GraphConfigurator<V, E> edges(Collection<EdgeVertices<V>> edges);

	public default GraphConfigurator<V, E> edges(
			@SuppressWarnings("unchecked") EdgeVertices<V>... edges) {
		return edges(Arrays.asList(edges));
	}

	public GraphConfigurator<V, E> edgeRule(
			Function<V, Collection<? super V>> betweenNeighbours);

	public GraphConfigurator<V, E> edgeRule(
			Function<V, Collection<? super V>> fromNeighbours,
			Function<V, Collection<? super V>> toNeighbours);

	public GraphConfigurator<V, E> edgeRule(BiPredicate<V, V> betweenNeighbours);

	public GraphConfigurator<V, E> edgeRule(BiPredicate<V, V> fromNeighbours,
			BiPredicate<V, V> toNeighbours);

	public GraphConfigurator<V, E> edgeRuleAsymmetric();

	public GraphConfigurator<V, E> directed();

	public GraphConfigurator<V, E> acyclic();

	public GraphConfigurator<V, E> multigraph();

	public default GraphConfigurator<V, E> direction(Comparator<V> lowToHigh) {
		return direction((e) -> lowToHigh);
	}

	public GraphConfigurator<V, E> direction(Function<E, Comparator<V>> lowToHigh);

	public <F extends E> GraphConfigurator<V, F> edgeFactory(
			BiFunction<V, V, F> factory);

	public <F extends E> GraphConfigurator<V, F> edgeMultiFactory(
			BiFunction<V, V, Set<F>> factory);

	public default <F extends E> GraphConfigurator<V, F> edgeFactory(
			Factory<F> factory) {
		return edgeFactory((v, w) -> factory.create());
	}

	public GraphConfigurator<V, E> edgeWeight(Function<E, Double> weight,
			boolean mutable);

	public GraphConfigurator<V, E> generateNeighbours();

	public GraphConfigurator<V, E> constraint(Predicate<Graph<V, E>> constraint);
}