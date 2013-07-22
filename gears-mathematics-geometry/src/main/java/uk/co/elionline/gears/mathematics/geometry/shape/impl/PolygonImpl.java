package uk.co.elionline.gears.mathematics.geometry.shape.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uk.co.elionline.gears.mathematics.geometry.Bounds2;
import uk.co.elionline.gears.mathematics.geometry.matrix.Vector2;
import uk.co.elionline.gears.mathematics.geometry.shape.ComplexPolygon;
import uk.co.elionline.gears.mathematics.geometry.shape.Polygon;
import uk.co.elionline.gears.mathematics.geometry.shape.Shape;
import uk.co.elionline.gears.mathematics.geometry.shape.Triangle;
import uk.co.elionline.gears.mathematics.values.Value;

public abstract class PolygonImpl<S extends Polygon<S, V>, V extends Value<V>>
		implements Polygon<S, V> {
	@Override
	public Value<?> getArea() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Value<?> getPerimeter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bounds2<?> getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public final S getThis() {
		return (S) this;
	}

	@Override
	public Set<List</*@Immutable*/Integer>> getTessellationIndices(int limit,
			TessellationScheme tessellation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Polygon<?, V>> getTessellationShapes(int limit,
			TessellationScheme tessellation) {
		// output list
		Set<List</*@Immutable*/Integer>> outputPolygonSet = new HashSet<>();

		List</*@Immutable*/Integer> remainingIndices = indices;

		int earIndex;
		int nextIndex;
		int lastValidEarIndex;

		earIndex = lastValidEarIndex = 0;
		nextIndex = earIndex + 1;

		Polygon<?, V> outputPolygon = new ArrayList<>();
		while (remainingIndices.size() > 2) {
			if (isEar(remainingIndices, earIndex)) {
				outputPolygon.add(earIndex);
				do {
					outputPolygon.add(nextIndex);
					remainingIndices.remove(nextIndex);
					earIndex = nextIndex - 1;
					if (nextIndex == remainingIndices.size())
						nextIndex = 0;
				} while (remainingIndices.size() > 2
						&& isEar(remainingIndices, earIndex)
						&& (outputPolygon.size() < limit + 1 || limit == 0));
				outputPolygon.add(nextIndex);
				outputPolygonSet.add(outputPolygon);
				outputPolygon = new ArrayList<>();

				lastValidEarIndex = earIndex;
			}

			earIndex++;
			if (earIndex == remainingIndices.size())
				earIndex = 0;

			if (earIndex == lastValidEarIndex) {
				System.out.println("failed to tesselate @ " + lastValidEarIndex
						+ " with " + remainingIndices.size() + " vertices remaining");
				for (Integer index : remainingIndices) {
					System.out.println(vertices.get(index));
				}

				return outputPolygonSet;
			}
			nextIndex = earIndex + 1;
			if (nextIndex == remainingIndices.size())
				nextIndex = 0;
		}

		return outputPolygonSet;
	}

	public boolean isEar(
	/*@ReadOnly*/List</*@Immutable*/Integer> remainingIndices, int earIndex) /*@ReadOnly*/{
		int indicesLeft = remainingIndices.size();

		// if not even triangle
		if (indicesLeft < 3)
			return false;

		// ear triangle
		/*@ReadOnly*/Vector2<V> a = vertices.get(remainingIndices.get(earIndex));
		if (++earIndex == indicesLeft)
			earIndex = 0;
		/*@ReadOnly*/Vector2<V> b = vertices.get(remainingIndices.get(earIndex));
		if (++earIndex == indicesLeft)
			earIndex = 0;
		/*@ReadOnly*/Vector2<V> c = vertices.get(remainingIndices.get(earIndex));

		Triangle<V> triangle = new TriangleImpl<V>(a, b, c);

		// if ear is concave then reject as invalid
		if (!triangle.isClockwise())
			return false;

		// if ear Integerersects with any other geometry it is invalid
		Vector2<V> previousVertex = vertices.get(vertices.size() - 1);
		for (Vector2<V> vertex : vertices) {
			// if a vertex is inside the ear
			if (triangle.contains(vertex))
				return false;

			// if a line crosses through the ear it is invalid
			if (triangle.intersects(new LineImpl<V>(previousVertex, vertex)))
				return false;

			/* Check if any point(s) 'n' overlap b, then for each check if the angle
			 * swept about b through the solid portion (by winding) between a and c
			 * Integerersects the angle swept about n through the solid portion between
			 * its own neighbouring points. If intersection found then fail.
			 *
			 * It's worth noting that In this case a valid triangulation may still
			 * fail, but it doesn't matter as the polygon will be reduced elsewhere
			 * until an ear at b is made passable. Also the alternative, to more
			 * thoroughly check validity here, is way too overcomplicated...
			 */
			// TO DO (very rare case, only for *very* redundant empty geometry)

			previousVertex = vertex;
		}

		return true;
	}

	@Override
	public boolean contains(Vector2<?> other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touches(Vector2<?> other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean intersects(Shape<?> other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touches(Shape<?> other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ComplexPolygon<V> and(Polygon<?, V> value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ComplexPolygon<V> getAnd(Polygon<?, V> value) {
		return new ComplexPolygonImpl<V>(getVertices()).and(value);
	}

	@Override
	public ComplexPolygon<V> nand(Polygon<?, V> value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ComplexPolygon<V> getNand(Polygon<?, V> value) {
		return new ComplexPolygonImpl<V>(getVertices()).nand(value);
	}

	@Override
	public ComplexPolygon<V> nor(Polygon<?, V> value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ComplexPolygon<V> getNor(Polygon<?, V> value) {
		return new ComplexPolygonImpl<V>(getVertices()).nor(value);
	}

	@Override
	public ComplexPolygon<V> or(Polygon<?, V> value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ComplexPolygon<V> getOr(Polygon<?, V> value) {
		return new ComplexPolygonImpl<V>(getVertices()).or(value);
	}

	@Override
	public ComplexPolygon<V> xnor(Polygon<?, V> value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ComplexPolygon<V> getXnor(Polygon<?, V> value) {
		return new ComplexPolygonImpl<V>(getVertices()).xnor(value);
	}

	@Override
	public ComplexPolygon<V> xor(Polygon<?, V> value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ComplexPolygon<V> getXor(Polygon<?, V> value) {
		return new ComplexPolygonImpl<V>(getVertices()).xor(value);
	}
}
