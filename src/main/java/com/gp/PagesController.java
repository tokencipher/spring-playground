package com.gp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

import static javax.swing.text.html.FormSubmitEvent.MethodType.GET;

// Prefix all paths in a controller - In most common applications, all of the mappings in a controller are
// prefixed with the same path. For example: /app/foo /app/bar /app/baz
// You can easily set the prefix for an entire controller with @RequestMapping annotation on the controller class
// Ex: @RequestMapping("/app")

@RestController
public class PagesController {

  @GetMapping("/hello")
  public String sayHello() {
    return "Hello world\n";
  }

  @GetMapping("/math/pi")
  public String getPi() {
    return "3.141592653589793";
  }

  @GetMapping("/math/calculate")
  public String calculate(
          @RequestParam(required = false) String operation,
          @RequestParam Integer x,
          @RequestParam Integer y) {

    Integer result = null;
    if (("add".equals(operation.toLowerCase()))) {
      result = x + y;
    }

    return result.toString();
  }

  // how to access querystring params
  @GetMapping("/tasks")
  public String getTaskInfo(@RequestParam String filter) {
    return String.format("The filter for your task is: %s \n", filter);
  }


  // what if the name of the querystring is not a valid Java parameter name?
  @GetMapping("/people")
  public String getPeople(@RequestParam("sort-by") String sortBy, @RequestParam(value = "sort-dir") String sortDir) {
    return String.format("sortBy is %s and sortDirection is %s", sortBy, sortDir);
  }

  // access querystring data as a map
  @GetMapping("/map-example")
  public String getMapParams(@RequestParam Map querystring) {
    return querystring.toString();
  }

  // access querystring inflated into an custom object
  @GetMapping("/object-example")
  public String getObjectParams(CarInfo carInfo) {
    return String.format("Your car's make is %s and model is %s", carInfo.getMake(), carInfo.getModel());
  }

}
