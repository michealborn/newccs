package zz.demo.dto;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.NotBlank;
import zz.demo.validator.MyConstraint;

import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @Time 2019/4/2
 * @Author zlian
 */
public class User {

    //1、使用接口声明多个视图
    public interface UserSimpleView {};
    public interface UserDetailView extends UserSimpleView {};

    private String id;
    @MyConstraint(message = "这是一个测试")
    private String userName;
    @NotBlank(message = "密码不能为空")
    private String passWord;
    @Past(message = "生日必须是过去的时间")
    private Date birthday;

    //2、在值对象的get方法上指定视图
    @JsonView(UserSimpleView.class)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonView(UserSimpleView.class)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    @JsonView(UserDetailView.class)
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @JsonView(UserSimpleView.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
