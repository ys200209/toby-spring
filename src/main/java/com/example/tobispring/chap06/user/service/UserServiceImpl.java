package com.example.tobispring.chap06.user.service;

import com.example.tobispring.chap06.user.dao.UserDao;
import com.example.tobispring.chap06.user.domain.Level;
import com.example.tobispring.chap06.user.domain.User;
import java.util.List;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class UserServiceImpl implements UserService {
    UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void add(User user) {
        if (user.getLevel() == null) {
            user.setLevel(Level.BASIC);
        }
        userDao.add(user);
    }

    public void upgradeLevels() {
        List<User> users = userDao.getAll();
        for (User user : users) {
            upgradeLevel(user);
        }
    }

    protected void upgradeLevel(User user) {
        if (canUpgradeLevel(user)) {
            upgradeLevelAndUpdateDao(user);
        }
    }

    private boolean canUpgradeLevel(User user) {
        return user.canUpgradeLevel();
    }

    private void upgradeLevelAndUpdateDao(User user) {
        user.upgradeLevel();
        userDao.update(user);
    }
}
