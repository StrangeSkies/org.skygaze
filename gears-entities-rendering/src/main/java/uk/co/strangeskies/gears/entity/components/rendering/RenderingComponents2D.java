package uk.co.strangeskies.gears.entity.components.rendering;

import uk.co.strangeskies.gears.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.gears.entity.state.StateComponent;
import uk.co.strangeskies.gears.rendering.Renderable;
import uk.co.strangeskies.gears.rendering.Scene;
import uk.co.strangeskies.gears.rendering.rendering2d.Camera2D;
import uk.co.strangeskies.gears.rendering.rendering2d.Data2D;

public interface RenderingComponents2D {

	public BehaviourComponent getRenderingBehaviour();

	public BehaviourComponent getBufferingBehaviour();

	public StateComponent<Renderable<Data2D>> getRenderableState();

	public StateComponent<Camera2D> getCameraState();

	public StateComponent<Scene<Data2D>> getSceneState();

}