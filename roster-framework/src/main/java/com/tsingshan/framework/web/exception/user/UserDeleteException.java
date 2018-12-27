package com.tsingshan.framework.web.exception.user;

/**
 * 用户账号已被删除
 * 
 * @author 拖板孩
 */
public class UserDeleteException extends UserException
{
    private static final long serialVersionUID = 1L;

    public UserDeleteException()
    {
        super("user.password.delete", null);
    }
}
