package luoma.test_cms.response.Impl;

import lombok.Data;
import luoma.test_cms.Entity.Customer;
import luoma.test_cms.response.Response;

import java.util.List;

public class CustomerResponse {

    @Data
    public static class GetCustomer implements Response {
        int state;

        List<Customer> customers;

        public GetCustomer(int state, List<Customer> customers) {
            this.state = state;
            this.customers = customers;
        }
    }
}
