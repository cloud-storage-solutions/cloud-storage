package org.cloud.storage;

import org.cloud.storage.providers.dropbox.DropBoxHttpClient;
import org.cloud.storage.services.CloudProviderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CloudStorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudStorageApplication.class, args);
		CloudProviderService cloudProviderService = new CloudProviderService(new DropBoxHttpClient());
		try {
			System.out.println(cloudProviderService.getSpaceQuota());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
