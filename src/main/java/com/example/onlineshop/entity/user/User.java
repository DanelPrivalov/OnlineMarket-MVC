import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userIid;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "access_level_id", nullable = false)
    private int accessLevelID;

    @Column(name = "city")
    private String city;//customer

    @Column(name = "date_of_birth")
    private String dateOfBirth; //customer

    @Column(name = "discount")
    private double discount; //customer

    @Column(name = "images")
    private String images; //ссылка на каталог картинки для менеджера
  
    public User() {
    }

    public User(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
}

