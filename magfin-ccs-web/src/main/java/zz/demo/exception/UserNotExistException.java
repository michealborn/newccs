package zz.demo.exception;

/**
 * @Time 2019/4/3
 * @Author zlian
 */
public class UserNotExistException extends RuntimeException {
    private String id;

    public UserNotExistException(String id) {
        super("user not exist");
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
