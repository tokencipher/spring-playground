package com.gp;

import org.springframework.web.context.request.WebRequest;

import java.lang.String;
import java.util.Map;

public class MathService {
  private String operation;

  public MathService() {}

  public MathService(String operation) {
    this.operation = operation;
  }

  public String getOperation() {
    return operation;
  }

  public Integer add(int x, int y) {
    return x + y;
  }

  public Integer subtract(int x, int y) {
    return x - y;
  }

  public Integer multiply(int x, int y) {
    return x * y;
  }

  public Integer divide(int x, int y) {
    return x / y;
  }

  public Integer calculate(int x, int y) {
    switch (operation.toLowerCase()) {
      case "add":
        return add(x, y);

      case "subtract":
        return subtract(x, y);

      case "multiply":
        return multiply(x, y);

      case "divide":
        return divide(x, y);

      case "":
        return x + y;
    }

    return null;
  }

  public Integer calculateSum(WebRequest webRequest) {
    Map<String, String[]> params = webRequest.getParameterMap();
    String[] results = params.get("n");
    int acc = 0;
    for (int x = 0; x <= results.length - 1; x++) {
      acc += Integer.parseInt(results[x]);
    }
    return acc;
  }
  

}
