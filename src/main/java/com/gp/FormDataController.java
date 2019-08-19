package com.gp;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class FormDataController {

  // The simplest (and least useful) way to get access to the body is to just grab the
  // entire body as a String, using the @RequestBody annotation on the parameter
  @PostMapping("/string-example")
  public String getRawString(@RequestBody String rawBody) {
    return rawBody;
  }

  // A slightly more useful way to process form parameters it to turn them into a Map
  // NOTE: You need to use @RequestParam in order to get form data as a Map - not
  // @RequestBody as you might expect
  @PostMapping(path = "/people")
  public String showFormData(@RequestParam Map<String, String> body) {
    return body.toString();
  }

  // Access request body as an object
  @PostMapping(path = "/person", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String showFormDataObject(@RequestBody Person person) {
    return person.toString();
  }

  // One way you can get access to to the data is to put all of the querystring params and
  // form data params into the same object. The thing to note here is that @RequestParam
  // grabs everything from both querystring and form body.
  @PostMapping("/posts/{postId}/comments")
  public String createComment(@PathVariable int postId, @RequestParam Map<String, String> params) {
    return String.format(
            "postId:%d notify:%s content:%s author:%s",
            postId,
            params.get("notify"),
            params.get("content"),
            params.get("author")
    );
  }

  @PostMapping(path = "/comments", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String getComment(@RequestParam String author, @RequestParam String content) {
    return String.format("%s said %s!", author, content);
  }

  @PostMapping(path = "/math/area/circle", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String calculateCircleArea(Circle circle) {
    double radius = circle.getRadius();
    double area = circle.calculateArea();
    return String.format("Area of a circle with a radius of %.1f is %f", radius, area);
  }

  @PostMapping(path = "/math/area/rectangle", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String calculateRectangleArea(Rectangle rectangle) {
    int width = rectangle.getWidth();
    int height = rectangle.getHeight();
    double area = rectangle.calculateArea();
    return String.format("Area of a rectangle %dx%d rectangle is %.2f", width, height, area);
  }

  @PostMapping(path = "/math/area", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String calculateArea(@RequestParam Map<String, String> params) {
    String type = params.get("type");
    String output;
    double radius;
    int width;
    int height;
    double area;

    if (type.equals("circle") && params.get("radius") != null) {
      radius = Double.parseDouble(params.get("radius"));
      area = Math.PI * (Math.pow(radius, 2));
      output = String.format("Area of a circle with a radius of %.1f is %f", radius, area);
    } else if (type.equals("rectangle") && params.get("width") != null && params.get("height") != null) {
      width = Integer.parseInt(params.get("width"));
      height = Integer.parseInt(params.get("height"));
      area = Math.PI * (width * height);
      System.out.println("Area is: " + area);
      output = String.format("Area of a %dx%d rectangle is %.2f", width, height, area);
    } else {
      output = "Invalid";
    }

    return output;

  }

}
