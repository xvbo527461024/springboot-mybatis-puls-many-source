package cn.sm.com.mapper.db2;
import cn.sm.com.domain.BmSubject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 预算科目表 Mapper 接口
 * </p>
 *
 * @author xubo
 * @since 2020-05-23
 */
public interface BmSubjectMapper extends BaseMapper<BmSubject> {
//查询科目树
  List<Map<String, Object>> selectByYear();
  List<LinkedHashMap<String, Object>> getSelectTreeData();
}
