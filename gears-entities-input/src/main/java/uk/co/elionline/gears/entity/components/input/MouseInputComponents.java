package uk.co.elionline.gears.entity.components.input;

import uk.co.elionline.gears.entity.Entity;
import uk.co.elionline.gears.entity.behaviour.BehaviourComponent;
import uk.co.elionline.gears.entity.behaviour.BehaviourComponentBuilder;
import uk.co.elionline.gears.entity.behaviour.BehaviourProcess;
import uk.co.elionline.gears.entity.behaviour.BehaviourProcessingContext;
import uk.co.elionline.gears.entity.state.StateComponent;
import uk.co.elionline.gears.entity.state.StateComponentBuilder;
import uk.co.elionline.gears.input.MouseInputController;
import uk.co.elionline.gears.input.MouseMovementAdapter;
import uk.co.elionline.gears.input.MouseMovementAdapter.MovementType;
import uk.co.elionline.gears.input.WindowManagerInputController;
import uk.co.elionline.gears.mathematics.geometry.matrix.builder.MatrixBuilder;
import uk.co.elionline.gears.mathematics.geometry.matrix.vector.Vector2;
import uk.co.elionline.gears.mathematics.values.IntValue;
import uk.co.elionline.gears.utilities.CopyFactory;

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
