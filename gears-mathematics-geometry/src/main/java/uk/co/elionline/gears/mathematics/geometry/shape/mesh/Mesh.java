package uk.co.elionline.gears.mathematics.geometry.shape.mesh;

import java.util.List;
import java.util.Set;

import uk.co.elionline.gears.mathematics.geometry.matrix.vector.Vector;

public interface Mesh<T extends Vector<?, ?>> {
	public enum MeshingScheme {
		Triangle, TriangleList, Fan, Strip, AlternatingStrip;
	}

	public List<T> getVertices();

	public MeshingScheme getMeshingScheme();

	public Set<MeshFragment> getFragments();
}
