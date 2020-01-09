package luoma.test_cms.Dao;

import luoma.test_cms.Entity.UserManager_Entity;
import luoma.test_cms.Entity.User_entity;

import java.util.List;

public interface User_entityDao {

    public List<User_entity> getAllUser();

    public List<User_entity> getUserById(int id);

    public User_entity getUserByName(String name);

    public List<User_entity> getUserByDel(int del);

    public List<UserManager_Entity> selectUserManager();

    int addUser(User_entity user_entity);

    public List<User_entity> getAllUserNotDel();
}
