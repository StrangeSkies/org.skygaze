package uk.co.strangeskies.gears.entity.declaration.impl.xml;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import uk.co.strangeskies.gears.entity.declaration.DeclarationNode;
import uk.co.strangeskies.gears.entity.declaration.DeclarationTreeProcessor;

public class XMLDeclarationTreeProcessor implements DeclarationTreeProcessor {
	@Override
	public <T> T processInput(DeclarationNode<T> rootNode, InputStream input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T processOutput(DeclarationNode<T> rootNode, OutputStream output) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFormatName() {
		return "XML";
	}

	@Override
	public List<String> getFileExtentions() {
		return Arrays.asList("xml");
	}
}
