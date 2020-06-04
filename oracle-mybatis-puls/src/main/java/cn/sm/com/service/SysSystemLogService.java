package cn.sm.com.service;
import cn.sm.com.domain.SysSystemLog;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Insert;

import java.io.Serializable;

/**
 * <p>
 * 系统日志 服务类
 * </p>
 *
 * @author xubo
 * @since 2020-06-03
 */
public interface SysSystemLogService extends IService<SysSystemLog> {

       Serializable insert(SysSystemLog sysSystemLog);
}
