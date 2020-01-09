package luoma.test_cms.Controller;

import luoma.test_cms.Entity.Login;
import luoma.test_cms.Entity.Rights;
import luoma.test_cms.Entity.UserManager_Entity;
import luoma.test_cms.Entity.User_entity;
import luoma.test_cms.Service.LoginService;
import luoma.test_cms.Service.RightsService;
import luoma.test_cms.Service.User_entityService;
import luoma.test_cms.common.Util.StringUtil;
import luoma.test_cms.response.Impl.UserResponse;
import luoma.test_cms.response.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class User_entityController {
    @Resource
    User_entityService user_entityService;
    @Resource
    RightsService rightsService;

    @Resource
    LoginService loginService;

    @RequestMapping("/selectAllUser")
    public List<User_entity> selectAllUser(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*"); //解决跨域问题
        List<User_entity> user_entities = user_entityService.getAllUser();
        return user_entities;
    }

    @RequestMapping("/login")
    public Response login(
            @RequestParam(value = "user", defaultValue = "") String userName,
            @RequestParam(value = "passwd", defaultValue = "") String passwd
    ) {
        return loginBO(userName, passwd);
    }

    @RequestMapping("/register")
    public Response addAccount(
            @RequestParam(value = "user", defaultValue = "") String userName,
            @RequestParam(value = "passwd", defaultValue = "") String passwd
    ) {
        return registerBO(userName, passwd);
    }

    @RequestMapping("/right")
    public Rights rights(
            @RequestParam(value = "id", defaultValue = "1") int id
    ) {
        return rightsService.selectRightsById(id);
    }

//    @RequestMapping("selectAllRights")
//    public List<Rights> selAllRights() {
//        return rightsService.selectAllRights();
//    }

    @RequestMapping("selectAllRight")
    public Response selectAllRight(
            @RequestParam(value = "token", defaultValue = "") String token
    ) {
        List<Login> logins = loginService.selectLoginByToken(token);

        if (logins.size() == 0) {
            return new UserResponse.Manager(-1, null);
        } else {

            System.out.println(logins.get(0).id);
            System.out.println(rightsService.selectRightsById(logins.get(0).id).getRol_id());
            if (rightsService.selectRightsById(logins.get(0).id).getRol_id() == 1) {
                List<Rights> rights = rightsService.selectAllRights();

                List<User_entity> user_entities = user_entityService.getAllUserNotDel();

                List<UserManager_Entity> userManager_entities = new ArrayList<UserManager_Entity>();

                for (int i = 0; i < user_entities.size(); i++) {

                    int rol_id = -1;

                    for (Rights rights1: rights) {
                        if (rights1.getId() == user_entities.get(i).getId()) {
                            rol_id = rights1.getRol_id();
                            break;
                        }
                    }
                    UserManager_Entity manager_entity = new UserManager_Entity(user_entities.get(i).getName(), rol_id);

                    userManager_entities.add(manager_entity);
                    System.out.print("");
                }

                return new UserResponse.Manager(0, userManager_entities);
            } else return new UserResponse.Manager(1, null);
        }
    }

    @RequestMapping("/addRootList")
    public Response addRootList(
            @RequestParam(value = "token", defaultValue = "") String token,
            @RequestParam(value = "list") List<String> list
    ) {



        List<Login> logins = loginService.selectLoginByToken(token);

        if (logins.size() == 0) {
            return new UserResponse.Manager(-1, null);
        } else {

            System.out.println(logins.get(0).id);
            System.out.println(rightsService.selectRightsById(logins.get(0).id).getRol_id());
            if (rightsService.selectRightsById(logins.get(0).id).getRol_id() == 1) {
                List<Rights> rights = rightsService.selectAllRights();
                List<User_entity> user_entities = user_entityService.getAllUserNotDel();
                List<UserManager_Entity> userManager_entities = new ArrayList<UserManager_Entity>();



                for (int i = 0; i < user_entities.size(); i++) {

                    int rol_id = -1;

                    for (Rights rights1: rights) {
                        if (rights1.getId() == user_entities.get(i).getId()) {
                            rol_id = rights1.getRol_id();
                            break;
                        }
                    }
                    UserManager_Entity manager_entity = new UserManager_Entity(user_entities.get(i).getName(), rol_id);

                    userManager_entities.add(manager_entity);
                }


//                for (int j = 0; j < user_entities.size(); j++) {
//                    UserManager_Entity manager_entity = new UserManager_Entity(user_entities.get(j).getName(), rights.get(user_entities.get(j).getId() - 1).getRol_id());
//                    userManager_entities.add(manager_entity);
//                }

                for (int k = 0; k < list.size(); k++) {
                    int keys = Integer.parseInt(list.get(k));

                    User_entity changeUser = user_entityService.getUserByName(userManager_entities.get(keys).name);

                    rightsService.updateRights(changeUser.getId(), 1);
                }

                return selectAllRight(token);
            } else return selectAllRight(token);
        }




    }

    @RequestMapping("/addUserList")
    public Response addUserList(
            @RequestParam(value = "token", defaultValue = "") String token,
            @RequestParam(value = "list") List<String> list
    ) {



        List<Login> logins = loginService.selectLoginByToken(token);

        if (logins.size() == 0) {
            return new UserResponse.Manager(-1, null);
        } else {

            System.out.println(logins.get(0).id);
            System.out.println(rightsService.selectRightsById(logins.get(0).id).getRol_id());
            if (rightsService.selectRightsById(logins.get(0).id).getRol_id() == 1) {
                List<Rights> rights = rightsService.selectAllRights();
                List<User_entity> user_entities = user_entityService.getAllUserNotDel();
                List<UserManager_Entity> userManager_entities = new ArrayList<UserManager_Entity>();


                for (int i = 0; i < user_entities.size(); i++) {

                    int rol_id = -1;

                    for (Rights rights1: rights) {
                        if (rights1.getId() == user_entities.get(i).getId()) {
                            rol_id = rights1.getRol_id();
                            break;
                        }
                    }
                    UserManager_Entity manager_entity = new UserManager_Entity(user_entities.get(i).getName(), rol_id);

                    userManager_entities.add(manager_entity);
                    System.out.print("");
                }



//                for (int j = 0; j < user_entities.size(); j++) {
//                    UserManager_Entity manager_entity = new UserManager_Entity(user_entities.get(j).getName(), rights.get(user_entities.get(j).getId() - 1).getRol_id());
//                    userManager_entities.add(manager_entity);
//                    System.out.println();
//                }

                for (int k = 0; k < list.size(); k++) {
                    int keys = Integer.parseInt(list.get(k));

                    User_entity changeUser = user_entityService.getUserByName(userManager_entities.get(keys).name);

                    rightsService.updateRights(changeUser.getId(), 0);
                }

                return selectAllRight(token);
            } else return selectAllRight(token);
        }




    }



    public Response loginBO(String name, String passwdMD5) {
        return getResponse(name, passwdMD5, user_entityService, rightsService);


    }

    public Response getResponse(String name, String passwdMD5, User_entityService user_entityService, RightsService rightsService) {
        User_entity user = user_entityService.getUserByName(name);

        if (user == null) {
            return new UserResponse.Login(1, 0, "");
        } else {
            String newPasswd = StringUtil.MD5(passwdMD5 + user.getSalt());

            if (newPasswd.compareTo(user.getPassword()) == 0) {

                String token = StringUtil.MD5(name + passwdMD5 + StringUtil.getRandString());

                Login login = new Login(user.getId(), token);
                loginService.addLogin(login);

                return new UserResponse.Login(
                        0,
                        rightsService.selectRightsById(user.getId()).getRol_id(),
                        token);
            } else {
                return new UserResponse.Login(
                        -1,
                        0,
                        ""
                );
            }
        }
    }

    public Response registerBO(String userName, String passwdMD5) {
        User_entity user = user_entityService.getUserByName(userName);

        if (user == null) {
            //注册

            int userNum = user_entityService.getAllUser().size();

            String salt = StringUtil.getRandString();
            String newPasswd = StringUtil.MD5(passwdMD5 + salt);

            User_entity newUser = new User_entity(userNum + 1, userName, newPasswd, salt, 0);

            Rights rights = new Rights(0, newUser.getId());
            user_entityService.addUser(newUser);
            rightsService.addRights(rights);
            return new UserResponse.Register(0);
        } else {
            return new UserResponse.Register(1);
        }
    }

}
