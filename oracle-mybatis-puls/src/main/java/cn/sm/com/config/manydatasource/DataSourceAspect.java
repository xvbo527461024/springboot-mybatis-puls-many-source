package cn.sm.com.config.manydatasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * aop的实现的数据源切换<br> * aop切点，实现mapper类找寻，找到所属大本营以后，如db1Aspect(),则会调用<br> * db1()前面之前的操作，进行数据源的切换。
 */
@Component
@Order(value = -100)
@Slf4j
@Aspect
public class DataSourceAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceContextHolder.class);

    @Pointcut("execution(* cn.sm.com.mapper.db1..*.*(..))")
    private void db1Aspect() {
    }
 
    @Pointcut("execution(* cn.sm.com.mapper.db2..*.*(..))")
    private void db2Aspect() {
    }
 
    @Before("db1Aspect()")
    public void db1() {
        System.out.println("切换到数据源db1");
        DataSourceContextHolder.setDbType(DBTypeEnum.db1);
    }
 
    @Before("db2Aspect()")
    public void db2() {
        System.out.println("切换到数据源db2");
        DataSourceContextHolder.setDbType(DBTypeEnum.db2);
    }
}