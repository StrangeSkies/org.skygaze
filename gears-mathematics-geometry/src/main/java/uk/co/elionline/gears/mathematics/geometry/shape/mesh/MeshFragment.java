package uk.co.elionline.gears.mathematics.geometry.shape.mesh;

import java.util.List;

import uk.co.elionline.gears.mathematics.geometry.matrix.vector.Vector;
import uk.co.elionline.gears.mathematics.values.IntValue;

public interface MeshFragment<T extends Vector<?, ?>> {
	public enum MeshingScheme {
		Triangles, Fan, Strip, AlternatingStrip;
	}

	public MeshingScheme getMeshingScheme();

	public List<T> getVertices();

	public List<IntValue> getIndices();
}
