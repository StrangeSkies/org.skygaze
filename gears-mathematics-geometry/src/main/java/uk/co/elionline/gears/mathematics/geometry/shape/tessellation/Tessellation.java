package uk.co.elionline.gears.mathematics.geometry.shape.tessellation;

import uk.co.elionline.gears.mathematics.expressions.Expression;
import uk.co.elionline.gears.mathematics.geometry.matrix.vector.Vector2;
import uk.co.elionline.gears.mathematics.geometry.shape.SimplePolygon;
import uk.co.elionline.gears.mathematics.geometry.shape.mesh.Mesh;
import uk.co.elionline.gears.mathematics.geometry.shape.mesh.Mesh.MeshingScheme;
import uk.co.elionline.gears.mathematics.values.Value;

public interface Tessellation<V extends Value<V>> extends
		Expression<Mesh<Vector2<V>>> {
	public void setPolygon(SimplePolygon<?, V> polygon);

	public void setLimit(int limit);

	public void setTessellationScheme(MeshingScheme tessellationScheme);

	public MeshingScheme getTessellationScheme();
}
