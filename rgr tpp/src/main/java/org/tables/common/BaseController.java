package org.tables.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tables.user.UserService;
import org.util.StringUtil;

import java.util.List;

public abstract class BaseController<T, S extends BaseService<T>> {

    protected S service;
    protected UserService userService;

    public BaseController(S service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping
    public String getEntities() {
        return service.show();
    }

    @PutMapping
    public String createEntity(@RequestParam("email") String email, @RequestParam("columnsVal") String columnsVal) {
        if (!userService.verifyAuthority(email, "create")) {
            return "You don't have the authority to create entity";
        }
        List columns = StringUtil.columnsMatcher(columnsVal);
        List<String> columnNames = (List<String>) columns.get(0);
        List<String> columnValues = (List<String>) columns.get(1);

        service.insert(columnNames, columnValues);

        return "Entity created successfully";
    }

    @PatchMapping
    public String updateEntity(@RequestParam("email") String email,
                               @RequestParam("columnsVal") String columnsVal) {
        if (!userService.verifyAuthority(email, "update")) {
            return "You don't have the authority to update entity";
        }
        List columns = StringUtil.columnsMatcher(columnsVal);
        List<String> columnNames = (List<String>) columns.get(0);
        List<String> columnValues = (List<String>) columns.get(1);

        service.update(columnNames, columnValues);

        return "Entity updated successfully";
    }

    @DeleteMapping
    public String deleteEntity(@RequestParam("email") String email,
                               @RequestParam("columnsVal") String columnsVal) {
        if (!userService.verifyAuthority(email, "delete")) {
            return "You don't have the authority to delete entity";
        }
        List columns = StringUtil.columnsMatcher(columnsVal);
        List<String> columnNames = (List<String>) columns.get(0);
        List<String> columnValues = (List<String>) columns.get(1);

        service.delete(columnNames, columnValues);

        return "Entity deleted successfully";
    }
}