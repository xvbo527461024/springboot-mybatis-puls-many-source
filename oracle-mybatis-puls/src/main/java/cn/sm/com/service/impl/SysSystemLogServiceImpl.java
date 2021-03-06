package cn.sm.com.service.impl;

import cn.sm.com.domain.SysSystemLog;
import cn.sm.com.mapper.db1.SysSystemLogMapper;
import cn.sm.com.service.SysSystemLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * <p>
 * 系统日志 服务实现类
 * </p>
 *
 * @author xubo
 * @since 2020-06-03
 */
@Service
public class SysSystemLogServiceImpl extends ServiceImpl<SysSystemLogMapper, SysSystemLog> implements SysSystemLogService {
@Resource
private SysSystemLogMapper sysSystemLogMapper;
    @Override
    public Serializable insert(SysSystemLog sysSystemLog) {
         sysSystemLogMapper.insert(sysSystemLog);
         return sysSystemLog.getLogId();
    }
}
