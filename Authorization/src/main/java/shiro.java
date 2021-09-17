import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

import java.util.Arrays;

/**
 * @Description
 * @Author: 祝嘉洋
 * @Date: 2021 09 16 10:34
 */
@Slf4j
public class shiro {
    public static void main(String[] args) {
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

        MyRealm md5Realm = new MyRealm();

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
        /**
         * 授权
         */
        if (subject.isAuthenticated()) {
//            System.out.println("==============授权");
            /**
             * 主体用户是否有改角色
             */
            System.out.println(subject.hasRole("user"));
            System.out.println(subject.hasAllRoles(Arrays.asList("user", "admin")));
            boolean[] booleans = subject.hasRoles(Arrays.asList("user", "admin"));
            for (boolean aBoolean : booleans) {
                System.out.print(aBoolean);
            }
        }
    }
}
