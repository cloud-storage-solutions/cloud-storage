package org.cloud.storage.rest;

import static http.constants.CloudWorkerRestApiConstants.CLOUD_WORKER_REST_PATH;
import static http.constants.CloudWorkerRestApiConstants.QUOTA_PATH;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.io.IOException;

import org.cloud.storage.worker.services.CloudProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = CLOUD_WORKER_REST_PATH, produces = APPLICATION_JSON_UTF8_VALUE)
public class CloudWorkerController {

	@Autowired
	private CloudProviderService cloudProviderService;

	@GetMapping(QUOTA_PATH)
	public String getQuota() throws IOException {
		return cloudProviderService.getSpaceQuota();
	}

	@PostMapping("/test")
	public void upload() {

	}
}
