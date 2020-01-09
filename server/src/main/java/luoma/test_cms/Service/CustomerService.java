package luoma.test_cms.Service;

import luoma.test_cms.Entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> selectAllCustomer();

    int addCustomer(Customer customer);

    List<Customer> getCustomerById(int id);
}
