package com.akul.taskslist.repository.impl;

import com.akul.taskslist.domain.exception.ResourceMappingException;
import com.akul.taskslist.domain.task.Task;
import com.akul.taskslist.repository.DataSourceConfig;
import com.akul.taskslist.repository.TaskRepository;
import com.akul.taskslist.repository.mappers.TaskRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {

    private final DataSourceConfig dataSourceConfig;

    private final String FIND_BY_ID = """
            SELECT t.id as task_id,
                   t.title as task_title,
                   t.description as task_description,
                   t.expiration_date as task_expiration_date,
                   t.status as task_status
            FROM taskslist.tasks  t
            WHERE t.id = ?""";

    private final String FIND_ALL_BY_USER_ID = """
            SELECT t.id as task_id,
                   t.title as task_title,
                   t.description as task_description,
                   t.expiration_date as task_expiration_date,
                   t.status as task_status
            FROM taskslist.tasks  t
            JOIN taskslist.users_tasks ut ON t.id = ut.task_id
            WHERE ut.user_id = ?
            """;

    private final String ASSIGN = """
            INSERT INTO taskslist.users_tasks (task_id, user_id)
            VALUES (?,?)""";

    private final String DELETE = """
            DELETE FROM taskslist.tasks
            WHERE id = ?""";

    private final String UPDATE = """
            UPDATE taskslist.tasks
            SET title = ?,
                description = ?,
                expiration_date = ?,
                status = ?
            WHERE id =?""";

    private final String CREATE = """
            INSERT INTO  taskslist.tasks (title,description,expiration_date,status)
            VALUES (?,?,?,?)""";

    @Override
    public Optional<Task> findTaskByUserId(Long id) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_BY_ID);
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                return Optional.ofNullable(TaskRowMapper.mapRow(rs));
            }
        } catch (SQLException throwable) {
            throw new ResourceMappingException("Exception in procedure findUserById ");
        }

    }

    @Override
    public List<Task> findAllByUserId(Long userId) {

        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_BY_USER_ID);
            statement.setLong(1, userId);
            try (ResultSet rs = statement.executeQuery()) {
                return TaskRowMapper.mapRows(rs);
            }
        } catch (SQLException throwable) {
            throw new ResourceMappingException("Error in procedure findAllByUser ");
        }

    }

    @Override
    public void assignToUser(Long taskId, Long userId) {

        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(ASSIGN);
            statement.setLong(1, taskId);
            statement.setLong(2, userId);
            statement.executeUpdate();
        } catch (SQLException throwable) {
            throw new ResourceMappingException("Error in procedure assign to user ");
        }

    }

    @Override
    public void update(Task task) {

        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE);
            statement.setString(1, task.getTitle());
            if (task.getDescription() == null) {
                statement.setNull(2, Types.VARCHAR);
            } else {
                statement.setString(2, task.getDescription());
            }
            if (task.getExpirationDate() == null) {
                statement.setNull(3, Types.TIMESTAMP);
            } else {
                statement.setTimestamp(3, Timestamp.valueOf(task.getExpirationDate()));
            }
            statement.setString(4, task.getStatus().name());
            statement.setLong(5, task.getId());
            statement.executeUpdate();
        } catch (SQLException throwable) {
            throw new ResourceMappingException("Error in procedure update task for user ");
        }

    }

    @Override
    public void create(Task task) {

        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(CREATE, PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setString(1, task.getTitle());
            if (task.getDescription() == null) {
                statement.setNull(2, Types.VARCHAR);
            } else {
                statement.setString(2, task.getDescription());
            }
            if (task.getExpirationDate() == null) {
                statement.setNull(3, Types.TIMESTAMP);
            } else {
                statement.setTimestamp(3, Timestamp.valueOf(task.getExpirationDate()));
            }
            statement.setString(4, task.getStatus().name());
            statement.executeUpdate();
            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    task.setId(rs.getLong(1));
                }

            }

        } catch (SQLException throwable) {
            throw new ResourceMappingException("Error in procedure create task");
        }
    }

    @Override
    public void delete(Long id) {

        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE);
            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException throwable) {
            throw new ResourceMappingException("Error in procedure delete task");
        }
    }
}
