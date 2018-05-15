package org.cloud.storage;

import org.cloud.storage.worker.services.CloudProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "org.cloud.storage", "http.factories" })
public class CloudStorageApplication {
	@Autowired
	public CloudProviderService cloudProviderService;

	public static void main(String[] args) {
		SpringApplication.run(CloudStorageApplication.class, args);
	}
}
