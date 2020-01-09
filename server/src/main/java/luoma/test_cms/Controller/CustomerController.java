package luoma.test_cms.Controller;

import luoma.test_cms.Entity.Customer;
import luoma.test_cms.Entity.Login;
import luoma.test_cms.Service.CustomerService;
import luoma.test_cms.Service.LoginService;
import luoma.test_cms.common.Util.StringUtil;
import luoma.test_cms.response.Impl.CustomerResponse;
import luoma.test_cms.response.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Resource
    CustomerService customerService;

    @Resource
    LoginService loginService;

    @RequestMapping("/selectAllCustomer")
    public Response selectAllCustomer() {
        return new CustomerResponse.GetCustomer(0, customerService.selectAllCustomer());
    }

    @RequestMapping("/addCustomer")
    public Response addCustomer(
            @RequestParam(value = "token", defaultValue = "") String token,
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "address", defaultValue = "") String address,
            @RequestParam(value = "tel", defaultValue = "") String tel,
            @RequestParam(value = "fax", defaultValue = "")String fax,
            @RequestParam(value = "mail", defaultValue = "")String mail,
            @RequestParam(value = "bank", defaultValue = "")String bank,
            @RequestParam(value = "account", defaultValue = "")String account
    ) {
        List<Login> logins = loginService.selectLoginByToken(token);

        if (logins.size() == 0) {
            return new CustomerResponse.GetCustomer(-1, null);
        } else {
            Customer customer = new Customer(name, address, tel, fax, mail, bank, account);

            customer.setNum(StringUtil.getRandString());
            customerService.addCustomer(customer);

            return new CustomerResponse.GetCustomer(0, customerService.selectAllCustomer());
        }
    }
}
