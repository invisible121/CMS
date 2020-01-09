package luoma.test_cms.Service.Impl;

import luoma.test_cms.Dao.PermissionDao;
import luoma.test_cms.Entity.Permission;
import luoma.test_cms.Entity.User_entity;
import luoma.test_cms.Service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("PermissionService")
public class PermissionServiceImpl implements PermissionService {

    @Resource
    PermissionDao permissionDao;

    @Override
    public int selectPermissionWithConIDAndUserID(int con_id, int user_id) {
        return permissionDao.selectPermissionWithConIDAndUserID(con_id, user_id);
    }

    @Override
    public List<User_entity> selectUserWithPermission(int con_id) {
        return permissionDao.selectUserWithPermission(con_id);
    }

    @Override
    public int updatePermission(int con_id, int user_id, int permission) {
        return permissionDao.updatePermission(con_id, user_id, permission);
    }

    @Override
    public List<Permission> selectConPermissionUser(int con_id) {
        return permissionDao.selectConPermissionUser(con_id);
    }
}
