package org.cloud.storage.rest;

import java.io.IOException;

import org.cloud.storage.worker.services.CloudProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CloudWorkerController {
	@Autowired
	private CloudProviderService cloudProviderService;

	@GetMapping("/quota")
	public ResponseEntity<String> getQuota() throws IOException {
		return new ResponseEntity<String>(cloudProviderService.getSpaceQuota(), HttpStatus.OK);
	}
}
