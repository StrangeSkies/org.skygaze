package uk.co.strangeskies.gears.mathematics.graph.impl;

public class EdgeVertices<V> {
	private final V from;

	private final V to;

	public EdgeVertices(V from, V to) {
		this.from = from;
		this.to = to;
	}

	public V getFrom() {
		return from;
	}

	public V getTo() {
		return to;
	}
}