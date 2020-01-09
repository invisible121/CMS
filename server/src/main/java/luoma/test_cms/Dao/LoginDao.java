package luoma.test_cms.Dao;

import luoma.test_cms.Entity.Login;

import java.util.List;

public interface LoginDao {

    int addLogin(Login login);

    int updateLogin(Login login);

    List<Login> selectLoginByToken(String token);

    List<Login> selectLoginById(int id);

    int selectLoginUserByToken(String token);
}
