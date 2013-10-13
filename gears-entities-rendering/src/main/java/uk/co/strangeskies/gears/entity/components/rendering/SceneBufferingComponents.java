package uk.co.strangeskies.gears.entity.components.rendering;

import uk.co.strangeskies.gears.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.gears.entity.state.StateComponent;
import uk.co.strangeskies.gears.rendering.buffering.SceneBuffer;
import uk.co.strangeskies.gears.rendering.buffering.SceneInterpolator;

public interface SceneBufferingComponents {
	public StateComponent<SceneBuffer> getSceneBufferState();

	public StateComponent<SceneInterpolator> getInterpolatableSceneBufferState();

	public BehaviourComponent getBufferingBehaviour();

	public BehaviourComponent getBufferingBehaviour(SceneBuffer buffer);
}