package luoma.test_cms.Dao;

import luoma.test_cms.Entity.Contract;
import luoma.test_cms.common.Util.StringUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ContractDao {

    List<Contract> selectAllContract();

    List<Contract> selectContractById(int id);

    List<Contract> selectContractByUserId(int user_id);

    List<Contract> selectContractByProcess(
            @Param(value = "user_id") int user_id,
            @Param(value = "process") int process);

    List<Contract> selectAllContractByProcess(
            @Param(value = "process") int process);

    int addContract(Contract contract);

    int addState(
            @Param(value = "con_id") int con_id,
            @Param(value = "id") int id
    );

    int updateState(
            @Param(value = "con_id") int con_id,
            @Param(value = "id") int id
    );

    int commitContract(
            @RequestParam(value = "id") int id,
            @RequestParam(value = "msg") String msg);
}
