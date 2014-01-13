package uk.co.strangeskies.gears.mathematics.graph;

import java.util.Map;
import java.util.Set;

import uk.co.strangeskies.gears.utilities.Copyable;
import uk.co.strangeskies.gears.utilities.tuples.Pair;

public interface Graph<V, E> extends Copyable<Graph<V, E>> {
	public Set<V> getVertices();

	public Set<E> getEdges();

	public E getEdge(V from, V to);

	public Pair<V, V> getVertices(E edge);

	public default boolean addVertex(V vertex) {
		throw new UnsupportedOperationException();
	}

	public default boolean removeVertex(V vertex) {
		throw new UnsupportedOperationException();
	}

	public default E addEdge(V from, V to) {
		return addEdge(from, to, false);
	}

	public default E addEdge(V from, V to, boolean addMissingVertices) {
		throw new UnsupportedOperationException();
	}

	public default E removeEdge(V from, V to) {
		throw new UnsupportedOperationException();
	}

	public default boolean removeEdge(E edge) {
		Pair<V, V> vertices = getVertices(edge);
		return removeEdge(vertices.getLeft(), vertices.getRight()) != null;
	}

	public Map<Pair<V, V>, E> edges();

	public boolean isDirected();

	public boolean isWeighted();

	public double weight(E edge);

	public double weight(V from, V to);

	public boolean isSimple();

	public GraphTransformer<V, E> transform();
}
