package luoma.test_cms.Service;

import luoma.test_cms.Entity.Permission;
import luoma.test_cms.Entity.User_entity;

import java.util.List;

public interface PermissionService {

    List<Permission> selectConPermissionUser(int con_id);

    int updatePermission(int con_id, int user_id, int permission);

    List<User_entity> selectUserWithPermission(int con_id);

    int selectPermissionWithConIDAndUserID(int con_id, int user_id);
}
