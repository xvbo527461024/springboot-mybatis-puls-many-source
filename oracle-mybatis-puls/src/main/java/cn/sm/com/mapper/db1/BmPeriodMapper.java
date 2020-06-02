package cn.sm.com.mapper.db1;


import cn.sm.com.domain.BmPeriod;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 预算期间表 Mapper 接口
 * </p>
 *
 * @author xubo
 * @since 2020-05-30
 */
public interface BmPeriodMapper extends BaseMapper<BmPeriod> {

    List<BmPeriod> queryPeriodListByYear(int year);
}
