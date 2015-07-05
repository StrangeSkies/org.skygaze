package uk.co.strangeskies.extengine.entity.components.input;

import uk.co.strangeskies.extengine.entity.Entity;
import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.extengine.entity.behaviour.BehaviourComponentBuilder;
import uk.co.strangeskies.extengine.entity.state.StateComponent;
import uk.co.strangeskies.extengine.entity.state.StateComponentBuilder;
import uk.co.strangeskies.extengine.input.MouseInputController;
import uk.co.strangeskies.extengine.input.MouseMovementAdapter;
import uk.co.strangeskies.extengine.input.MouseMovementAdapter.MovementType;
import uk.co.strangeskies.extengine.input.WindowManagerInputController;
import uk.co.strangeskies.mathematics.geometry.matrix.building.MatrixBuilder;
import uk.co.strangeskies.mathematics.geometry.matrix.vector.Vector2;
import uk.co.strangeskies.mathematics.values.IntValue;

public class MouseInputComponents {
	private final StateComponent<CursorStateData, CursorStateData> cursorState;

	private final BehaviourComponent cursorBehaviour;

	public MouseInputComponents(MouseInputController mouseInputController,
			WindowManagerInputController windowManagerInputController,
			BehaviourComponentBuilder behaviourComponentBuilder,
			StateComponentBuilder stateComponentBuilder, MatrixBuilder matrixBuilder) {
		final MouseMovementAdapter mouseMovementAdapter = new MouseMovementAdapter(
				mouseInputController, windowManagerInputController, matrixBuilder);
		mouseMovementAdapter.setMovementType(MovementType.Relative);

		cursorState = stateComponentBuilder.configure().name("Cursor State")
				.description("The state of an entity which behaves like a cursor")
				.data(CursorStateData::new).create();

		cursorBehaviour = behaviourComponentBuilder
				.configure()
				.name("Cursor Behaviour")
				.description("The behaviour of a cursor")
				.writeDependencies(cursorState)
				.process(
						processingContext -> {
							for (Entity entity : processingContext.participatingEntities()) {
								CursorStateData cursorStateData = processingContext.entity()
										.state().getData(entity, getCursorState());

								Vector2<IntValue> nextPosition = mouseMovementAdapter
										.getPosition().getBack();
								Vector2<IntValue> currentPosition = cursorStateData
										.getPosition();

								cursorStateData.getVelocity().set(
										currentPosition.subtract(nextPosition).negate());
								currentPosition.set(nextPosition);
							}
						}).create();
	}

	public final StateComponent<CursorStateData, CursorStateData> getCursorState() {
		return cursorState;
	}

	public final BehaviourComponent getCursorBehaviour() {
		return cursorBehaviour;
	}
}
