package com.gp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FormDataController.class)
public class FormDataControllerTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void testCreateComment() throws Exception {
    String content = String.valueOf(new Random().nextInt());

    MockHttpServletRequestBuilder request1 = post("/comments")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("content", content)
            .param("author", "Dwayne");

    this.mvc.perform(request1)
            .andExpect(status().isOk())
            .andExpect(content().string(String.format("Dwayne said %s!", content)));
  }

  @Test
  public void calculateAreaForCircleTest() throws Exception {
    MockHttpServletRequestBuilder request = post("/math/area/circle")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("radius", "8");

    this.mvc.perform(request)
            .andExpect(status().isOk());
  }

  @Test
  public void calculateAreaForRectangleTest() throws Exception {
    MockHttpServletRequestBuilder request = post("/math/area/rectangle")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("width", "13")
            .param("height", "13");

    this.mvc.perform(request)
            .andExpect(status().isOk());
  }

  @Test
  public void expectOutputToRenderInvalidWithNullOrBadParams() throws Exception {
    MockHttpServletRequestBuilder request = post("/math/area")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("type", "circle");

    this.mvc.perform(request)
            .andExpect(content().string("Invalid"));
  }
}
