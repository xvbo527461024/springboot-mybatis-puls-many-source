package cn.sm.com.controller;

import cn.sm.com.domain.BmUser;
import cn.sm.com.service.BmUserService;
import cn.sm.com.utils.AjaxResult;
import cn.sm.com.utils.SystemControllerLog;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class BmUserController {
    @Resource
    private BmUserService bmUserService;
    /**
     * 登录方法
     * @param bmUser
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @SystemControllerLog(description = "登录....")
    public AjaxResult login(@RequestBody BmUser bmUser, HttpSession session){
         session.setAttribute("user",bmUser);
        return AjaxResult.me().setMessage("登陆成功");
    }
}
