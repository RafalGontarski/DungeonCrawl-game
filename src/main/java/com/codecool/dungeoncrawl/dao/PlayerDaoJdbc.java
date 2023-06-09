package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.PlayerModel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDaoJdbc implements PlayerDao {
    private DataSource dataSource;

    public PlayerDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(PlayerModel player) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "" +
                    "INSERT INTO player (player_name, hp, x, y) " +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, player.getPlayerName());
            statement.setInt(2, player.getHp());
            statement.setInt(3, player.getX());
            statement.setInt(4, player.getY());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            player.setId(resultSet.getInt(1));
            // check player ID
            int id = player.getId();
            System.out.println(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(PlayerModel player) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "" +
                    "UPDATE player " +
                    "SET player_name = ?, " +
                    "hp = ?, " +
                    "x = ?, " +
                    "y = ?" +
                    "WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, player.getPlayerName());
            statement.setInt(2, player.getHp());
            statement.setInt(3, player.getX());
            statement.setInt(4, player.getY());
            statement.setInt(5, player.getId());

            System.out.println(player.getId());
            statement.executeUpdate();
        }   catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PlayerModel get(int id) {
        try(Connection conn = dataSource.getConnection()) {
            String sql = "" +
                    "SELECT " +
                    "player_name, " +
                    "hp, " +
                    "x " +
                    "y " +
                    "FROM player " +
                    "WHERE id = ?";

            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if(!rs.next()) {
                return null;
            }
            PlayerModel playerModel = new PlayerModel(
                    rs.getString(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getInt(4));
            playerModel.setId(id);
            return playerModel;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PlayerModel> getAll() {
        try(Connection conn = dataSource.getConnection()) {
            String sql = "" +
                    "SELECT " +
                    "id, " +
                    "player_name, " +
                    "hp, " +
                    "x, " +
                    "y " +
                    "FROM player";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<PlayerModel> result = new ArrayList<>();
            while(rs.next()) {
                PlayerModel playerModel = new PlayerModel(
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5));
                playerModel.setId(rs.getInt(1));
                result.add(playerModel);

            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while getting all players", e);
        }
        return null;
    }
}
