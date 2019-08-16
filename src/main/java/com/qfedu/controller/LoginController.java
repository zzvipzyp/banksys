package com.qfedu.controller;

import com.qfedu.common.JsonResult;
import com.qfedu.entity.User;
import com.qfedu.service.UserService;
import com.qfedu.utils.StrUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@Controller
@Validated // 如果要在方法的参数上使用验证注解，需要在控制器类中使用该注解
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login2.do")
    @ResponseBody
//    public JsonResult login(String bankCode, String password, HttpSession session){
    // 实体类中有验证注解，控制器中，使用@Valid修饰
    // 紧跟着使用注解的参数，需要定义BIndingResult参数（获取验证不通过的信息）
    public JsonResult login2(@Valid User u, BindingResult bindResult, HttpSession session){
        //User user = userService.login(bankCode, password);

        // 判断验证是否没有通过
        boolean ret = bindResult.hasErrors();
        if(ret){
            // 获取所有验证不通过的信息
            List<ObjectError> allErrors = bindResult.getAllErrors();
            for(ObjectError err : allErrors){
                FieldError error = (FieldError)err;
                // 获取验证不通过的属性和信息
                System.out.println(error.getField());
                System.out.println(error.getDefaultMessage());
            }
            return new JsonResult(0, "验证异常");
        }


        User user = userService.login(u.getBankCode(), u.getPassword());
        session.setAttribute(StrUtils.LOGIN_USER, user);

        JsonResult result = new JsonResult(1, null);
        return result;
    }

    @RequestMapping("/login.do")
    @ResponseBody
    public JsonResult login(@NotEmpty String bankCode, @Size(min = 1,max = 10) String password, HttpSession session){
        User user = userService.login(bankCode, password);
        session.setAttribute(StrUtils.LOGIN_USER, user);

        JsonResult result = new JsonResult(1, null);
        return result;
    }

    // 使用springmvc的时候，处理异常

    // @ExceptionHandler 修饰的方法，表示一个异常处理方法
    // 指定针对哪个异常进行处理
//    @ExceptionHandler(ConstraintViolationException.class)
//    @ResponseBody
//    public JsonResult constraintViolationException(ConstraintViolationException ex){
//        // 验证不通过的信息
//        Iterator<ConstraintViolation<?>>
//                iterator = ex.getConstraintViolations().iterator();
//        String message = null;
//        if (iterator.hasNext()) {
//            message = iterator.next().getMessage();
//            System.out.println(message);
//        }
//        return new JsonResult(0, "验证异常");
//    }

}
