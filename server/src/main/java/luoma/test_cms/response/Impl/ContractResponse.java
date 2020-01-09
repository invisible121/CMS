package luoma.test_cms.response.Impl;

import lombok.Data;
import luoma.test_cms.Entity.Contract;
import luoma.test_cms.response.Response;

import java.util.List;

public class ContractResponse {
    @Data
    public static class GetContract implements Response {
        int state;

        List<Contract> data;

        public GetContract(int state, List<Contract> data) {
            this.state = state;
            this.data = data;
        }
    }
}
