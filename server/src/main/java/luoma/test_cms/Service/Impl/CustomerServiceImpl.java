package luoma.test_cms.Service.Impl;

import luoma.test_cms.Dao.CustomerDao;
import luoma.test_cms.Entity.Customer;
import luoma.test_cms.Service.CustomerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("CustomerService")
public class CustomerServiceImpl implements CustomerService {

    @Resource
    CustomerDao customerDao;

    @Override
    public List<Customer> selectAllCustomer() {
        return customerDao.selectAllCustomer();
    }

    @Override
    public List<Customer> getCustomerById(int id) {
        return customerDao.getCustomerById(id);
    }

    @Override
    public int addCustomer(Customer customer) {
        return customerDao.addCustomer(customer);
    }
}
