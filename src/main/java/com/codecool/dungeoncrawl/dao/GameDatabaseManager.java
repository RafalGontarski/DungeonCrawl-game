package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.model.GameState;
import com.codecool.dungeoncrawl.model.PlayerModel;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;


/**
 * The application uses a PostgreSQL database with the schema in schema_ddl.sql
 */
public class GameDatabaseManager {
    /**
     * The application uses a PostgreSQL database with the schema in schema_ddl.sql
     */
    private PlayerDaoJdbc playerDao;
    private GameStateDaoJdbc gameStateDao;

    public void setup() throws SQLException {
        DataSource dataSource = connect();
        playerDao = new PlayerDaoJdbc(dataSource);
        gameStateDao = new GameStateDaoJdbc(dataSource, playerDao);
    }

    public void savePlayer(PlayerModel player) {
        playerDao.add(player);
    }

    public void updatePlayer(PlayerModel player) {
        playerDao.update(player);
    }

    public void getPlayer(int id) {
        playerDao.get(id);
    }

    public void getAllPlayers() {
        playerDao.getAll();
    }


    public void saveState(GameState state) {
        gameStateDao.add(state);
    }

    public void updateState(GameState state) {
        gameStateDao.update(state);
    }

    public void getState(int id) {
        gameStateDao.get(id);
    }

    public void getAllStates() {
        gameStateDao.getAll();
    }


    public DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String dbName = "dungeon_crawl2";
        String user = "codecool";
        String password = "codecool1";

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }
}
