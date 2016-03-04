package com.weixinserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weixinserver.entity.OpenInfo;
import com.weixinserver.model.CONST;
import com.weixinserver.model.WeChatMessage;
import com.weixinserver.repository.CommonRepository;
import com.weixinserver.service.CoreService;
import com.weixinserver.util.AccessTokenHolder;
import com.weixinserver.util.MsgUtil;
import com.weixinserver.util.NetUtil;
import com.weixinserver.util.ParseXML;

@Service("coreService")
@Transactional
public class CoreServiceImpl implements CoreService {
  @Autowired
  private CommonRepository commonRepository;

  @Override
  public String processRequest(String msg) {
    String respMessage = null;
    try {
      WeChatMessage msgFrom = ParseXML.parseXML2Obj(WeChatMessage.class, msg);
      WeChatMessage msgTo = new WeChatMessage();
      msgTo.setCreateTime(msgFrom.getCreateTime());
      msgTo.setFromUserName(msgFrom.getToUserName());
      msgTo.setToUserName(msgFrom.getFromUserName());
      msgTo.setMsgType(MsgUtil.REQ_MESSAGE_TYPE_TEXT);

      // 文本消息
      if (msgFrom.getMsgType().equals(MsgUtil.REQ_MESSAGE_TYPE_TEXT)) {
        msgTo.setContent("我们已经收到了你发送的：" + msgFrom.getContent());
      }
      // 图片消息
      else if (msgFrom.getMsgType().equals(MsgUtil.REQ_MESSAGE_TYPE_IMAGE)) {
        msgTo.setContent("我们已经收到了你发送的图片");
      }
      // 地理位置消息
      else if (msgFrom.getMsgType().equals(MsgUtil.REQ_MESSAGE_TYPE_LOCATION)) {
        msgTo.setContent("我们已经收到了你发送的地理位置");
      }
      // 链接消息
      else if (msgFrom.getMsgType().equals(MsgUtil.REQ_MESSAGE_TYPE_LINK)) {
        msgTo.setContent("我们已经收到了你发送的连接");
      }
      // 音频消息
      else if (msgFrom.getMsgType().equals(MsgUtil.REQ_MESSAGE_TYPE_VOICE)) {
        msgTo.setContent("我们已经收到了你发送的音频文件");
      }
      // 事件推送
      else if (msgFrom.getMsgType().equals(MsgUtil.REQ_MESSAGE_TYPE_EVENT)) {
        // 事件类型
        switch (msgFrom.getEvent()) {
          case MsgUtil.EVENT_TYPE_CLICK:
            // 自定义菜单点击事件
            // 事件KEY值，与创建自定义菜单时指定的KEY值对应
            // String eventKey = requestMap.get("EventKey");
            // System.out.println("EventKey=" + eventKey);
            // respContent = "Sunlight提示：您点击的菜单KEY是" + eventKey;
            break;
          case MsgUtil.EVENT_TYPE_SUBSCRIBE:
            // 关注事件
            String openID = msgFrom.getFromUserName();
            String api = CONST.API.API_GET_USERINFO;
            api = String.format(api, AccessTokenHolder.getAccessToken(), openID);
            OpenInfo user = NetUtil.httpRequest(api, "GET", null, OpenInfo.class);
            updateOpenInfo(user);
            msgTo.setContent(String.format(CONST.TEXT.TXT_WELCOME_WORDS, user.getNickname()));
            break;
          case MsgUtil.EVENT_TYPE_UNSUBSCRIBE:
            // 取消关注事件
            System.out.println("用户：" + msgFrom.getFromUserName() + "取消关注");
            break;
        }
      }
      return ParseXML.formatObject2XML(msgTo);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(e);
      respMessage = "有异常了。。。";
    }
    return respMessage;
  }

  private String getAnswer(String ques) {
    return null;
  }
  @Override
  public OpenInfo updateOpenInfo(OpenInfo user){
    OpenInfo old = commonRepository.findByWhere("where u.openid = '" + user.getOpenid() + "'", OpenInfo.class);
    if(null != old){
      old.setSubscribe(user.getSubscribe());
      old.setNickname(user.getNickname());
      old.setSex(user.getSex());
      old.setLanguage(user.getLanguage());
      old.setCity(user.getCity());
      old.setProvince(user.getProvince());
      old.setCountry(user.getCountry());
      old.setHeadimgurl(user.getHeadimgurl());
      old.setSubscribe_time(user.getSubscribe_time());
      old.setUnionid(user.getUnionid());
      old.setRemark(user.getRemark());
      old.setGroupid(user.getGroupid());
      return commonRepository.update(old);
    }else{
      return commonRepository.save(user);
    }
  }
}
