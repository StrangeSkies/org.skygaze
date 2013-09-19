package uk.co.elionline.gears.mathematics.geometry.shape.tessellation;

import uk.co.elionline.gears.mathematics.values.Value;

public class Tessellator<V extends Value<V>> {
	//
	// @Override
	// public Set<List</*@Immutable*/Integer>> getTessellationIndices(int limit,
	// TriangleMeshingScheme tessellation) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public Set<Polygon<?, V>> getTessellationShapes(int limit,
	// TriangleMeshingScheme tessellation) {
	// // output list
	// Set<List</*@Immutable*/Integer>> outputPolygonSet = new HashSet<>();
	//
	// List</*@Immutable*/Integer> remainingIndices = indices;
	//
	// int earIndex;
	// int nextIndex;
	// int lastValidEarIndex;
	//
	// earIndex = lastValidEarIndex = 0;
	// nextIndex = earIndex + 1;
	//
	// Polygon<?, V> outputPolygon = new ArrayList<>();
	// while (remainingIndices.size() > 2) {
	// if (isEar(remainingIndices, earIndex)) {
	// outputPolygon.add(earIndex);
	// do {
	// outputPolygon.add(nextIndex);
	// remainingIndices.remove(nextIndex);
	// earIndex = nextIndex - 1;
	// if (nextIndex == remainingIndices.size())
	// nextIndex = 0;
	// } while (remainingIndices.size() > 2
	// && isEar(remainingIndices, earIndex)
	// && (outputPolygon.size() < limit + 1 || limit == 0));
	// outputPolygon.add(nextIndex);
	// outputPolygonSet.add(outputPolygon);
	// outputPolygon = new ArrayList<>();
	//
	// lastValidEarIndex = earIndex;
	// }
	//
	// earIndex++;
	// if (earIndex == remainingIndices.size())
	// earIndex = 0;
	//
	// if (earIndex == lastValidEarIndex) {
	// System.out.println("failed to tesselate @ " + lastValidEarIndex
	// + " with " + remainingIndices.size() + " vertices remaining");
	// for (Integer index : remainingIndices) {
	// System.out.println(vertices.get(index));
	// }
	//
	// return outputPolygonSet;
	// }
	// nextIndex = earIndex + 1;
	// if (nextIndex == remainingIndices.size())
	// nextIndex = 0;
	// }
	//
	// return outputPolygonSet;
	// }
	//
	// public boolean isEar(
	// /*@ReadOnly*/List</*@Immutable*/Integer> remainingIndices, int earIndex)
	// /*@ReadOnly*/{
	// int indicesLeft = remainingIndices.size();
	//
	// // if not even triangle
	// if (indicesLeft < 3)
	// return false;
	//
	// // ear triangle
	// /*@ReadOnly*/Vector2<V> a = vertices.get(remainingIndices.get(earIndex));
	// if (++earIndex == indicesLeft)
	// earIndex = 0;
	// /*@ReadOnly*/Vector2<V> b = vertices.get(remainingIndices.get(earIndex));
	// if (++earIndex == indicesLeft)
	// earIndex = 0;
	// /*@ReadOnly*/Vector2<V> c = vertices.get(remainingIndices.get(earIndex));
	//
	// Triangle<V> triangle = new TriangleImpl<V>(a, b, c);
	//
	// // if ear is concave then reject as invalid
	// if (!triangle.isClockwise())
	// return false;
	//
	// // if ear Integerersects with any other geometry it is invalid
	// Vector2<V> previousVertex = vertices.get(vertices.size() - 1);
	// for (Vector2<V> vertex : vertices) {
	// // if a vertex is inside the ear
	// if (triangle.contains(vertex))
	// return false;
	//
	// // if a line crosses through the ear it is invalid
	// if (triangle.intersects(new Line2Impl<V>(previousVertex, vertex)))
	// return false;
	//
	// /* Check if any point(s) 'n' overlap b, then for each check if the angle
	// * swept about b through the solid portion (by winding) between a and c
	// * Integerersects the angle swept about n through the solid portion between
	// * its own neighbouring points. If intersection found then fail.
	// *
	// * It's worth noting that In this case a valid triangulation may still
	// * fail, but it doesn't matter as the polygon will be reduced elsewhere
	// * until an ear at b is made passable. Also the alternative, to more
	// * thoroughly check validity here, is way too overcomplicated...
	// */
	// // TO DO (very rare case, only for *very* redundant empty geometry)
	//
	// previousVertex = vertex;
	// }
	//
	// return true;
	// }
}
