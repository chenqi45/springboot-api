package com.crell.common.controller;

import com.alibaba.fastjson.JSON;
import com.crell.common.model.User;
import com.crell.common.service.UserService;
import com.crell.core.annotation.NotNull;
import com.crell.core.constant.Context;
import com.crell.core.constant.ResponseState;
import com.crell.core.dto.Page;
import com.crell.core.dto.ParamsBody;
import com.crell.core.dto.ReturnBody;
import com.crell.core.util.LogUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by crell on 2016/10/9.
 */
@RestController
@RequestMapping(value = "crell/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private LogUtil logUtil;

    @ApiOperation(value="获取用户列表", httpMethod = "GET", notes="参数 pageNo pageSize")
    @RequestMapping(value = {"/list"},method = RequestMethod.GET)
    public ReturnBody findList(HttpServletRequest request){
        ReturnBody rbody = new ReturnBody();
        Page page = userService.findList(new Page(request));
        List<User> userList = page.getResults();

        rbody.setStatus(ResponseState.SUCCESS);
        rbody.setData(userList);
        rbody.setPages(page.getTotalPage());

        return rbody;
    }

    @ApiOperation(value="获取用户", httpMethod = "GET", notes="参数 id")
    @RequestMapping(value = {"{id}"},method = RequestMethod.GET)
    public ReturnBody get(@PathVariable("id") String id, HttpServletRequest request){
        ReturnBody rbody = new ReturnBody();
        User user = userService.get(id);

        rbody.setStatus(ResponseState.SUCCESS);
        rbody.setData(user);

        return rbody;
    }

    @ApiOperation(value="新建用户", httpMethod = "POST", notes="参数 userName email")
    @RequestMapping(value = {""},method = RequestMethod.POST)
    @NotNull(value = "userName")
    public ReturnBody add(@RequestBody ParamsBody paramsBody, HttpServletRequest request) throws Exception {
        ReturnBody rbody = new ReturnBody();
        Map<String, Object> body = paramsBody.getBody();

        User user = new User();
        BeanUtils.populate(user,body);
        userService.save(user);

        rbody.setStatus(ResponseState.SUCCESS);
        rbody.setData(user);
        return rbody;
    }

    @ApiOperation(value="删除用户", httpMethod = "DELETE", notes="参数 id")
    @RequestMapping(value = {""},method = RequestMethod.DELETE)
    @NotNull(value = "id")
    public ReturnBody delete(@RequestBody ParamsBody paramsBody, HttpServletRequest request) throws Exception {
        ReturnBody rbody = new ReturnBody();
        Map<String, Object> body = paramsBody.getBody();

        User user = new User();
        BeanUtils.populate(user,body);
        userService.delete(user);

        rbody.setStatus(ResponseState.SUCCESS);
        rbody.setData(user);
        return rbody;
    }

    @ApiOperation(value="修改用户", httpMethod = "PUT", notes="参数 id userName email")
    @RequestMapping(value = {""},method = RequestMethod.PUT)
    @NotNull(value = "id")
    public ReturnBody update(@RequestBody ParamsBody paramsBody, HttpServletRequest request) throws Exception {
        ReturnBody rbody = new ReturnBody();
        Map<String, Object> body = paramsBody.getBody();

        User user = new User();
        BeanUtils.populate(user,body);
        userService.save(user);
        logUtil.action("修改用户", JSON.toJSONString(user));

        rbody.setStatus(ResponseState.SUCCESS);
        rbody.setData(user);
        return rbody;
    }
}
