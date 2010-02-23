package graph;
import map.Cell;

import org.jgrapht.graph.DefaultEdge;

	public class CellEdge extends DefaultEdge {
		public Cell nextCell (Cell cell) {
			return cell.equals((Cell)this.getSource())?(Cell)this.getTarget():(Cell)this.getSource();
		}
	}