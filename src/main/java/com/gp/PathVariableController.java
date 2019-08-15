package com.gp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PathVariableController {

  @RequestMapping("/math/volume/{length}/{width}/{height}")
  public String getVolume(@PathVariable int length, @PathVariable int width, @PathVariable int height) {
    int volume = length * width * height;
    return String.format("The volume of a %dx%dx%d rectangle is %d", length, width, height, volume);
  }

  // Access individual path variables
  @GetMapping("/example/{q}/{from}") // matches /example/bar/foo
  public String getIndividualParams(@PathVariable String from, @PathVariable("q") String query) {
    return String.format("q:%s from:%s", query, from);
  }

  // Access path variables in a map
  @GetMapping("/work/tasks/{taskId}/comments/{commentId}")
  public String getCommentsForTask(@PathVariable Map pathVariables) {
    return pathVariables.toString();
  }

  // Access path variables from an object
  /**
   *
   * @param ids
   * When a request comes in:
   * 1. Spring matches then inspects the RequestMapping and finds your getCommentsForTask method
   * 2. Spring sees that you have defined a parameter of type Task so it creates an instance of that class
   * 3. Spring iterates through the path variables and looks for corresponding setters (setTaskId etc...)
   * 4. Spring passes the instantiated, inflated Task object to your method
   */
  @GetMapping("/test/tasks/{taskId}/comments/{commentId}")
  public String getCommentsForTask(Task ids) {
    return String.format("taskId is %d; commentId is %s", ids.getTaskId(), ids.getCommentId());
  }

}
