package com.xiyuan.project.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiyuan.project.model.dto.user.UserLoginRequest;
import com.xiyuan.project.model.dto.user.UserQueryRequest;
import com.xiyuan.project.model.dto.user.UserRegisterRequest;
import com.xiyuan.project.model.entity.User;
import com.xiyuan.project.model.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author xiyuan
 * @description 针对表【user】的数据库操作Service
 * @createDate 2023-12-21 22:24:11
 */
public interface UserService extends IService<User> {
    /**
     * 用户注册
     *
     * @return 新用户 id
     */
    long userRegister(UserRegisterRequest registerRequest);

    /**
     * 用户登录
     *
     * @param loginRequest 登陆请求封装
     * @param request
     * @return 脱敏后的用户信息
     */
    UserVO userLogin(UserLoginRequest loginRequest, HttpServletRequest request);

    /**
     * 获取当前登录用户
     *
     * @param request
     * @return 用户信息
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * 获取密码加密
     *
     * @param password
     * @return
     */
    String EncryptPassword(String password);

    /**
     * 是否为管理员
     *
     * @param request
     * @return
     */
    boolean isAdmin(HttpServletRequest request);

    /**
     * 是否为管理员
     *
     * @param user
     * @return
     */
    boolean isAdmin(User user);

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    boolean userLogout(HttpServletRequest request);

    /**
     * 获取脱敏的用户信息
     *
     * @return
     */
    UserVO getUserVO(User user);

    /**
     * 获取脱敏的用户信息
     *
     * @return
     */
    UserVO getUserVO(Long userId);

    /**
     * 获取脱敏的用户信息
     *
     * @param userList
     * @return
     */
    List<UserVO> getUserVO(List<User> userList);

    /**
     * 获取查询条件
     *
     * @param userQueryRequest
     * @return
     */
    QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest);
}
