package luoma.test_cms.Entity;

import lombok.Data;

@Data
public class Role {
    private int id;
    private String name;
    private String description;
    private int del;
}
