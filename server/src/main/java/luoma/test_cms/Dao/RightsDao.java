package luoma.test_cms.Dao;

import luoma.test_cms.Entity.Rights;

import java.util.List;

public interface RightsDao {

    public Rights selectRightsById(int id);

    public List<Rights> selectAllRights();

    public int addRights(Rights rights);

    public int updateRootById(int id);

    public int updateUserById(int id);
}
