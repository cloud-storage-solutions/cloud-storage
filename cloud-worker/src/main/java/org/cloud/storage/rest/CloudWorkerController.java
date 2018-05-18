package org.cloud.storage.rest;

import static http.constants.CloudWorkerRestApiConstants.CLOUD_WORKER_REST_PATH;
import static http.constants.CloudWorkerRestApiConstants.DOWNLOAD_FILE_PATH;
import static http.constants.CloudWorkerRestApiConstants.QUOTA_PATH;
import static http.constants.CloudWorkerRestApiConstants.UPLOAD_FILE_PATH;
import static org.apache.commons.io.FileUtils.writeByteArrayToFile;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM_VALUE;

import java.io.File;
import java.io.IOException;

import org.cloud.storage.worker.services.CloudProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = CLOUD_WORKER_REST_PATH, consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
public class CloudWorkerController {

	@Autowired
	private CloudProviderService cloudProviderService;

	@GetMapping(QUOTA_PATH)
	public String getQuota() throws IOException {
		return cloudProviderService.getSpaceQuota();
	}

	@GetMapping(DOWNLOAD_FILE_PATH)
	public void download(@PathVariable("file") String filePath) throws IOException {
		cloudProviderService.download(filePath);
	}

	@PostMapping(path = UPLOAD_FILE_PATH, consumes = APPLICATION_OCTET_STREAM_VALUE)
	@ResponseStatus(value = INTERNAL_SERVER_ERROR, reason = "Error while trying to upload a file")
	@ExceptionHandler(IOException.class)
	public void upload(@RequestBody MultipartFile multipartFile, @PathVariable("destination") String destination)
			throws IOException {
		final File file = null;
		writeByteArrayToFile(file, multipartFile.getBytes());
		cloudProviderService.upload(file, destination);
	}
}
