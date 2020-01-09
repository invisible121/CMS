package luoma.test_cms.Service.Impl;

import luoma.test_cms.Dao.LoginDao;
import luoma.test_cms.Entity.Login;
import luoma.test_cms.Service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.ws.ServiceMode;
import java.util.List;

@Service("LoginService")
public class LoginServiceImpl implements LoginService {
    @Resource
    LoginDao loginDao;


    @Override
    public int selectLoginUserByToken(String token) {
        return loginDao.selectLoginUserByToken(token);
    }

    @Override
    public List<Login> selectLoginByToken(String token) {
        return loginDao.selectLoginByToken(token);
    }

    @Override
    public List<Login> selectLoginById(int id) {
        return loginDao.selectLoginById(id);
    }

    @Override
    public int addLogin(Login login) {
        List<Login> logins = loginDao.selectLoginById(login.id);

        if (logins.size() != 0) {
            return loginDao.updateLogin(login);
        } else return loginDao.addLogin(login);
    }
}
