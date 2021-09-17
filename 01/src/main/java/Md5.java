import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @Description
 * @Author: 祝嘉洋
 * @Date: 2021 09 16 9:37
 */
public class Md5 {
    public static void main(String[] args) {
        /**
         * shiro提供的MD5Hash算法
         */
        Md5Hash md5Hash = new Md5Hash();
        //不要使用set赋值
        md5Hash.setBytes("123".getBytes());
        System.out.println(md5Hash);
        /**
         * 要使用构造方法 参数一：加密字符串
         */
        Md5Hash md5Hash1 = new Md5Hash("123");
        //以十六进制 32 位的字符串打印
        System.out.println(md5Hash1.toHex());
        /**
         * 参数一：要加密字符串 二、盐 三、散列次数
         */
        Md5Hash md5Hash2 = new Md5Hash("123","xP*IODx",1024);
        System.out.println(md5Hash2.toHex());
    }
}
