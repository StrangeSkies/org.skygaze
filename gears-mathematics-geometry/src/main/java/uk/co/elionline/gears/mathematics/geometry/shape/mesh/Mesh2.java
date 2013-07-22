package uk.co.elionline.gears.mathematics.geometry.shape.mesh;

import java.util.List;

import uk.co.elionline.gears.mathematics.geometry.matrix.Vector2;
import uk.co.elionline.gears.mathematics.values.Value;

public interface Mesh2<V extends Value<V>> {
	public enum TessellationScheme {
		Triangles, Fans, Strips, AlternatingStrips;
	}

	public TessellationScheme getTessellationScheme();

	public List<Vector2<V>> getVertices();
}
