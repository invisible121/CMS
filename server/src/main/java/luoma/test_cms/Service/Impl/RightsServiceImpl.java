package luoma.test_cms.Service.Impl;

import luoma.test_cms.Dao.RightsDao;
import luoma.test_cms.Entity.Rights;
import luoma.test_cms.Service.RightsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("RightsService")
public class RightsServiceImpl implements RightsService {
    @Resource
    RightsDao rightsDao;
    @Override
    public Rights selectRightsById(int id) {
        return this.rightsDao.selectRightsById(id);
    }

    @Override
    public int updateRights(int id, int rol_id) {
        if (rol_id == 0) {
            rightsDao.updateUserById(id);
        }

        if (rol_id == 1) {
            rightsDao.updateRootById(id);
        }

        return 0;
    }

    @Override
    public List<Rights> selectAllRights() {
        return rightsDao.selectAllRights();
    }

    @Override
    public int addRights(Rights rights) {

        return this.rightsDao.addRights(rights);
    }
}
