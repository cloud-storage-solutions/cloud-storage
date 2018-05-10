package org.cloud.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = { org.cloud.storage.worker.services.CloudProviderService.class,
		org.cloud.storage.worker.providers.HttpClientCloudProvider.class })
public class CloudStorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudStorageApplication.class, args);
	}
}
