package realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @Description
 * @Author: 祝嘉洋
 * @Date: 2021 09 15 17:16
 */
public class MyRealm extends AuthorizingRealm {
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        /**
         * 模拟数据库取值校验账号密码
         */
        //假数据：用户名密码
        String userName = "zhujiayang";
        String passWord = "123456";
        //通过login传过来的token取到用户的身份信息
        String principal = (String) authenticationToken.getPrincipal();
        //这里实际是要从数据库中getone拿到用户的所有info，判断isLock？ isExpire？
        if (userName.equals(principal)){
            //参数1：用户名 参数2：密码 参数3：realm的name
            return new SimpleAuthenticationInfo(userName,passWord,this.getName());
        }
        //如果这里返回null doGetAuthenticationInfo返回的info==null 这里会抛出UnknownAccountException
        return null;
    }
}
