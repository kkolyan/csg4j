package jcsg;

import java.util.ArrayList;
import java.util.List;

// # class Polygon

// Represents a convex polygon. The vertices used to initialize a polygon must
// be coplanar and form a convex loop. They do not have to be `CSG.Vertex`
// instances but they must behave similarly (duck typing can be used for
// customization).
//
// Each convex polygon has a `shared` property, which is shared between all
// polygons that are clones of each other or were split from the same polygon.
// This can be used to define per-polygon properties (such as surface color).

public class Polygon {
    public final List<Vertex> vertices;
    public final boolean shared;
    public final Plane plane;

    public Polygon(List<Vertex> vertices, boolean shared) {
        this.vertices = vertices;
        this.shared = shared;
        this.plane = Plane.fromPoints(vertices.get(0).pos, vertices.get(1).pos, vertices.get(2).pos);
    }

    public Polygon clone() {
        List<Vertex> vertices = new ArrayList<Vertex>();
        for (Vertex vertex: this.vertices) {
            vertices.add(vertex.clone());
        }
        return new Polygon(vertices, shared);
    }

    public void flip() {
        for (Vertex vertex: vertices) {
            vertex.flip();
        }
        plane.flip();
    }
}
