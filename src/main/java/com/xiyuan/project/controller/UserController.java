package com.xiyuan.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiyuan.project.common.BaseResponse;
import com.xiyuan.project.common.ErrorCode;
import com.xiyuan.project.common.PageRequest;
import com.xiyuan.project.common.ResultUtils;
import com.xiyuan.project.exception.BusinessException;
import com.xiyuan.project.exception.ThrowUtils;
import com.xiyuan.project.model.dto.user.UserEditRequest;
import com.xiyuan.project.model.dto.user.UserLoginRequest;
import com.xiyuan.project.model.dto.user.UserQueryRequest;
import com.xiyuan.project.model.dto.user.UserRegisterRequest;
import com.xiyuan.project.model.entity.CompetitionEntry;
import com.xiyuan.project.model.entity.Entry;
import com.xiyuan.project.model.entity.User;
import com.xiyuan.project.model.vo.CompetitionEntryVO;
import com.xiyuan.project.model.vo.EntryVO;
import com.xiyuan.project.model.vo.UserVO;
import com.xiyuan.project.service.CompetitionEntryService;
import com.xiyuan.project.service.EntryService;
import com.xiyuan.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户接口
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private EntryService entryService;
    @Resource
    private CompetitionEntryService competitionEntryService;

    /**
     * 用户注册
     *
     * @param userRegisterRequest
     * @return
     */
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long result = userService.userRegister(userRegisterRequest);
        return ResultUtils.success(result);
    }

    /**
     * 用户登录
     *
     * @param userLoginRequest
     * @param request
     * @return
     */
    @PostMapping("/login")
    public BaseResponse<UserVO> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UserVO userVO = userService.userLogin(userLoginRequest, request);
        return ResultUtils.success(userVO);
    }

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public BaseResponse<Boolean> userLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = userService.userLogout(request);
        return ResultUtils.success(result);
    }

    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    @GetMapping("/login")
    public BaseResponse<UserVO> getLoginUser(HttpServletRequest request) {
        User user = userService.getLoginUser(request);
        return ResultUtils.success(userService.getUserVO(user));
    }

    /**
     * 分页获取用户封装列表
     *
     * @param userQueryRequest
     * @return
     */
    @GetMapping
    public BaseResponse<Page<UserVO>> userList(@ModelAttribute UserQueryRequest userQueryRequest) {
        if (userQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long current = userQueryRequest.getCurrent();
        long size = userQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 50, ErrorCode.PARAMS_ERROR);
        Page<User> userPage = userService.page(new Page<>(current, size),
                userService.getQueryWrapper(userQueryRequest));
        Page<UserVO> userVOPage = new Page<>(current, size, userPage.getTotal());
        List<UserVO> userVO = userService.getUserVO(userPage.getRecords());
        userVOPage.setRecords(userVO);
        return ResultUtils.success(userVOPage);
    }

    /**
     * 更新个人信息（用户）
     *
     * @param userUpdateMyRequest
     * @param request
     * @return
     */
    @PutMapping
    public BaseResponse<Boolean> updateUser(@RequestBody UserEditRequest userUpdateMyRequest,
                                            HttpServletRequest request) {
        if (userUpdateMyRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User loginUser = userService.getLoginUser(request);
        User user = new User();
        BeanUtils.copyProperties(userUpdateMyRequest, user);
        user.setId(loginUser.getId());
        boolean result = userService.updateById(user);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    @GetMapping("/entry")
    public BaseResponse<Page<EntryVO>> getUserEntryList(HttpServletRequest request, PageRequest pageRequest) {
        User currentUser = userService.getLoginUser(request);
        long current = pageRequest.getCurrent();
        long size = pageRequest.getPageSize();
        ThrowUtils.throwIf(size > 50, ErrorCode.PARAMS_ERROR);
        QueryWrapper<Entry> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("creator_id", currentUser.getId());
        Page<Entry> entryPage = entryService.page(new Page<>(current, size),
                queryWrapper);
        List<EntryVO> entryVOList = entryService.getEntryVO(entryPage.getRecords());
        Page<EntryVO> voPage = new Page<>(current, size, entryPage.getTotal());
        voPage.setRecords(entryVOList);
        return ResultUtils.success(voPage);
    }

    @GetMapping("/competition")
    public BaseResponse<Page<CompetitionEntryVO>> getUserCompetitionList(HttpServletRequest request, PageRequest pageRequest) {
        User currentUser = userService.getLoginUser(request);
        long current = pageRequest.getCurrent();
        long size = pageRequest.getPageSize();
        ThrowUtils.throwIf(size > 50, ErrorCode.PARAMS_ERROR);
        QueryWrapper<CompetitionEntry> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("captain_id", currentUser.getId());
        Page<CompetitionEntry> competitionEntryPage = competitionEntryService.page(new Page<>(current, size),
                queryWrapper);
        List<CompetitionEntryVO> entryVOList = competitionEntryService.getCompetitionEntryVO(competitionEntryPage.getRecords());
        Page<CompetitionEntryVO> voPage = new Page<>(current, size, competitionEntryPage.getTotal());
        voPage.setRecords(entryVOList);
        return ResultUtils.success(voPage);
    }
}
