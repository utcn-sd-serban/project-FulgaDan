package ro.utcn.fulgadan.BookStore.data.entity;

import javax.persistence.*;

@Entity
@Table(name="admin")
public class Admin {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Integer adminId;

    @Column
    private String name;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    public Admin() {}

    public Admin(Integer adminId, String name, User user) {
        this.adminId = adminId;
        this.name = name;
        this.user = user;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
