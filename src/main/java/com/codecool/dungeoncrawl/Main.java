package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.dao.PlayerDaoJdbc;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.text.Text;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends Application {
    GameMap map;
    Label playerName = new Label();
    GameState gameState;
    Player player;
    PlayerModel playerModel;
    GameDatabaseManager gameDatabaseManager;
    TextField textField = new TextField();
    List<GameMap> maps = new ArrayList<>();
    List<String> levels = Arrays.asList("/map.txt","/map2.txt","/map3.txt");
    int level;
    int left = 13;
    int right = 13;
    int up = 10;
    int down = 10;
    int height = 10;
    private Window parentStage;
    Canvas canvas = new Canvas(
            25 * Tiles.TILE_WIDTH,
            20 * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label damageLabel = new Label();
    Label inventory = new Label();
    Label fight = new Label();

    public Main() {
        maps.add(MapLoader.loadMap(this, levels.get(level)));
        this.map = maps.get(level);
    }
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        initialModalWindow(primaryStage);
    }
    private void initializeGame(Stage primaryStage) {
        parentStage = primaryStage;
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));


        ui.add(new Label("Name: "), 0, 0);
        ui.add(new Label("Health: "), 0, 1);
        ui.add(new Label("Damage: "), 0, 2);
        ui.add(new Label("Inventory: "), 0, 3);
        ui.add(new Label("Fight Area: "), 0, 14);
        ui.add(playerName, 1, 0);
        ui.add(healthLabel, 1, 1);
        ui.add(damageLabel, 1, 2);
        ui.add(inventory, 1, 3);
        ui.add(fight, 1, 15);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);

        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }
    /**
     * When the user run game,
     * a modal window pops up with a text input field (labelled Name) and two buttons,
     * Start Game and Exit.
     */
    void initialModalWindow(Stage primaryStage) {
        Stage dialog = new Stage();
        HBox root = new HBox();
        Scene scene = new Scene(root, 400, 100);
        dialog.setScene(scene);

        Label labelfirst= new Label("Name:    ");
        Button btnStart = new Button("Start Game");
        Button btnExit = new Button("Exit");

        textField.setText("Name");
        btnStart.setDefaultButton(true);
        btnExit.setCancelButton(true);

        btnStart.setOnAction(e -> {
            startGame(textField.getText());
            initializeGame(primaryStage);
            dialog.close();
        });
        btnExit.setOnAction(e -> {
            exitGame();
            dialog.close();});

        root.getChildren().addAll(labelfirst, textField, btnStart, btnExit, playerName);
        root.setAlignment(Pos.CENTER);

        dialog.setScene(scene);
        dialog.initOwner(parentStage);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Dungeon Crawl");
        dialog.showAndWait();
    }
    /**
     * When the user presses Ctrl+S,
     * a modal window pops up with a text input field (labelled Name) and two buttons,
     * Save and Cancel.
     */
    public void saveModalWindow() {
        Stage dialog = new Stage();
        HBox root = new HBox();
        Scene scene = new Scene(root, 400, 200);
        dialog.setScene(scene);

        Button btnSave = new Button("Save");
        Button btnCancel = new Button("Cancel");

        textField.setText("character name");
        btnSave.setDefaultButton(true);
        btnCancel.setCancelButton(true);


        btnSave.setOnAction(e -> {
            saveGame(playerModel);
            dialog.close();
        });
        btnCancel.setOnAction(e -> {
            cancelGame();
            dialog.close();
        });

        root.getChildren().addAll(textField, btnSave, btnCancel);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        dialog.setScene(scene);
        dialog.initOwner(parentStage);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Save Game");
        dialog.showAndWait();
    }
    /**
     * When the user presses Esc,
     * a modal window pops up with a text input field (labelled Name) and two buttons,
     * Exit and Cancel.
     */
    private void exitModalWindow() {
        Stage dialog = new Stage();
        HBox root = new HBox();
        Scene scene = new Scene(root, 300, 100);
        dialog.setScene(scene);

        Button btnExit = new Button("Exit");
        Button btnCancel = new Button("Cancel");

        btnExit.setCancelButton(true);
        btnCancel.setDefaultButton(true);

        btnExit.setOnAction(e -> {
            exitGame();
            dialog.close();
            Platform.exit();
        });


        btnCancel.setOnAction(e -> {
            cancelGame();
            dialog.close();
        });


        root.getChildren().addAll(btnExit, btnCancel);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        dialog.setScene(scene);
        dialog.initOwner(parentStage);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Dungeon Crawl");
        dialog.showAndWait();
    }
    /**
     * When the user health is under zero,
     * a modal window pops up with a text input field (labelled Name) and two buttons,
     * Exit and Try Again.
     */
    public void deadModalWindow(Stage primaryStage) {
        Stage dialog = new Stage();
        HBox root = new HBox();
        Scene scene = new Scene(root, 350, 100);
        dialog.setScene(scene);

        Button btnExit = new Button("Exit");
        Button btnStartAgain = new Button("Try Again");

        btnExit.setCancelButton(true);
        btnStartAgain.setDefaultButton(true);

        btnExit.setOnAction(e -> {
            exitGame();
            dialog.close();
            Platform.exit();
        });
        btnStartAgain.setOnAction(e -> {
            startGame(textField.getText());
            dialog.close();
        });

        root.getChildren().addAll(btnExit, btnStartAgain);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        dialog.setScene(scene);
        dialog.initOwner(parentStage);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Dungeon Crawl");
        dialog.showAndWait();
    }
    private void startGame(String name) {
        playerName.setText(name);

        player = new Player(
                playerName.getText(),
                map.getPlayer().getCell(),
                map.getPlayer().getHealth(),
                map.getPlayer().getDamage());

        playerModel = new PlayerModel(player);

        playerModel.setPlayerName(name);


        gameDatabaseManager = new GameDatabaseManager();

        try {
            gameDatabaseManager.setup();
            gameDatabaseManager.savePlayer(playerModel);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    private void no() {
        System.out.println("No");
    }
    private void yes() {
        System.out.println("Yes");
    }
    private void exitGame(){
        System.out.println("Exit");
    }
    private void saveGame(PlayerModel playerModel){

        playerName.setText(textField.getText());
        playerModel.setPlayerName(playerName.getText());
        gameDatabaseManager = new GameDatabaseManager();

        try {
            gameDatabaseManager.setup();
            gameDatabaseManager.updatePlayer(playerModel);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        Stage dialog = new Stage();
//        HBox root = new HBox();
        GridPane root = new GridPane();
        Scene scene = new Scene(root, 400, 100);
        dialog.setScene(scene);

        Button btnYes = new Button("Yes");
        Button btnNo = new Button("No");

        btnYes.setDefaultButton(true);
        btnNo.setCancelButton(true);


        btnYes.setOnAction(e -> {
            yes();
            dialog.close();
        });
        btnNo.setOnAction(e -> {
            no();
            dialog.close();
        });
        root.setAlignment(Pos.BASELINE_CENTER);

        Text text = new Text("Would you like to overwrite the already existing state?");
        root.add(text, 0, 4);
        root.add(btnYes, 0,2);
        root.add(btnNo, 1, 2);
//        root.getChildren().addAll(text, btnYes, btnNo);
//        root.setSpacing(10);
        dialog.setScene(scene);
        dialog.initOwner(parentStage);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Save Game");

        dialog.showAndWait();

        System.out.println("Save");
    }
    private void cancelGame(){
        System.out.println("Cancel");
    }
    private void handeButtonClick(javafx.event.ActionEvent actionEvent) {
        map.getPlayer().checkPickUp();
    }
    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP -> {
                map.getPlayer().move(0, -1);
                refresh();
            }
            case DOWN -> {
                map.getPlayer().move(0, 1);
                refresh();
            }
            case LEFT -> {
                map.getPlayer().move(-1, 0);
                refresh();
            }
            case RIGHT -> {
                map.getPlayer().move(1, 0);
                refresh();
            }
            case S -> saveModalWindow();
            case ESCAPE -> exitModalWindow();
            case SPACE -> {
                map.getPlayer().checkPickUp();
                refresh();
            }
        }
//        map.getMobs().forEach(Actor::move);
    }
    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        Cell playerCell = map.getPlayer().getCell();

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

        }
    }
    public void addMap(GameMap map){
        maps.add(map);
    }
    public void upperLevel(){
        level++;
        if(level >= maps.size()){
            GameMap nowaMapa = MapLoader.loadMap(this,levels.get(level));
            addMap(nowaMapa);
            System.out.println(level);
            System.out.println(maps.size());
        }
        this.map = maps.get(level);
        Player player = maps.get(level-1).getPlayer();
        map.getPlayer().setAttribute(player.getInventory(),player.getHealth(), player.getDamage());
    }
    public void lowerLevel(){
        level--;
        this.map = maps.get(level);
        Player player = maps.get(level+1).getPlayer();
        map.getPlayer().setAttribute(player.getInventory(),player.getHealth(), player.getDamage());
    }
}