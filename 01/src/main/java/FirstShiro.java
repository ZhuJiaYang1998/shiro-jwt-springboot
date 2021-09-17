import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import realm.MyRealm;

/**
 * @Description
 * @Author: 祝嘉洋
 * @Date: 2021 09 15 15:42
 */
public class FirstShiro {
    public static void main(String[] args) {
        /**
         * 安全管理器组件
         */
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        /**
         * 设置Rleam
         */
        defaultSecurityManager.setRealm(new MyRealm());
        /**
         * 在全局工具类中设置安全管理器
         */
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        /**
         * 拿到主体
         */
        Subject subject = SecurityUtils.getSubject();
        /**
         * 把用户名密码封装成token
         */
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("zhujiayang","123");
        /**
         * login()进行认证
         */
        try {
            System.out.println(subject.isAuthenticated());
            subject.login(usernamePasswordToken);
            System.out.println(subject.isAuthenticated());
        } catch (UnknownAccountException e) {
            e.printStackTrace();
        }catch (CredentialsException e){
            e.printStackTrace();
        }
    }
}
