package com.weixinserver.controller.wap;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weixinserver.entity.Ammeter;
import com.weixinserver.entity.AmmeterData;
import com.weixinserver.entity.Project;
import com.weixinserver.model.UploadData;
import com.weixinserver.model.UploadModel;
import com.weixinserver.service.CommonService;

@Controller
@RequestMapping(value = "/upload")
public class UploadController {
  @Autowired
  private CommonService commonService;

  @RequestMapping(value = "/uploadData", method = RequestMethod.POST)
  public @ResponseBody ModelMap uploadData(@RequestBody UploadModel data) {
    ModelMap map = new ModelMap();
    try {
      SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      map.put("hasSuccess", false);
      String sql = " where u.basicName='%s' and u.basicPwd='%s' and u.code='%s' ";
      sql = String.format(sql, data.getUsername(), data.getPassword(),data.getProCode());

      Project pro = commonService.findByWhere(sql, Project.class);
      if(pro == null){
        map.put("msg", "验证失败。");
        return map;
      }
      for (UploadData item : data.getDataList()) {
        Ammeter amm = commonService.findByCode(item.getAmm_code(), Ammeter.class);
        if(amm == null){
          Ammeter newAmm = new Ammeter();
          newAmm.setCode(item.getAmm_code());
          newAmm.setName(item.getAmm_name());
          newAmm.setProject(pro);
          amm = commonService.save(newAmm);
        }else{
          amm.setName(item.getAmm_name());
          amm = commonService.update(amm);
        }
        AmmeterData ammData = new AmmeterData();
        ammData.setAmmeter(amm);
        ammData.setData(item.getData());
        ammData.setDate(sf.parse(item.getDate()));
        commonService.save(ammData);
      }
      map.put("msg", "上传成功。");
      map.remove("hasSuccess");
      map.put("hasSuccess", true);
    } catch (Exception e) {
      map.put("msg", "系统异常：" + e.getMessage());
    }
    return map;
  }
}
