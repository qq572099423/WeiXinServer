package com.weixinserver.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.weixinserver.entity.Ammeter;
import com.weixinserver.entity.OpenInfo;
import com.weixinserver.entity.Project;
import com.weixinserver.model.ServiceRequest;
import com.weixinserver.model.WechatProperty;
import com.weixinserver.service.CommonService;
import com.weixinserver.service.CoreService;

@Controller
@RequestMapping("/OpenWechat")
public class OpenWechat {
  @Autowired
  private WechatProperty props;
  @Autowired
  private CoreService coreService;
  @Autowired
  private CommonService commonService;

  @ResponseBody
  @RequestMapping(value = "/req.wx", method = RequestMethod.GET)
  public String get(ServiceRequest sq) throws AesException {
    String[] values = {props.getToken(), sq.getTimestamp(), sq.getNonce()};
    // 字典序排序
    Arrays.sort(values);
    String value = values[0] + values[1] + values[2];
    @SuppressWarnings("deprecation")
    String sign = DigestUtils.shaHex(value);

    if (sq.getSignature().equals(sign)) {
      // 验证成功返回 ehcostr
      return sq.getEchostr();
    } else {
      return "";
    }
  }

  @ResponseBody
  @RequestMapping(value = "/req.wx", method = RequestMethod.POST, produces = "application/xml;charset=UTF-8")
  public void post(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");

    // 微信加密签名
    String msg_signature = request.getParameter("msg_signature");
    // 时间戳
    String timestamp = request.getParameter("timestamp");
    // 随机数
    String nonce = request.getParameter("nonce");

    // 从请求中读取整个post数据
    InputStream inputStream = request.getInputStream();
    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    StringBuilder sb = new StringBuilder();
    String line = null;
    try {
      while ((line = reader.readLine()) != null) {
        sb.append(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        inputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
      String postData = sb.toString();
      System.out.println(postData);

      String msg = "";
      WXBizMsgCrypt wxcpt = null;
      try {
        wxcpt = new WXBizMsgCrypt(props.getToken(), props.getEncodingAesKey(), props.getAppId());
        // 解密消息
        msg = wxcpt.decryptMsg(msg_signature, timestamp, nonce, postData);
      } catch (AesException e) {
        e.printStackTrace();
      }
      System.out.println("msg=" + msg);

      // 调用核心业务类接收消息、处理消息
      String respMessage = coreService.processRequest(msg);
      System.out.println("respMessage=" + respMessage);

      String encryptMsg = "";
      try {
        // 加密回复消息
        encryptMsg = wxcpt.encryptMsg(respMessage, timestamp, nonce);
      } catch (AesException e) {
        e.printStackTrace();
      }

      // 响应消息
      PrintWriter out = response.getWriter();
      out.print(encryptMsg);
      out.close();

    }
  }

  /*
   * public String get(HttpServletRequest request) throws UnsupportedEncodingException,
   * IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException,
   * AesException { 消息的接收、处理、响应
   * 
   * // 将请求、响应的编码均设置为UTF-8（防止中文乱码） request.setCharacterEncoding("UTF-8");
   * 
   * // 调用核心业务类接收消息、处理消息 String respMessage = MsgService.processRequest(request, props);//
   * CoreService.processRequest(request);
   * 
   * return respMessage; }
   */
  @RequestMapping(value = "/authorize.wx", method = RequestMethod.GET)
  public @ResponseBody String authorize(HttpServletRequest req, HttpServletResponse rep) {
    System.out.println(123);
    return "ok";
  }
}
