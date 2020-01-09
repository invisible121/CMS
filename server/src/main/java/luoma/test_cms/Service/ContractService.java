package luoma.test_cms.Service;

import luoma.test_cms.Entity.Contract;

import java.util.List;

public interface ContractService {
    public List<Contract> selectAllContract();

    public List<Contract> selectContractById(int id);

    public List<Contract> selectContractByUserId(int user_id);

    public List<Contract> selectContractByProcess(int user_id, int process);

    List<Contract> selectAllContractByProcess(int process);

    int addContract(Contract contract);

    int addState(int con_id, int id);

    int updateState(int con_id, int id);

    int commitContract(int id, String msg);
}
