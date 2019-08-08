package com.gp;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

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

  @PostMapping("/math/sum")
  public String calculateSum(WebRequest webRequest) {
    Map<String, String[]> params = webRequest.getParameterMap();
    String[] results = params.get("n");
    int acc = 0;
    for (int x = 0; x < results.length - 1; x++) {
      acc += Integer.parseInt(results[x]);
    }
    return Integer.toString(acc);
  }


  @GetMapping("/math/calculate")
  public String calculate(
          @RequestParam(required = false, defaultValue = "") String operation,
          @RequestParam Integer x,
          @RequestParam Integer y) {

    Integer result = null;

    switch (operation.toLowerCase()) {

      case "add":
        result = x + y;
        break;

      case "subtract":
        result = x - y;
        break;

      case "multiply":
        result = x * y;
        break;

      case "divide":
        result = x / y;
        break;

      case "":
        result = x + y;
        break;

      default:
        result = x + y;

    }

    return result.toString();
  }

  @GetMapping("/vehicle")
  public String getVehicle(@RequestParam(required = false) String type) {
    return type;
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
