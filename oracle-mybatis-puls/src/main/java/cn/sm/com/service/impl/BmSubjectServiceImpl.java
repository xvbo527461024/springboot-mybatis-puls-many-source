package cn.sm.com.service.impl;
import cn.sm.com.mapper.db1.BmPeriodMapper;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.dynamic.datasource.annotation.DS;

import cn.sm.com.domain.BmSubject;
import cn.sm.com.mapper.db2.BmSubjectMapper;
import cn.sm.com.service.BmSubjectService;
import cn.sm.com.utils.DealListMapUtils;
import cn.sm.com.utils.RedisUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * <p>
 * 预算科目表 服务实现类
 * </p>
 *
 * @author xubo
 * @since 2020-05-23
 */
@Service
public class BmSubjectServiceImpl extends ServiceImpl<BmSubjectMapper, BmSubject> implements BmSubjectService {
    @Resource
    private BmSubjectMapper bmSubjectMapper;

    @Resource
    private BmPeriodMapper  periodMapper;

    @Autowired
    private RedisUtil redisUtil;    //工具类bean注入调用方
    @Override
    public List<LinkedHashMap<String, Object>> getSelectTreeData() {
        Date date = new Date();
        Object subjectTree = redisUtil.get("20202222fgg");
        List<LinkedHashMap<String, Object>> treeList=null;
        if (subjectTree == null) {
            List<LinkedHashMap<String, Object>> selectTreeData = bmSubjectMapper.getSelectTreeData();
            treeList = DealListMapUtils.getTreeList(selectTreeData, "0", "parentId", "id", true, true);
            String str = JSON.toJSONString(treeList); // List转json
            redisUtil.set("20202222fgg", str);
            return treeList;
        }else {
            treeList=JSON.parseObject((String) subjectTree, new TypeReference<List<LinkedHashMap<String, Object>>>() {});

        }
        Date date1 = new Date();
        System.out.println(date1.getTime()-date.getTime());
        return treeList;
    }

    @Override
    public List<LinkedHashMap<String, Object>> exportBudgetByEntityId() {
        List<LinkedHashMap<String, Object>> list=null;
        Object exportBudgetByEntityId = redisUtil.get("exportBudgetByEntityId");
        if(exportBudgetByEntityId==null){
             list = bmSubjectMapper.getSelectTreeData();
             redisUtil.set("exportBudgetByEntityId", list);
        }else {
/*
            list=JSON.parseObject((String) exportBudgetByEntityId, new TypeReference<List<LinkedHashMap<String, Object>>>() {});
*/
            list=(List<LinkedHashMap<String, Object>>)exportBudgetByEntityId;



        }
        return list;
    }
}
