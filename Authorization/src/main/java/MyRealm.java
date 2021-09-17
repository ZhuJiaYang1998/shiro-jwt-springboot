import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.Arrays;

/**
 * @Description
 * @Author: 祝嘉洋
 * @Date: 2021 09 16 10:36
 */
@Slf4j
public class MyRealm extends AuthorizingRealm {
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        /**
         * 这里的primaryPrincial 就是账号 拿到账号可以重datasource中取user的信息
         */
        System.out.println("==============================");
        /**
         * 这里做数据库的假数据
         */
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //user admin datasource中取
        simpleAuthorizationInfo.addRoles(Arrays.asList("user","admin"));
        //权限从database中取
        simpleAuthorizationInfo.addStringPermissions(Arrays.asList("books:update:*","books:select:*"));
        return simpleAuthorizationInfo;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String) authenticationToken.getPrincipal();
        String username = "zhujiayang";
        String pass = "672b529083f6ccd0ae6ed4ab15e03423";
        if (username.equals(principal)) {
            return new SimpleAuthenticationInfo(username, pass,
                    ByteSource.Util.bytes("xP*IODx"),this.getName());
        }
        return null;
    }
}
