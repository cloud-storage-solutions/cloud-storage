package org.cloud.storage.commons.parsers;

import java.util.HashMap;
import java.util.Map;

import org.cloud.storage.providers.HttpClientCloudProvider;
import org.cloud.storage.providers.dropbox.DropBoxHttpClient;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class SpaceQuotaParser {
	private final Map<Class<? extends HttpClientCloudProvider>, JsonElement> spaceQuotaParsers = new HashMap<>();

	public SpaceQuotaParser(final String spaceQuota) {
		final JsonElement dropBoxSpaceQuoataJsonElement = new JsonParser().parse(spaceQuota).getAsJsonObject()
				.get("allocation").getAsJsonObject().get("allocated");
		spaceQuotaParsers.put(DropBoxHttpClient.class, dropBoxSpaceQuoataJsonElement);
	}

	public long parseProviderSpaceQuoata(Class<? extends HttpClientCloudProvider> provider) {
		return spaceQuotaParsers.get(provider).getAsLong();
	}
}
