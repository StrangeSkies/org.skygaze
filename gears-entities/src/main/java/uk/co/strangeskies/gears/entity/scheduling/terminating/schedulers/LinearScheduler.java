package uk.co.strangeskies.gears.entity.scheduling.terminating.schedulers;

import uk.co.strangeskies.gears.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.gears.entity.scheduling.ScheduleProcessingContext;
import uk.co.strangeskies.gears.entity.scheduling.terminating.TerminatingScheduler;
import uk.co.strangeskies.mathematics.graph.Graph;
import uk.co.strangeskies.mathematics.graph.building.GraphBuilder;
import uk.co.strangeskies.mathematics.graph.building.GraphConfigurator;
import uk.co.strangeskies.mathematics.graph.impl.GraphBuilderImpl;

public class LinearScheduler implements TerminatingScheduler {
	private GraphBuilder graphBuilder;

	public LinearScheduler() {
		setGraphBuilder(new GraphBuilderImpl());
	}

	public void setGraphBuilder(GraphBuilder builder) {
		graphBuilder = builder;
	}

	public GraphConfigurator<Object, Object> buildGraph() {
		return graphBuilder.configure();
	}

	@Override
	public synchronized boolean process(
			ScheduleProcessingContext processingContext) {
		Graph<BehaviourComponent, ?> behaviourGraph = buildGraph()
				.vertices(processingContext.getBehaviours())
				.edgesTo(v -> v.getBehaviourDependents())
				.edgesFrom(v -> v.getBehaviourDependencies()).edgeRuleAsymmetric()
				.acyclic().unmodifiableStructure().create();

		return true;
	}
}
