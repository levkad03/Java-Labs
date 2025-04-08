package org.db_lab.repository;

import org.db_lab.mapper.EntityMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDbRepository<ENTITY, ID> implements DbRepository<ENTITY, ID> {

    private final Connection connection;
    private final String tableName;
    private final EntityMapper<ENTITY, ID> entityMapper;

    public JdbcDbRepository(Connection connection, String tableName, EntityMapper<ENTITY, ID> entityMapper) {
        this.connection = connection;
        this.tableName = tableName;
        this.entityMapper = entityMapper;
    }

    @Override
    public ENTITY findById(ID id) {
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return entityMapper.fromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ENTITY> findAll() {
        List<ENTITY> list = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName;

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                list.add(entityMapper.fromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public ENTITY save(ENTITY entity) {
        try {
            PreparedStatement stmt = entityMapper.createInsertStatement(connection, tableName, entity);
            stmt.executeUpdate();
            return entity;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ENTITY update(ENTITY entity) {
        try {
            PreparedStatement stmt = entityMapper.createUpdateStatement(connection, tableName, entity);
            stmt.executeUpdate();
            return entity;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteById(ID id) {
        String sql = "DELETE FROM " + tableName + " WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
