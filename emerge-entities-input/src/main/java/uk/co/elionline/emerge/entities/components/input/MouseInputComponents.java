package uk.co.elionline.emerge.entities.components.input;

import java.util.Set;
import java.util.UUID;

import uk.co.elionline.emerge.entities.LockedEntityManager;
import uk.co.elionline.emerge.entities.behaviour.BehaviourComponent;
import uk.co.elionline.emerge.entities.behaviour.BehaviourComponentProcess;
import uk.co.elionline.emerge.entities.state.StateComponent;
import uk.co.elionline.emerge.input.MouseInputController;
import uk.co.elionline.emerge.input.MouseMovementAdapter;
import uk.co.elionline.emerge.input.MouseMovementAdapter.MovementType;
import uk.co.elionline.emerge.input.WindowManagerInputController;
import uk.co.elionline.emerge.mathematics.geometry.matrices.Vector2;
import uk.co.elionline.emerge.mathematics.values.IntValue;
import uk.co.elionline.emerge.utilities.CopyFactory;

public class MouseInputComponents {
	private final StateComponent<CursorStateData> cursorState;

	private final BehaviourComponent cursorBehaviour;

	public MouseInputComponents(MouseInputController mouseInputController,
			WindowManagerInputController windowManagerInputController) {
		final MouseMovementAdapter mouseMovementAdapter = new MouseMovementAdapter(
				mouseInputController, windowManagerInputController);
		mouseMovementAdapter.setMovementType(MovementType.Relative);

		cursorState = StateComponent.<CursorStateData> builder()
				.name("Cursor State")
				.description("The state of an entity which behaves like a cursor")
				.dataFactory(new CopyFactory<>(new CursorStateData())).create();

		cursorBehaviour = BehaviourComponent.builder().name("Cursor Behaviour")
				.description("The behaviour of a cursor")
				.writeDependencies(cursorState)
				.process(new BehaviourComponentProcess() {
					@Override
					public void process(Set<? extends UUID> entities,
							LockedEntityManager entityManager) {
						for (UUID entity : entities) {
							CursorStateData cursorStateData = entityManager.getStateManager()
									.getData(entity, getCursorState());

							Vector2<IntValue> nextPosition = mouseMovementAdapter
									.getPosition().getBack();
							Vector2<IntValue> currentPosition = cursorStateData.getPosition();

							cursorStateData.getVelocity().set(
									currentPosition.subtract(nextPosition).negate());
							currentPosition.set(nextPosition);
						}
					}
				}).create();
	}

	public final StateComponent<CursorStateData> getCursorState() {
		return cursorState;
	}

	public final BehaviourComponent getCursorBehaviour() {
		return cursorBehaviour;
	}
}
