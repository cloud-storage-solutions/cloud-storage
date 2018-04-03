package org.cloud.storage.providers.dropbox;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import org.cloud.storage.commons.http.factory.RequestFactory;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
class DropBoxHttpClientTest {
   private static final String RESPONSE_BODY = "{\n" +
         "    \"used\": 314159265,\n" +
         "    \"allocation\": {\n" +
         "        \".tag\": \"individual\",\n" +
         "        \"allocated\": 10000000000\n" +
         "    }\n" +
         "}";

   private DropBoxHttpClient dropBoxHttpClient;

   @Mock
   private RequestFactory requestFactory;

   @Mock
   private HttpRequest httpRequest;

   @Mock
   private HttpResponse httpResponse;

   @BeforeEach
   public void setup() throws Exception {
      MockitoAnnotations.initMocks(this);
      when(httpResponse.parseAsString()).thenReturn(RESPONSE_BODY);
      when(httpRequest.execute()).thenReturn(httpResponse);
      when(requestFactory.createPostRequest(anyString())).thenReturn(httpRequest);
      dropBoxHttpClient = new DropBoxHttpClient(requestFactory);
   }

   @Test
   void upload() {
      assertThat(false, Matchers.allOf());
   }

   @Test
   void download() {
   }

   @Test
   void getSpaceQuota() throws IOException {
      assertThat(dropBoxHttpClient.getSpaceQuota(), equalTo("10GB"));
   }

}