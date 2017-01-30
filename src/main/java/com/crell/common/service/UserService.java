package com.crell.common.service;

import com.crell.common.mapper.UserMapper;
import com.crell.common.model.User;
import com.crell.core.dto.Page;
import com.crell.core.expection.FailedException;
import com.crell.core.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by crell on 2016/10/9.
 */

@Service
@Transactional(readOnly = true)
public class UserService extends CrudService<UserMapper, User> {

}
