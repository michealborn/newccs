package zz.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import zz.demo.dto.User;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @Time 2019/4/2
 * @Author zlian
 */
@RestController
public class UserController {

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    //3、在controller方法上指定视图
    @JsonView(User.UserSimpleView.class)
    public List<User> query(@RequestParam String userName,
                            @PageableDefault Pageable pageable){

        System.out.println(userName);
        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getSort());

        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    //正则表达式表示id只能是数字
    @RequestMapping(value = "/user/{id:\\d+}",method = RequestMethod.GET)
    //3、在controller方法上指定视图
    @JsonView(User.UserDetailView.class)
    //通过 @PathVariable 可以将URL中占位符参数{xxx}绑定到处理器类的方法形参中
    public User getInfo(@PathVariable String id){
        System.out.println("进入getInfo服务");
        User user = new User();
        user.setUserName("micheal");
        return user;
    }

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public User create(@Valid @RequestBody User user, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
        }

        System.out.println(user.getId());
        System.out.println(user.getUserName());
        System.out.println(user.getPassWord());
        System.out.println(user.getBirthday());
        user.setId("1");
        return user;
    }

    @RequestMapping(value = "/user/{id:\\d+}",method = RequestMethod.PUT)
    public User update(@Valid @RequestBody User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().stream().forEach(error ->{
//                FieldError fieldError = (FieldError)error;
//                String message = fieldError.getField()+"  "+error.getDefaultMessage();
                System.out.println(error.getDefaultMessage());
            });
        }

        System.out.println(user.getId());
        System.out.println(user.getUserName());
        System.out.println(user.getPassWord());
        System.out.println(user.getBirthday());
        user.setId("1");
        return user;
    }

    @RequestMapping(value = "/user/{id:\\d+}",method = RequestMethod.DELETE)
    public void delete (@PathVariable String id){
        System.out.println(id);
        return ;
    }

    @RequestMapping(value = "/user/testError",method = RequestMethod.GET)
    public void testError (String id){
        throw new RuntimeException(id);
    }
}
