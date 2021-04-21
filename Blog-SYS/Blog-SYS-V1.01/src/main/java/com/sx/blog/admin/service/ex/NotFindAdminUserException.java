package com.sx.blog.admin.service.ex;

/**
 * 找不到管理员名字异常类
 * @author 浩爸爸
 *
 */
public class NotFindAdminUserException extends ServiceException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFindAdminUserException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NotFindAdminUserException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public NotFindAdminUserException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NotFindAdminUserException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NotFindAdminUserException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
