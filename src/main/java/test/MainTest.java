package test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weixinserver.model.AccessToken;
import com.weixinserver.model.CONST;
import com.weixinserver.model.UploadData;
import com.weixinserver.model.UploadModel;
import com.weixinserver.model.WechatProperty;

public class MainTest {
  public static void main(String[] args) {

    testUpload();
  }

  static void testUpload() {
    UploadModel data = new UploadModel();
    data.setProCode("PRO002");
    data.setUsername("user2");
    data.setPassword("user2");
    data.setDataList(new ArrayList<UploadData>());
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.DAY_OF_MONTH, 1);
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    for (int i = 0; i < 1000; i++) {
        UploadData ud = new UploadData();

        ud.setAmm_code("PRO002" + "AMM" + i);
        ud.setAmm_name("电表" + i);
        ud.setData(new BigDecimal(i * 10));
        ud.setDate(sf.format(cal.getTime()));
        data.getDataList().add(ud);
        cal.add(Calendar.HOUR, 1);
    }

    try {
        URL url = new URL("http://localhost/WeiXinServer/upload/uploadData");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(1000);
        conn.setRequestMethod("POST");

        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setUseCaches(false);

        conn.setRequestProperty("Content-type", "application/json");
        conn.connect();
        OutputStream os = conn.getOutputStream();
        Gson gson = new Gson();
        String json = gson.toJson(data);
        conn.getOutputStream().write(json.getBytes("utf-8"));// 输入参数
        InputStream inStream = conn.getInputStream();
        List<Byte> bytes = new ArrayList<Byte>();
        Byte b;
        Scanner scanner = new Scanner(inStream, "UTF-8");
        String text = scanner.useDelimiter("\\A").next();
        System.out.println(text);

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        System.out.println(123);
    }
}

  void testWeiXinMenu() {
    try {
      ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-*.xml");
      WechatProperty props = (WechatProperty) ctx.getBean("wechatProps");
      String url = String.format(CONST.API.API_GET_ACCESS_TOKEN, props.getAppId(), props.getAppSecret());
      URL u = new URL(url);
      URLConnection connection = u.openConnection();
      connection.connect();
      BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      String access_token = "";
      String line = null;
      while (null != (line = reader.readLine())) {
        access_token += line;
      }
      connection.getInputStream().close();
      Gson json = new GsonBuilder().disableHtmlEscaping().create();
      AccessToken accessToken = json.fromJson(access_token, AccessToken.class);
      System.out.println(accessToken.getAccess_token());
      url = String.format(CONST.API.API_SET_CUSTOM_MENU, accessToken.getAccess_token());
      u = new URL(url);
      connection = u.openConnection();
      connection.setDoOutput(true);
      OutputStream os = connection.getOutputStream();
      String jsonText = " { \"button\":[  { \"type\":\"click\", \"name\":\"歌曲\", \"key\":\"V1001_TODAY_MUSIC\" }, { \"name\":\"菜单\", \"sub_button\":[ { \"type\":\"view\", \"name\":\"百度搜索\", \"url\":\"http://www.baidu.com/\" }, { \"type\":\"view\", \"name\":\"视频\", \"url\":\"http://v.qq.com/\" }, { \"type\":\"click\", \"name\":\"赞一下我们\", \"key\":\"V1001_GOOD\" }] }] }";
      os.write(jsonText.getBytes());
      os.close();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      System.out.println(123);
    }
  }
}
