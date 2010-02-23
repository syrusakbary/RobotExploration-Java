package world.objects;

import graph.CellEdge;

import java.awt.Point;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import map.Cell;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.SimpleGraph;

import world.World;
import world.WorldObjectException;

abstract public class DijkstraRobot extends BasicRobot{
	private UndirectedGraph<Cell, CellEdge> graph = new SimpleGraph<Cell, CellEdge>(CellEdge.class);
	public DijkstraRobot(World world) {
		super(world);
		// TODO Auto-generated constructor stub
	}

	public void move(Cell cell) throws WorldObjectException {
		
		//if (this.isExplored(cell)) {
			
			List path = this.findPath(this.getCell(), cell);
			Iterator<CellEdge> it = path.iterator();
			while (it.hasNext()) {
				this.moveTo(it.next().nextCell(this.getCell()));
			}
		//}
	}
	public HashSet<Cell> getVertexCells () {
		HashSet<Cell> cells = new HashSet<Cell>();
		cells.addAll(this.graph.vertexSet());
		return cells;
	}
	protected List findPath (Cell c1, Cell c2) {
		return DijkstraShortestPath.findPathBetween(this.graph,c1, c2);
	}
	public void setExplored (Cell cell) {
		//System.out.println("set explored"+cell);
		this.graph.addVertex(cell);
		this.addExplored(cell);
		for (Cell canGo: this.getMovements(cell)) {
			if (!canGo.equals(cell) && this.isVisitable(canGo)) {
				this.graph.addVertex(canGo);
				this.graph.addEdge(cell, canGo);				
			}
		}
		for (Cell view: this.getViews(cell)) {
			this.addExplored(view);
		}
	}
}
