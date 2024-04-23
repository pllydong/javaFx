package mtyx.model;

import cn.hutool.core.annotation.Alias;
import lombok.Data;

/**
 * @author guokun
 * @date 2024/4/20 18:56
 */
@Data
public class LoginAccount {
    /**
     * 账号
     */
    @Alias("账号")
    private String account;
    /**
     * 密码
     */
    @Alias("密码")
    private String password;

    /**
     * 必要cookie，用于验证身份
     */
    @Alias("BSID")
    private String BSID;
}
