package com.akul.taskslist.web.dto.user;

import com.akul.taskslist.domain.task.Task;
import com.akul.taskslist.domain.user.Role;
import com.akul.taskslist.web.validation.OnCreate;
import com.akul.taskslist.web.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;
import java.util.Set;


@Data
public class UserDto {

    @NotNull(message = "Id must be not null.", groups = OnUpdate.class)
    private Long id;

    @NotNull(message = "Name must be not null.", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255, message = "Name length must be smaller then 255.", groups = {OnUpdate.class, OnCreate.class})
    private String name;

    @NotNull(message = "Username must be not null.", groups = {OnUpdate.class, OnCreate.class})
    @Length(max = 255, message = "Username length must be smaller then 255.", groups = {OnUpdate.class, OnCreate.class})
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password must be not null.", groups = {OnUpdate.class, OnCreate.class})
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password conformation must be not null.", groups = {OnUpdate.class})
    private String passwordConfirmation;
    private Set<Role> roles;
    private List<Task> tasks;

}
