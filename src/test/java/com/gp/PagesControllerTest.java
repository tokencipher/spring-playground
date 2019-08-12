package com.gp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) // Adds spring specific functionality to your tests
@WebMvcTest(PagesController.class) // Tells Spring to load minimal configuration to test that one controller
@ContextConfiguration(classes={PagesController.class})
public class PagesControllerTest {

  // Instructs spring to hand your test a pre-configured object that knows how to make requests to your controller
  @Autowired
  MockMvc mvc;

  @Test
  public void testHomepage() throws Exception {
    // Create a mock request where you will configure all of the properties of the http request
    // that you'd like to send to your controller for the test such as the verb, path, and optionally
    // things like headers or the body

    RequestBuilder request = MockMvcRequestBuilders.get("/hello");
    this.mvc.perform(request);

  }

  @Test
  public void mathSumEndpointShouldTakePostRequest() throws Exception {
    RequestBuilder request = MockMvcRequestBuilders.post("/math/sum").param("n", "8", "7");
    this.mvc.perform(request).andExpect(status().isOk());
  }

  @Test
  public void mathSumEndpointShouldCalculateSum() throws Exception {
    RequestBuilder request = MockMvcRequestBuilders.post("/math/sum").param("n", "8", "7");
    this.mvc.perform(request).andExpect(content().string("15"));
  }

  @Test
  public void mathCalculateEndpointShouldTakeOptionalParam() throws Exception {
    RequestBuilder request = MockMvcRequestBuilders
            .get("/math/calculate")
            .param("x", "5")
            .param("y", "5");

    this.mvc.perform(request)
            .andExpect(status().isOk());
  }


}
