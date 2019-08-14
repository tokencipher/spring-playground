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
@WebMvcTest(PathVariableController.class) // Tells Spring to load minimal configuration to test that one controller
public class PathVariableControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Test
  public void mathVolumeEndpointShouldReturnOkStatusOnGetRequest() throws Exception {
    RequestBuilder request = MockMvcRequestBuilders.get("/math/volume/2/5/6");
    this.mockMvc.perform(request)
            .andExpect(status().isOk());
  }

  @Test
  public void mathVolumeEndpointShouldReturnOkStatusOnPostRequest() throws Exception {
    RequestBuilder request = MockMvcRequestBuilders.post("/math/volume/2/6/8");
    this.mockMvc.perform(request)
            .andExpect(status().isOk());
  }
}
