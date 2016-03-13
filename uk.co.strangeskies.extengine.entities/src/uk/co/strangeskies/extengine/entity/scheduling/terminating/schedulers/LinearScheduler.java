package uk.co.strangeskies.extengine.entity.scheduling.terminating.schedulers;

import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.extengine.entity.scheduling.ScheduleProcessingContext;
import uk.co.strangeskies.extengine.entity.scheduling.terminating.TerminatingScheduler;
import uk.co.strangeskies.mathematics.graph.Graph;
import uk.co.strangeskies.mathematics.graph.GraphListeners;
import uk.co.strangeskies.mathematics.graph.building.GraphBuilder;
import uk.co.strangeskies.mathematics.graph.building.GraphConfigurator;
import uk.co.strangeskies.mathematics.graph.processing.GraphProcessor;

public class LinearScheduler implements TerminatingScheduler {
	private GraphBuilder graphBuilder;

	public LinearScheduler(GraphBuilder graphBuilder) {
		setGraphBuilder(graphBuilder);
	}

	public void setGraphBuilder(GraphBuilder builder) {
		graphBuilder = builder;
	}

	public GraphConfigurator<Object, Object> buildGraph() {
		return graphBuilder.configure();
	}

	@Override
	public synchronized boolean process(ScheduleProcessingContext processingContext) {
		Graph<BehaviourComponent, ?> behaviourGraph = buildGraph().vertices(processingContext.getBehaviours())
				.addInternalListener(GraphListeners::vertexAdded, c -> {
					for (BehaviourComponent b : c.vertex().getBehaviourDependents()) {
						c.graph().vertices().add(b);
						c.graph().edges().add(c.vertex(), b);
					}
					for (BehaviourComponent b : c.vertex().getBehaviourDependencies()) {
						c.graph().vertices().add(b);
						c.graph().edges().add(b, c.vertex());
					}
				}).directed().acyclic().readOnly().edgeFactory(Object::new).create();

		new GraphProcessor().begin(behaviourGraph, processingContext::processBehaviour).processEagerParallel();

		return true;
	}
}
