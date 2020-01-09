package luoma.test_cms.Dao;

import luoma.test_cms.Entity.Permission;
import luoma.test_cms.Entity.User_entity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionDao {
    List<Permission> selectConPermissionUser(int con_id);

    int updatePermission(
            @Param(value = "con_id") int con_id,
            @Param(value = "user_id") int user_id,
            @Param(value = "permission") int permission
    );

    List<User_entity> selectUserWithPermission(
            @Param(value = "con_id")int con_id
    );

    int selectPermissionWithConIDAndUserID(
            @Param(value = "con_id") int con_id,
            @Param(value = "user_id") int user_id
    );
}
