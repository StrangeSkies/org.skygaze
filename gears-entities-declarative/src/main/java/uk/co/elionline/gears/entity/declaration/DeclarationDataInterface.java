package uk.co.elionline.gears.entity.declaration;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface DeclarationDataInterface {
	public List<String> getFilenameExtensions();

	public void serialise(OutputStream outputStream);

	public void unserialise(InputStream outputStream);
}
