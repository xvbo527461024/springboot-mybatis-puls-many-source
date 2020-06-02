package cn.sm.com.controller;
import cn.sm.com.domain.BmSubject;
import cn.sm.com.service.BmSubjectService;
import cn.sm.com.utils.AjaxResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * <p>
 * 预算科目表 前端控制器
 * </p>
 *
 * @author xubo
 * @since 2020-05-23
 */
@RestController
@RequestMapping("/bmSubject")
public class BmSubjectController {

    @Autowired
    private BmSubjectService bmSubjectService;

    @RequestMapping(value = "/queryAll",method = RequestMethod.GET)
    public AjaxResult queryAll(){
        try {
            QueryWrapper<BmSubject> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("subject_Id", "subject_Name").like("full_name", "营销");
            List<BmSubject> list = bmSubjectService.list(queryWrapper);
            return  AjaxResult.me().setMessage("查询成功").setObject(list);
        } catch (Exception e) {
            return  AjaxResult.me().setMessage("查询失败").setSucceed(false).setObject(e.getMessage());

        }
    }


    @RequestMapping(value = "/getSelectTreeData",method = RequestMethod.GET)
    public AjaxResult getSelectTreeData(){
        try {

            List<LinkedHashMap<String, Object>> list = bmSubjectService.getSelectTreeData();
            return  AjaxResult.me().setMessage("查询成功").setObject(list);
        } catch (Exception e) {
            return  AjaxResult.me().setMessage("查询失败").setSucceed(false).setObject(e.getMessage());

        }
    }

    @RequestMapping(value = "/exportBudgetByEntityId",method = RequestMethod.GET)
    public AjaxResult exportBudgetByEntityId(){
        try {
            List<LinkedHashMap<String, Object>> list = bmSubjectService.exportBudgetByEntityId();
            return  AjaxResult.me().setMessage("查询成功").setObject(list);
        } catch (Exception e) {
            return  AjaxResult.me().setMessage("查询失败").setSucceed(false).setObject(e.getMessage());

        }
    }
}

