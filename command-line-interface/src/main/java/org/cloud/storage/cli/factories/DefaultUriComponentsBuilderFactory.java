package org.cloud.storage.cli.factories;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class DefaultUriComponentsBuilderFactory {
	public UriComponentsBuilder createUriComponentsBuilder() {
		return UriComponentsBuilder.newInstance();
	}
}
