package luoma.test_cms.Service.Impl;

import luoma.test_cms.Dao.User_entityDao;
import luoma.test_cms.Entity.User_entity;
import luoma.test_cms.Service.User_entityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("User_entityService")
public class User_entityServiceImpl implements User_entityService {
    @Resource
    User_entityDao user_entityDao;

    @Override
    public List<User_entity> getAllUser() {
        return this.user_entityDao.getAllUser();
    }

    @Override
    public List<User_entity> getUserById(int id) {
        return this.user_entityDao.getUserById(id);
    }

    @Override
    public boolean addUser(User_entity user_entity) {
        return this.user_entityDao.addUser(user_entity) == 1;
    }

    @Override
    public List<User_entity> getAllUserNotDel() {
        return this.user_entityDao.getAllUserNotDel();
    }

    @Override
    public User_entity getUserByName(String name) {
        return this.user_entityDao.getUserByName(name);
    }

    @Override
    public List<User_entity> getUserByDel(int del) {
        return this.user_entityDao.getUserByDel(del);
    }
}
