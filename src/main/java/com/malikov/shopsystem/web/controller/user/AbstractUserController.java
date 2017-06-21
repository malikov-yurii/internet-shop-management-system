package com.malikov.shopsystem.web.controller.user;

import com.malikov.shopsystem.Profiles;
import com.malikov.shopsystem.model.User;
import com.malikov.shopsystem.service.UserService;
import com.malikov.shopsystem.to.UserTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractUserController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    private boolean systemUserForbiddenModification;

    @Autowired
    public void setEnvironment(Environment environment) {
        systemUserForbiddenModification = Arrays.asList(environment.getActiveProfiles()).contains(Profiles.HEROKU);
    }

    @Autowired
    private UserService service;

    public List<User> getAll() {
        log.info("getAllDtos");
        return service.getAll();
    }

    public User get(Long id) {
        log.info("get " + id);
        return service.get(id);
    }

    public User create(User user) {
        user.setId(null);
        log.info("create " + user);
        return service.save(user);
    }

    public void delete(Long id) {
        log.info("delete " + id);
        service.delete(id);
    }

    public void update(User user, Long id) {
        user.setId(id);
        log.info("update " + user);
        service.update(user);
    }

    public void update(UserTo userTo) {
        log.info("update " + userTo);
        service.update(userTo);
    }

    public User getLogin(String login) {
        log.info("getByEmail " + login);
        return service.getByLogin(login);
    }

}
