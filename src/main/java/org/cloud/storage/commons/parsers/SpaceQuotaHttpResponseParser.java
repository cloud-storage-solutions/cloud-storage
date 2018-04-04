package org.cloud.storage.commons.parsers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.cloud.storage.providers.CloudProvider;
import org.cloud.storage.providers.dropbox.DropBoxHttpClient;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class SpaceQuotaHttpResponseParser {
	private final Map<Class<? extends CloudProvider>, JsonElement> spaceQuotaParsers = new HashMap<>();

	public SpaceQuotaHttpResponseParser(final String spaceQuota) throws JsonSyntaxException, IOException {
		final JsonElement dropBoxSpaceQuoataJsonElement = new JsonParser().parse(spaceQuota).getAsJsonObject()
				.get("allocation").getAsJsonObject().get("allocated");
		spaceQuotaParsers.put(DropBoxHttpClient.class, dropBoxSpaceQuoataJsonElement);
	}

	public long getParsedSpaceQuoata(final Class<? extends CloudProvider> provider) {
		return spaceQuotaParsers.get(provider).getAsLong();
	}
}
