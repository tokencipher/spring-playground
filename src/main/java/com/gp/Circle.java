package com.gp;

class Circle {
  double radius;

  public void setRadius() {
    this.radius = radius;
  }

  public double getRadius() {
    return radius;
  }

  public double calculateArea() {
    return Math.PI * Math.pow(radius, 2);
  }
}