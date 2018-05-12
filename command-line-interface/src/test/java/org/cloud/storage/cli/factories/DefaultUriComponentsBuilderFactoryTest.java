package org.cloud.storage.cli.factories;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import org.cloud.storage.cli.commands.configuration.CommandConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.util.UriComponentsBuilder;

@RunWith(MockitoJUnitRunner.class)
public class DefaultUriComponentsBuilderFactoryTest {
	private static final String HTTPS_SCHEME = "https";
	private static final String HOST = "host";
	private static final int PORT = 12345;

	@Spy
	@InjectMocks
	private DefaultUriComponentsBuilderFactory defaultUriComponentsBuilderFactory;

	@Mock
	private CommandConfiguration commandConfiguration;

	@Mock
	private UriComponentsBuilder uriComponentsBuilder;

	@Before
	public void setUp() {
		doReturn(uriComponentsBuilder).when(defaultUriComponentsBuilderFactory).createUriComponentsBuilder();
		when(commandConfiguration.getScheme()).thenReturn(HTTPS_SCHEME);
		when(commandConfiguration.getHost()).thenReturn(HOST);
		when(commandConfiguration.getPort()).thenReturn(PORT);

		when(uriComponentsBuilder.scheme(HTTPS_SCHEME)).thenReturn(uriComponentsBuilder);
		when(uriComponentsBuilder.host(HOST)).thenReturn(uriComponentsBuilder);
		when(uriComponentsBuilder.port(PORT)).thenReturn(uriComponentsBuilder);
	}

	@Test
	public void testCreateDefaultUriComponentsBuilder() {
		System.out.println(defaultUriComponentsBuilderFactory.createDefaultUriComponentsBuilder().toUriString());
	}

}
