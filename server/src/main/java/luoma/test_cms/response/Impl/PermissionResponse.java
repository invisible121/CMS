package luoma.test_cms.response.Impl;

import lombok.Data;
import luoma.test_cms.Entity.User_entity;
import luoma.test_cms.response.Response;

import java.util.List;

public class PermissionResponse {

    @Data
    public static class UserPermission implements Response {
        int state;
        List<User_entity> users;

        public UserPermission(int state, List<User_entity> users) {
            this.state = state;
            this.users = users;
        }
    }
}
