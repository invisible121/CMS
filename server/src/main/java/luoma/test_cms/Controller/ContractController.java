package luoma.test_cms.Controller;

import luoma.test_cms.Entity.Contract;


import luoma.test_cms.Entity.Customer;
import luoma.test_cms.Entity.Login;
import luoma.test_cms.Entity.Permission;
import luoma.test_cms.Service.ContractService;
import luoma.test_cms.Service.CustomerService;
import luoma.test_cms.Service.LoginService;
import luoma.test_cms.Service.PermissionService;
import luoma.test_cms.common.Util.StringUtil;
import luoma.test_cms.response.Impl.ContractResponse;
import luoma.test_cms.response.Impl.PermissionResponse;
import luoma.test_cms.response.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/contract")
public class ContractController {
    @Resource
    ContractService contractService;

    @Resource
    LoginService loginService;

    @Resource
    PermissionService permissionService;

    @Resource
    CustomerService customerService;

    @RequestMapping("/selectAllContract")
    public Response selectAllContract(HttpServletResponse response) {
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        return new ContractResponse.GetContract(0, contractService.selectAllContract());
    }

    @RequestMapping("/selectContract")
    public Response selectUncommittedContract(
            @RequestParam(value = "token", defaultValue = "") String token
    ) {
        List<Login> logins = loginService.selectLoginByToken(token);

        if (logins.size() == 0) {
            return new ContractResponse.GetContract(-1, null);
        } else {
            Login login = logins.get(0);

            List<Contract> contracts = contractService.selectContractByUserId(login.id);

            return new ContractResponse.GetContract(0, contracts);
        }
    }

    @RequestMapping("/selectContractByType")
    public Response selectContractByType(
            @RequestParam(value = "token", defaultValue = "") String token,
            @RequestParam(value = "type", defaultValue = "") String type
    ) {
        List<Login> logins = loginService.selectLoginByToken(token);

        if (logins.size() == 0) {
            return new ContractResponse.GetContract(-1, null);
        } else {
            Login login = logins.get(0);
            switch (type) {
                case "uncommitted":
                    return new ContractResponse.GetContract(0, contractService.selectContractByProcess(login.id, 3));


                case "committed":
                    return new ContractResponse.GetContract(0, contractService.selectContractByProcess(login.id, 2));

                case "counterSign":
                    List<Contract> contracts = contractService.selectAllContractByProcess(1);

                    List<Contract> newContract = new ArrayList<>();

                    for (Contract contract : contracts) {
                        int permission = permissionService.selectPermissionWithConIDAndUserID(contract.getId(), login.id);
                        if (permission == 1 || permission == 3 ||
                                permission == 5 || permission == 7) {
                            newContract.add(contract);
                        }
                    }

                    return new ContractResponse.GetContract(0, newContract);


                case "iscounterSign":
//                    List<Contract> iscontracts = contractService.selectAllContractByProcess(1);
                    List<Contract> iscontracts1 = contractService.selectAllContractByProcess(3);
                    List<Contract> iscontracts2 = contractService.selectAllContractByProcess(4);
                    List<Contract> iscontracts3 = contractService.selectAllContractByProcess(5);
                    List<Contract> iscontracts4 = contractService.selectAllContractByProcess(6);


                    List<Contract> isnewContract = new ArrayList<>();

//                    for (Contract contract : iscontracts) {
//                        int permission = permissionService.selectPermissionWithConIDAndUserID(contract.getId(), login.id);
//                        if (permission == 1 || permission == 3 ||
//                                permission == 5 || permission == 7) {
//                            isnewContract.add(contract);
//                        }
//                    }

                    for (Contract contract : iscontracts1) {
                        int permission = permissionService.selectPermissionWithConIDAndUserID(contract.getId(), login.id);
                        if (permission == 1 || permission == 3 ||
                                permission == 5 || permission == 7) {
                            isnewContract.add(contract);
                        }
                    }

                    for (Contract contract : iscontracts2) {
                        int permission = permissionService.selectPermissionWithConIDAndUserID(contract.getId(), login.id);
                        if (permission == 1 || permission == 3 ||
                                permission == 5 || permission == 7) {
                            isnewContract.add(contract);
                        }
                    }

                    for (Contract contract : iscontracts3) {
                        int permission = permissionService.selectPermissionWithConIDAndUserID(contract.getId(), login.id);
                        if (permission == 1 || permission == 3 ||
                                permission == 5 || permission == 7) {
                            isnewContract.add(contract);
                        }
                    }

                    for (Contract contract : iscontracts4) {
                        int permission = permissionService.selectPermissionWithConIDAndUserID(contract.getId(), login.id);
                        if (permission == 1 || permission == 3 ||
                                permission == 5 || permission == 7) {
                            isnewContract.add(contract);
                        }
                    }

                    return new ContractResponse.GetContract(0, isnewContract);


                case "watchContract":
                    List<Contract> contracts1 = contractService.selectAllContractByProcess(4);

                    List<Contract> newContract1 = new ArrayList<>();

                    for (Contract contract : contracts1) {
                        int permission = permissionService.selectPermissionWithConIDAndUserID(contract.getId(), login.id);
                        if (permission == 2 || permission == 3 ||
                                permission == 6 || permission == 7) {
                            newContract1.add(contract);
                        }
                    }

                    return new ContractResponse.GetContract(0, newContract1);


                case "iswatchContract":
                    List<Contract> watcontracts1 = contractService.selectAllContractByProcess(5);
                    List<Contract> watcontracts2 = contractService.selectAllContractByProcess(6);

                    List<Contract> watnewContract1 = new ArrayList<>();

                    for (Contract contract : watcontracts1) {
                        int permission = permissionService.selectPermissionWithConIDAndUserID(contract.getId(), login.id);
                        if (permission == 2 || permission == 3 ||
                                permission == 6 || permission == 7) {
                            watnewContract1.add(contract);
                        }
                    }

                    for (Contract contract : watcontracts2) {
                        int permission = permissionService.selectPermissionWithConIDAndUserID(contract.getId(), login.id);
                        if (permission == 2 || permission == 3 ||
                                permission == 6 || permission == 7) {
                            watnewContract1.add(contract);
                        }
                    }

                    return new ContractResponse.GetContract(0, watnewContract1);


                case "sign":

                    List<Contract> contracts2 = contractService.selectAllContractByProcess(5);

                    List<Contract> newContract2 = new ArrayList<>();

                    for (Contract contract : contracts2) {
                        int permission = permissionService.selectPermissionWithConIDAndUserID(contract.getId(), login.id);
                        if (permission == 4 || permission == 5 ||
                                permission == 6 || permission == 7) {
                            newContract2.add(contract);
                        }
                    }

                    return new ContractResponse.GetContract(0, newContract2);

                case "issign":

                    List<Contract> signcontracts2 = contractService.selectAllContractByProcess(6);

                    List<Contract> signnewContract2 = new ArrayList<>();

                    for (Contract contract : signcontracts2) {
                        int permission = permissionService.selectPermissionWithConIDAndUserID(contract.getId(), login.id);
                        if (permission == 4 || permission == 5 ||
                                permission == 6 || permission == 7) {
                            signnewContract2.add(contract);
                        }
                    }

                    return new ContractResponse.GetContract(0, signnewContract2);


                default:
                    return new ContractResponse.GetContract(-1, null);
            }
        }
    }


    @RequestMapping("/selContract")
    public Response selContract(
            @RequestParam(value = "token", defaultValue = "") String token,
            @RequestParam(value = "id", defaultValue = "") int id
    ) {
        return new ContractResponse.GetContract(0, contractService.selectContractById(id));
    }


    @RequestMapping("/selectAllContractByProcess")
    public Response selectAllContractByProcess(
            @RequestParam(value = "token", defaultValue = "") String token,
            @RequestParam(value = "process", defaultValue = "") int process
    ) {
        List<Login> logins = loginService.selectLoginByToken(token);

        if (logins.size() == 0) {
            return new ContractResponse.GetContract(-1, null);
        } else {
            return new ContractResponse.GetContract(0, contractService.selectAllContractByProcess(process));
        }
    }

    @RequestMapping("/addContract")
    public Response addContract(
            @RequestParam(value = "token", defaultValue = "") String token,
            @RequestParam(value = "contract_name", defaultValue = "") String contract_name,
            @RequestParam(value = "customer_id", defaultValue = "") int id,
            @RequestParam(value = "dateFrom", defaultValue = "") String dateFrom,
            @RequestParam(value = "dateTo", defaultValue = "") String dateTo,
            @RequestParam(value = "content", defaultValue = "") String content
    ) {
        List<Login> logins = loginService.selectLoginByToken(token);

        if (logins.size() == 0) {
            return new ContractResponse.GetContract(-1, null);
        } else {
            String num = StringUtil.getRandString();

            List<Customer> customers = customerService.getCustomerById(id);
            contractService.addContract(
                    new Contract(0, id, num, contract_name,
                            logins.get(0).id, customers.get(0).getName(),
                            content, Date.valueOf(dateFrom), Date.valueOf(dateTo), 0));

            List<Contract> contracts = contractService.selectAllContract();

            contractService.addState(contracts.get(contracts.size() - 1).getId(), 1);

        }
        return new ContractResponse.GetContract(0, null);
    }


    @RequestMapping("/commit")
    public Response commit(
            @RequestParam(value = "token", defaultValue = "") String token,
            @RequestParam(value = "id", defaultValue = "") int id,
            @RequestParam(value = "msg", defaultValue = "") String msg
    ) {
        List<Login> logins = loginService.selectLoginByToken(token);
        if (logins.size() == 0) {
            return new ContractResponse.GetContract(-1, null);
        } else {
            contractService.commitContract(id, msg);
            contractService.updateState(id, 4);
            return selectContractByType(token, "uncommitted");
        }
    }

    @RequestMapping("/counter")
    public Response rounter(
            @RequestParam(value = "token", defaultValue = "") String token,
            @RequestParam(value = "id", defaultValue = "") int id
    ) {
        List<Login> logins = loginService.selectLoginByToken(token);
        if (logins.size() == 0) {
            return new ContractResponse.GetContract(-1, null);
        } else {

            contractService.updateState(id, 3);
            return selectContractByType(token, "counterSign");
        }
    }

    @RequestMapping("/watch")
    public Response watch(
            @RequestParam(value = "token", defaultValue = "") String token,
            @RequestParam(value = "id", defaultValue = "") int id
    ) {
        List<Login> logins = loginService.selectLoginByToken(token);
        if (logins.size() == 0) {
            return new ContractResponse.GetContract(-1, null);
        } else {

            contractService.updateState(id, 5);
            return selectContractByType(token, "watchContract");
        }
    }

    @RequestMapping("/sign")
    public Response sign(
            @RequestParam(value = "token", defaultValue = "") String token,
            @RequestParam(value = "id", defaultValue = "") int id
    ) {
        List<Login> logins = loginService.selectLoginByToken(token);
        if (logins.size() == 0) {
            return new ContractResponse.GetContract(-1, null);
        } else {

            contractService.updateState(id, 6);
            return selectContractByType(token, "sign");
        }
    }

    @RequestMapping("/selectUserPermission")
    public Response selectUserPermission(
            @RequestParam(value = "token", defaultValue = "") String token,
            @RequestParam(value = "con_id", defaultValue = "") int con_id
    ) {
        List<Login> logins = loginService.selectLoginByToken(token);

        if (logins.size() == 0) {
            return new PermissionResponse.UserPermission(-1, null);
        } else {
            return new PermissionResponse.UserPermission(0, permissionService.selectUserWithPermission(con_id));
        }
    }

    @RequestMapping("/updatePermission")
    public Response updatePermission(
            @RequestParam(value = "token") String token,
            @RequestParam(value = "con_id") int con_id,
            @RequestParam(value = "type") int type,
            @RequestParam(value = "list") List<String> list
    ) {
        List<Login> logins = loginService.selectLoginByToken(token);

        if (logins.size() == 0) {
            return new PermissionResponse.UserPermission(-1, null);
        } else {
            for (String str : list) {
                permissionService.updatePermission(con_id, Integer.parseInt(str), type);
            }

            return new PermissionResponse.UserPermission(0, null);
        }
    }

    @RequestMapping("/test")
    public List<Permission> test(

    ) {
        return permissionService.selectConPermissionUser(29);
    }

}
