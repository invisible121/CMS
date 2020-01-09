package luoma.test_cms.Dao;

import luoma.test_cms.Entity.Customer;

import java.util.List;

public interface CustomerDao {

    List<Customer> selectAllCustomer();

    int addCustomer(Customer customer);

    List<Customer> getCustomerById(int id);
}
