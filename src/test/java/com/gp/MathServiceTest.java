package com.gp;

import static org.junit.Assert.*;
import org.junit.Test;

public class MathServiceTest {

  @Test
  public void calculateShouldReturnInteger() {
    MathService calculator = new MathService("add");
    Integer result = calculator.calculate(5, 7);
    assertTrue(result instanceof Integer);
  }
}
