package org.example;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class ProductPane extends GridPane {
  public ProductPane() {
    super(16, 0);
    ColumnConstraints tableColumnConstraints = new ColumnConstraints();
    ColumnConstraints formColumnConstraints = new ColumnConstraints();
    RowConstraints rowConstraints = new RowConstraints();

    tableColumnConstraints.setPercentWidth(70);
    formColumnConstraints.setPercentWidth(30);
    rowConstraints.setPercentHeight(100);

    super.getColumnConstraints().addAll(tableColumnConstraints, formColumnConstraints);
    super.getRowConstraints().add(rowConstraints);
  }
}
