/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example;

import java.sql.SQLException;

public class App {
  public static void main(String[] args) throws SQLException {
    Product product = new Product(1, "Product 1 updated", "Category 1 updated", 20, 2000);
    ProductDao productDao = new ProductDao();
    productDao.updateProduct(product);
  }
}
