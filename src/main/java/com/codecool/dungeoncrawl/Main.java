package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public class Main extends Application {
    GameMap map = MapLoader.loadMap();


    int left = 10;
    int right = 10;
    int up = 10;
    int down = 10;
    int height = 10;

    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label damageLabel = new Label();
    Label inventory = new Label();
    Button button = new Button("Pick up");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));


        ui.add(new Label("Health: "), 0, 0);
        ui.add(new Label("Damage: "), 0, 1);
        ui.add(new Label("Inventory: "), 0, 2);
        ui.add(button, 4, 0);
        ui.add(healthLabel, 1, 0);
        ui.add(damageLabel, 1, 1);
        ui.add(inventory, 1, 2);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);

        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
        button.setOnAction(this::handeButtonClick);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    private void handeButtonClick(javafx.event.ActionEvent actionEvent) {
        map.getPlayer().checkPickUp();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1, 0);
                refresh();
                break;
        }
        map.getMobs().forEach(Actor::move);
    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        Cell playerCell = map.getPlayer().getCell();

//        for (int x = 0; x < map.getWidth(); x++) {
//            for (int y = 0; y < map.getHeight(); y++) {
        for (int x = playerCell.getX() - left; x <= playerCell.getX() + right; x++) {
            for (int y = playerCell.getY() - height; y <= playerCell.getY() + height; y++) {
                int canvaX = x - playerCell.getX() + left;
                int canvaY = y - playerCell.getY() + up;
                if (0 <= x && x < map.getWidth() && 0 <= y && y < map.getHeight()) {
                    Cell cell = map.getCell(x, y);

                    if (cell.getActor() != null) {
                        Tiles.drawTile(context, cell.getActor(), canvaX, canvaY);
                    } else if (cell.getItem() != null) {
                        Tiles.drawTile(context, cell.getItem(), canvaX, canvaY);
                    } else {
                        Tiles.drawTile(context, cell, canvaX, canvaY);
                    }
                } else {
                    Tiles.drawTile(context, new Cell(), canvaX, canvaY);
                }
            }
            healthLabel.setText("" + map.getPlayer().getHealth());
            damageLabel.setText("" + map.getPlayer().getDamage());
            inventory.setText("" + map.getPlayer().getItemNames());
            button.setFocusTraversable(false);
        }
    }
}