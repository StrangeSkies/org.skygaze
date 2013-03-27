package uk.co.elionline.emerge.mathematics.functions;

import uk.co.elionline.emerge.utilities.Property;

public class TransformationSettingOperation<T extends Property<?, ? super C>, C, F>
		extends AssignmentOperation<T, F> {
	private final Function<? extends C, ? super F> function;

	public TransformationSettingOperation(
			Function<? extends C, ? super F> function) {
		this.function = function;
	}

	@Override
	public void assign(T assignee, F assignment) {
		assignee.set(function.applyTo(assignment));
	}
}
