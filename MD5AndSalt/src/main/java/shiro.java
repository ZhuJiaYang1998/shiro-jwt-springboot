import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

/**
 * @Description
 * @Author: 祝嘉洋
 * @Date: 2021 09 16 10:34
 */
public class shiro {
    public static void main(String[] args) {
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

        MD5Realm md5Realm = new MD5Realm();

        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        /**
         * 密码校验规则使用MD5算法 散列1024次 这里的盐是从datasource中取出来的
         */
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(1024);

        md5Realm.setCredentialsMatcher(hashedCredentialsMatcher);

        defaultSecurityManager.setRealm(md5Realm);

        SecurityUtils.setSecurityManager(defaultSecurityManager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("zhujiayang","123");
        try {
            subject.login(usernamePasswordToken);
            System.out.println(subject.isAuthenticated());
        } catch (UnknownAccountException e) {
            e.printStackTrace();
        }catch (IncorrectCredentialsException E){
            E.printStackTrace();
        }catch (AuthenticationException e){
            e.printStackTrace();
        }
    }
}
