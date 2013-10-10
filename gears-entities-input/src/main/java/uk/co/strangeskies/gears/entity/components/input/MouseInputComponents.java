package uk.co.strangeskies.gears.entity.components.input;

import uk.co.strangeskies.gears.entity.Entity;
import uk.co.strangeskies.gears.entity.behaviour.BehaviourComponent;
import uk.co.strangeskies.gears.entity.behaviour.BehaviourComponentBuilder;
import uk.co.strangeskies.gears.entity.behaviour.BehaviourProcess;
import uk.co.strangeskies.gears.entity.behaviour.BehaviourProcessingContext;
import uk.co.strangeskies.gears.entity.state.StateComponent;
import uk.co.strangeskies.gears.entity.state.StateComponentBuilder;
import uk.co.strangeskies.gears.input.MouseInputController;
import uk.co.strangeskies.gears.input.MouseMovementAdapter;
import uk.co.strangeskies.gears.input.WindowManagerInputController;
import uk.co.strangeskies.gears.input.MouseMovementAdapter.MovementType;
import uk.co.strangeskies.gears.mathematics.geometry.matrix.builder.MatrixBuilder;
import uk.co.strangeskies.gears.mathematics.geometry.matrix.vector.Vector2;
import uk.co.strangeskies.gears.mathematics.values.IntValue;
import uk.co.strangeskies.gears.utilities.CopyFactory;

public class MouseInputComponents {
	private final StateComponent<CursorStateData> cursorState;

	private final BehaviourComponent cursorBehaviour;

	public MouseInputComponents(MouseInputController mouseInputController,
			WindowManagerInputController windowManagerInputController,
			BehaviourComponentBuilder behaviourComponentBuilder,
			StateComponentBuilder stateComponentBuilder, MatrixBuilder matrixBuilder) {
		final MouseMovementAdapter mouseMovementAdapter = new MouseMovementAdapter(
				mouseInputController, windowManagerInputController, matrixBuilder);
		mouseMovementAdapter.setMovementType(MovementType.Relative);

		cursorState = stateComponentBuilder
				.data(new CopyFactory<>(new CursorStateData())).name("Cursor State")
				.description("The state of an entity which behaves like a cursor")
				.create();

		cursorBehaviour = behaviourComponentBuilder
				.process(new BehaviourProcess() {
					@Override
					public void process(BehaviourProcessingContext processingContext) {
						for (Entity entity : processingContext.getEntities()) {
							CursorStateData cursorStateData = processingContext.entities()
									.state().getData(entity, getCursorState());

							Vector2<IntValue> nextPosition = mouseMovementAdapter
									.getPosition().getBack();
							Vector2<IntValue> currentPosition = cursorStateData.getPosition();

							cursorStateData.getVelocity().set(
									currentPosition.subtract(nextPosition).negate());
							currentPosition.set(nextPosition);
						}
					}
				}).name("Cursor Behaviour").description("The behaviour of a cursor")
				.writeDependencies(cursorState).create();
	}

	public final StateComponent<CursorStateData> getCursorState() {
		return cursorState;
	}

	public final BehaviourComponent getCursorBehaviour() {
		return cursorBehaviour;
	}
}
