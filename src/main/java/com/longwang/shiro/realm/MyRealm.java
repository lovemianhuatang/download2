package com.longwang.shiro.realm;

import com.longwang.common.Constant;
import com.longwang.common.Result;
import com.longwang.entity.SysUser;
import com.longwang.service.ISysUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义Realm
 * @author 竹林听雨
 * @date 2018年9月12日 下午9:16:47
 */
public class MyRealm extends AuthorizingRealm{

	@Autowired
	private ISysUserService iSysUserService;

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	/**
	 * 权限认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username=(String)token.getPrincipal();
		Result<SysUser> result = iSysUserService.login(username);
		AuthenticationInfo authcInfo = null;
		if(result.isSuccess()){
			if(result.getData().getStatus() == Constant.State.STATE_NORMAL)
				authcInfo = new SimpleAuthenticationInfo(result.getData().getUsername(),result.getData().getPassword(),"xx");
		}
		return authcInfo;
	}

}
