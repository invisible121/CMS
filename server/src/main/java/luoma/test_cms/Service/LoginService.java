package luoma.test_cms.Service;

import luoma.test_cms.Entity.Login;
import sun.rmi.runtime.Log;

import java.util.List;

public interface LoginService {

    public List<Login> selectLoginByToken(String token);

    public List<Login> selectLoginById(int id);

    public int addLogin(Login login);

    public int selectLoginUserByToken(String token);
}
