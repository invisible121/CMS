package luoma.test_cms.Service;

import luoma.test_cms.Entity.Rights;

import java.util.List;

public interface RightsService {

    public Rights selectRightsById(int id);

    public int addRights(Rights rights);

    public List<Rights> selectAllRights();

    public int updateRights(int id, int rol_id);
}
