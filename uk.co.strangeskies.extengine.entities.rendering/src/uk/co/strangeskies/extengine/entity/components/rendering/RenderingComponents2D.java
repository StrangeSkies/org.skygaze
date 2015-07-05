package uk.co.strangeskies.extengine.entity.components.rendering;

import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.extengine.entity.state.StateComponent;
import uk.co.strangeskies.extengine.rendering.Renderable;
import uk.co.strangeskies.extengine.rendering.Scene;
import uk.co.strangeskies.extengine.rendering.rendering2d.Camera2D;
import uk.co.strangeskies.extengine.rendering.rendering2d.Data2D;

public interface RenderingComponents2D {
	public BehaviourComponent getRenderingBehaviour();

	public BehaviourComponent getBufferingBehaviour();

	public StateComponent<Renderable<Data2D>, Object> getRenderableState();

	public StateComponent<Camera2D, Object> getCameraState();

	public StateComponent<Scene<Data2D>, Object> getSceneState();
}
