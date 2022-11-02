import java.io.Serializable;

/**
 * A user class to create a new player
 *
 * @author Micky Santiago-Zayas
 * @version July 9, 2021
 */

public class User implements Serializable {
    private long id;
    private String name;

    public User(String name) {
        this.name = name;
        long id = this.hashCode();
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User<" +
                "id=" + id +
                ", username='" + name + '\'' +
                '>';
    }
}
