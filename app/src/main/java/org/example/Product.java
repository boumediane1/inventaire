package org.example;

public class Product {
  private int id;
  private String name;
  private String category;
  private double price;
  private int quatity;

  public Product(String name, String category, double price, int quatity) {
    this.name = name;
    this.category = category;
    this.price = price;
    this.quatity = quatity;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public int getQuatity() {
    return quatity;
  }

  public void setQuatity(int quatity) {
    this.quatity = quatity;
  }
}
