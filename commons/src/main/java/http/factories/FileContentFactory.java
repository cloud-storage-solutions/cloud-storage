package http.factories;

import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM_VALUE;

import java.io.File;

import org.springframework.stereotype.Component;

import com.google.api.client.http.FileContent;

@Component
public class FileContentFactory {
	public FileContent createFileContent(final File file) {
		return new FileContent(APPLICATION_OCTET_STREAM_VALUE, file);
	}
}
