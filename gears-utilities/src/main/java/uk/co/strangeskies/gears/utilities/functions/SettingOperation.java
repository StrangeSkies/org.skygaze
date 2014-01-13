package uk.co.strangeskies.gears.utilities.functions;

import uk.co.strangeskies.gears.utilities.Property;

public class SettingOperation<T extends Property<?, ? super C>, C> extends
		AssignmentOperation<T, C> {
	@Override
	public void assign(T assignee, C assignment) {
		assignee.set(assignment);
	}
}
