package luoma.test_cms.response.Impl;

import lombok.Data;
import luoma.test_cms.Entity.UserManager_Entity;
import luoma.test_cms.response.Response;

import java.util.List;

public class UserResponse {
    @Data
    public static class Login implements Response {
        public int state;
        public int group;
        public String token;

        public Login(int state, int group, String token) {
            this.state = state;
            this.group = group;
            this.token = token;
        }
    }

    @Data
    public static class Register implements Response {
        public int state;

        public Register(int state) {
            this.state = state;
        }
    }

    @Data
    public static class Manager implements Response {
        public int state;

        public Manager(int state, List<UserManager_Entity> manager_entities) {
            this.state = state;
            this.manager_entities = manager_entities;
        }

        public List<UserManager_Entity> manager_entities;
    }
}
