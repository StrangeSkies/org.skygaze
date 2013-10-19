package uk.co.strangeskies.gears.entity.declaration;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface DeclarationTreeProcessor {
	public <T> T processInput(DeclarationNode<T> rootNode, InputStream input);

	public <T> T processOutput(DeclarationNode<T> rootNode, OutputStream output);

	public String getFormatName();

	public List<String> getFileExtentions();
}
