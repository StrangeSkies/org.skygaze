package uk.co.elionline.gears.mathematics.geometry.shape.tessellation;

import uk.co.elionline.gears.mathematics.geometry.matrix.vector.Vector2;
import uk.co.elionline.gears.mathematics.geometry.shape.SimplePolygon;
import uk.co.elionline.gears.mathematics.geometry.shape.mesh.Mesh;
import uk.co.elionline.gears.mathematics.geometry.shape.mesh.Mesh.MeshingScheme;
import uk.co.elionline.gears.mathematics.values.Value;

public interface Tessellator {
	public <V extends Value<V>> Mesh<Vector2<V>> getTessellationMesh(
			SimplePolygon<?, V> polygon, MeshingScheme scheme, int runLimit,
			boolean restrictToConvex);
}
