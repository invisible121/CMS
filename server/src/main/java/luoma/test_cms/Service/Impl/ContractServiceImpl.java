package luoma.test_cms.Service.Impl;

import luoma.test_cms.Dao.ContractDao;
import luoma.test_cms.Entity.Contract;
import luoma.test_cms.Service.ContractService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Service("ContractService")
public class ContractServiceImpl implements ContractService {
    @Resource
    ContractDao contractDao;

    @Override
    public List<Contract> selectContractByProcess(int user_id, int process) {
        return contractDao.selectContractByProcess(user_id, process);
    }

    @Override
    public List<Contract> selectAllContract() {
        return contractDao.selectAllContract();
    }

    @Override
    public List<Contract> selectContractByUserId(int user_id) {
        return contractDao.selectContractByUserId(user_id);
    }

    @Override
    public List<Contract> selectAllContractByProcess(int process) {
        return contractDao.selectAllContractByProcess(process);
    }

    @Override
    public int commitContract(int id, String msg) {
        return contractDao.commitContract(id, msg);
    }

    @Override
    public int updateState(int con_id, int id) {
        return contractDao.updateState(con_id, id);
    }

    @Override
    public int addState(int con_id, int id) {
        return contractDao.addState(con_id, id);
    }

    @Override
    public int addContract(Contract contract) {
        return contractDao.addContract(contract);
    }

    @Override
    public List<Contract> selectContractById(int id) {
        return contractDao.selectContractById(id);
    }
}
