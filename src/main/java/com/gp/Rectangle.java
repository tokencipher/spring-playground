package com.gp;

class Rectangle {
  int width;
  int height;

  public void setWidth() {
    this.width = width;
  }

  public void setHeight() {
    this.height = height;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public double calculateArea() {
    return Math.PI * (width * height);
  }
}