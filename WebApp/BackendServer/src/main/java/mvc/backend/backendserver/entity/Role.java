package mvc.backend.backendserver.entity;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.GenerationType;


@Data
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", columnDefinition = "TINYINT(1)")
    private Integer roleId;

    @Column(name = "role_name")
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role", orphanRemoval = true)
//    @JsonIgnore
//    private List<Account> accounts;
}
