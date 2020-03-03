package com.louay.project.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Controller {
    public TextField productText;
    public ListView productList;
    public ListView priceList;
    public TextField priceText;
    public MenuItem load;
    public Label editLabel;

    public Controller() {
    }

    public void closeEvent(ActionEvent actionEvent) {
        System.exit(0);
    }


    public void saveEvent(ActionEvent actionEvent) {
        File product = new File("product.txt");
        if (product.exists())
            product.delete();
        for (int i=0;i<productList.getItems().size();i++) {
            Object item = productList.getItems().get(i);
            try(PrintWriter out = new PrintWriter(new FileOutputStream(product,true))){
                out.println(item.toString());

            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

        File price = new File("price.txt");
        if (price.exists())
            price.delete();
        for (int i=0;i<priceList.getItems().size();i++) {
            Object item = priceList.getItems().get(i);
            try(PrintWriter out = new PrintWriter(new FileOutputStream(price,true))){
                out.println(item.toString());
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void pressAddButton(ActionEvent actionEvent) {
        String product = productText.getText();
        String price = priceText.getText();
        if (product.length()>1) {
            if (price.length()>0) {
                productList.getItems().add(product);
                priceList.getItems().add(price);
                productText.clear();
                priceText.clear();
            }
        }
    }

    public void loadEvent(ActionEvent actionEvent) {
        try(Scanner in = new Scanner(new File("product.txt"))) {
            while (in.hasNextLine()) {
                productList.getItems().add(in.nextLine());
            }
        }

        catch (IOException e){
            e.printStackTrace();
        }

        try(Scanner in1 = new Scanner(new File("price.txt"))) {
            while (in1.hasNextLine()) {
                priceList.getItems().add(in1.nextLine());
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    public void about(ActionEvent actionEvent) {
        Stage stage = new Stage();
        StackPane stackPane = new StackPane();
        Label label = new Label("\t   -->Author : Louay Amr<-- \n-->Email : Louay_Amr@outlook.com<--");
        label.setStyle("-fx-text-fill: brown");
        stackPane.getChildren().add(label);
        stage.setScene(new Scene(stackPane,250,250));
        stage.setTitle("About");
        stage.show();
    }


    public void editProduct(MouseEvent mouseEvent) {
        productList.setEditable(true);
        productList.setCellFactory(TextFieldListCell.forListView());
    }


    public void editPrice(MouseEvent mouseEvent) {
        priceList.setEditable(true);
        priceList.setCellFactory(TextFieldListCell.forListView());
    }


    public void deleteEvent(ActionEvent actionEvent) {
        int i = productList.getSelectionModel().getSelectedIndex();
        productList.getItems().remove(i);
        priceList.getItems().remove(i);
    }

    public void showLabelPrice(MouseEvent mouseEvent) {
        priceList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                editLabel.setText("Was: "+oldValue+", Now: "+newValue);
            }
        });
        editLabel.setVisible(true);
        editLabel.setMinWidth(200);
    }

    public void showLabelProduct(MouseEvent mouseEvent) {
        productList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                editLabel.setText("Was: "+oldValue+", Now: "+newValue);
            }
        });
        editLabel.setVisible(true);
        editLabel.setMinWidth(200);
    }

    public void addFromMenu(ActionEvent actionEvent) {
        String product = productText.getText();
        String price = priceText.getText();
        if (product.length()>1) {
            if (price.length()>0) {
                productList.getItems().add(product);
                priceList.getItems().add(price);
                productText.clear();
                priceText.clear();
            }
        }
    }

    public void copyFromMenu(ActionEvent actionEvent) {
        int i = productList.getSelectionModel().getSelectedIndex();
        Object product = productList.getItems().get(i);
        Object price = priceList.getItems().get(i);
        if (price != null){
            if (product != null){
                productList.getItems().add(product);
                priceList.getItems().add(price);
            }
        }
    }
}