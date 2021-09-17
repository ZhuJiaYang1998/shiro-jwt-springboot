import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * @Description
 * @Author: 祝嘉洋
 * @Date: 2021 09 16 10:36
 */
public class MD5Realm extends AuthorizingRealm {
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
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
