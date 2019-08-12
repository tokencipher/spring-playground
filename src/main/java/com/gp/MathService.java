package com.gp;

import java.lang.String;

public class MathService {
  private String operation;

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
  

}
