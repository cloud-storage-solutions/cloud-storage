package org.cloud.storage.rest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.cloud.storage.worker.services.CloudProviderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import http.constants.CloudWorkerRestApiConstants;

@RunWith(SpringRunner.class)
@WebMvcTest(CloudWorkerController.class)
public class CloudWorkerControllerTest {
	private static final String SPACE_QUOTA = "space-quota";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private CloudProviderService cloudProviderService;

	@Test
	public void testGetQuota() throws Exception {
		when(cloudProviderService.getSpaceQuota()).thenReturn(SPACE_QUOTA);

		mockMvc.perform(get(CloudWorkerRestApiConstants.CLOUD_WORKER_REST_PATH).accept(APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk()).andExpect(content().string(equalTo(SPACE_QUOTA)));
	}
}
