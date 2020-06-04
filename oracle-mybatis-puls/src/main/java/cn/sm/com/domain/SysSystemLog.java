package cn.sm.com.domain;
import java.math.BigDecimal;
import cn.sm.com.base.EntityBase;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 系统日志
 * </p>
 *
 * @author xubo
 * @since 2020-06-03
 */
@TableName("SYS_SYSTEM_LOG")
public class SysSystemLog extends EntityBase<SysSystemLog> {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableField("LOG_ID")
    private BigDecimal logId;

    /**
     * 类名
     */@TableField("class_name")
    private String className;
    /**
     * 类名
     */@TableField("METHOD_NAME")
    private String methodName;


    /**
     * 日志类型
     */
    @TableField("LOG_TYPE")
    private String logType;

    /**
     * 登录人id
     */
    @TableField("USER_ID")
    private Long userId;

    /**
     * 登录人名称
     */
    @TableField("EMPLOYEE_NAME")
    private String employeeName;

    /**
     * 登录ip
     */
    @TableField("LOGIN_IP")
    private String loginIp;

    /**
     * 操作时间
     */
    @TableField("ACTION_TIME")
    private LocalDateTime actionTime;


    public BigDecimal getLogId() {
        return logId;
    }

    public void setLogId(BigDecimal logId) {
        this.logId = logId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public LocalDateTime getActionTime() {
        return actionTime;
    }

    public void setActionTime(LocalDateTime actionTime) {
        this.actionTime = actionTime;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "SysSystemLog{" +
                "logId=" + logId +
                ", className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", logType='" + logType + '\'' +
                ", userId=" + userId +
                ", employeeName='" + employeeName + '\'' +
                ", loginIp='" + loginIp + '\'' +
                ", actionTime=" + actionTime +
                '}';
    }
}
