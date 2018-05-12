package org.cloud.storage.rest;

import java.io.IOException;

import org.cloud.storage.worker.services.CloudProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CloudWorkerController {
	@Autowired
	private CloudProviderService cloudProviderService;

	@GetMapping("/quota")
	public String getQuota() throws IOException {
		return cloudProviderService.getSpaceQuota();
	}

	@PostMapping("/test")
	public void upload() {

	}
}
