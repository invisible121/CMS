package luoma.test_cms.Entity;

import lombok.Data;

@Data
public class User_entity {
    private int id;
    private String name;
    private String password;
    private String salt;
    private int del;
    private int permissions;

    public int getPermissions() {
        return permissions;
    }

    public void setPermissions(int permissions) {
        this.permissions = permissions;
    }

    public User_entity(int id, String name, String password, String salt, int del) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.salt = salt;
        this.del = del;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getDel() {
        return del;
    }

    public void setDel(int del) {
        this.del = del;
    }
}
