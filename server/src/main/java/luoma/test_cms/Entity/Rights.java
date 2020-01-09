package luoma.test_cms.Entity;

import lombok.Data;

@Data
public class Rights {
    private int rol_id;
    private int id;

    public Rights(int rol_id, int id) {
        this.rol_id = rol_id;
        this.id = id;
    }

    public int getRol_id() {
        return rol_id;
    }

    public void setRol_id(int rol_id) {
        this.rol_id = rol_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
