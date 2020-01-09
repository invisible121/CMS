package luoma.test_cms.Entity;

import lombok.Data;

@Data
public class Login {
    public int id;
    public String token;

    public Login(int id, String token) {
        this.id = id;
        this.token = token;
    }
}
