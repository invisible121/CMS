package luoma.test_cms.Entity;

import lombok.Data;

@Data
public class UserManager_Entity {
    public String name;
    public int rol_id;

    public UserManager_Entity(String name, int rol_id) {
        this.name = name;
        this.rol_id = rol_id;
    }
}
