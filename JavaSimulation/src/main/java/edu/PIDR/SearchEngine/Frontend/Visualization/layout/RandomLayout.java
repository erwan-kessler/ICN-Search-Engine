package edu.PIDR.SearchEngine.Frontend.Visualization.layout;

import java.util.List;
import java.util.Random;

import edu.PIDR.SearchEngine.Frontend.Visualization.graph.Graph;
import edu.PIDR.SearchEngine.Frontend.Visualization.graph.ICell;

public class RandomLayout implements Layout {

	private final Random rnd = new Random();

	@Override
	public void execute(Graph graph) {
		final List<ICell> cells = graph.getModel().getAllCells();

		for (final ICell cell : cells) {
			final double x = rnd.nextDouble() * 500;
			final double y = rnd.nextDouble() * 500;

			graph.getGraphic(cell).relocate(x, y);
		}
	}

}