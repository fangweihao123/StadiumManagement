package com.example.po.stadiummanagement3.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.po.stadiummanagement3.Activity.MainActivity;
import com.example.po.stadiummanagement3.R;
import com.example.po.stadiummanagement3.WebService.HttpService;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 13701 on 2017/12/3.
 */

public class LoginFragment extends Fragment {
    @BindView(R.id.text_input_password)
    public EditText t_pass;
    @BindView(R.id.input_account)
    public EditText t_id;
    @BindView(R.id.btn_login)
    public AppCompatButton loginButton;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.login_fragment,container,false);
        ButterKnife.bind(this,v);
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try
                {
                    String account = t_id.getText().toString();
                    if(account.length() == 7 || account.length() == 11){
                        boolean flag = loginValidate(t_id.getText().toString(), t_pass.getText().toString());
                        if(flag){
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            getActivity().startActivity(intent);
                        }else{
                            Toast.makeText(getActivity(),"oops,something went wrong",Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(getContext(),"ops,your account is not formal",Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        });
        return v;
    }

    //进行登录操作
    private boolean loginValidate(String username,String passwd) throws Exception
    {
        final String data="option=credential&Ecom_User_ID="+t_id.getText()+"&Ecom_Password="+t_pass.getText()+"&submit=%E7%99%BB%E5%BD%95";
        final String cookie="SERVERNAME=s62";
        // final String html2=HttpUtil.sendGetRequest(url , false , null ,"gbk" );

        final String res=HttpUtil.sendGetRequest("http://4m3.tongji.edu.cn/eams/login.action",false,null,"UTF-8");
        final String orignCookie=Cookie_Class.J_SessionId;
        final String response=HttpUtil.sendGetRequest("http://4m3.tongji.edu.cn/eams/login.action",true,orignCookie,"UTF-8");
        final String response1=HttpUtil.sendGetRequest("http://4m3.tongji.edu.cn/eams/samlCheck",true,Cookie_Class.J_SessionId,"UTF-8");
        //处理samlrequest
        final String samlRequest=getSAMLRequest(response1);
        final String SAMLurl="https://ids.tongji.edu.cn:8443/nidp/saml2/sso?"+samlRequest;

        final String response2=HttpUtil.sendGetRequest(SAMLurl,true,Cookie_Class.J_SessionId,"UTF-8");
        final String response3=HttpUtil.sendPostRequest("https://ids.tongji.edu.cn:8443/nidp/saml2/sso?id=3954&sid=0&option=credential&sid=0","",true,Cookie_Class.J_SessionId,"UTF-8");
        final String response4=HttpUtil.sendPostRequest("https://ids.tongji.edu.cn:8443/nidp/app/login?sid=0&sid=0",data,true,Cookie_Class.J_SessionId,"UTF-8");
        final String response5=HttpUtil.sendGetRequest("https://ids.tongji.edu.cn:8443/nidp/app?sid=0",true, Cookie_Class.J_SessionId,"UTF-8");
        final String data2="";

        final String response7=HttpUtil.sendGetRequest("http://4m3.tongji.edu.cn/eams/security/my.action",true, orignCookie,"UTF-8");
        final String response6=HttpUtil.sendPostRequest("http://4m3.tongji.edu.cn/eams/security/my.action",data2,true, orignCookie,"UTF-8");

        final String html_final=response5;

        if(getName(html_final).equals(username)){
            //String a = HttpUtil.sendPostRequest(HttpService.junUrl+"/number/login","studentNumber=1552706",false,null,"UTF-8");
            Intent intent = new Intent(getContext(),MainActivity.class);
            OkHttpClient client =  new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();
            RequestBody requestBody = new FormBody.Builder().add("studentNumber",getName(html_final)).build();
            Request request = new Request.Builder().post(requestBody).url("192.168.1.105:8888/number/login").build();
            Response response8 = client.newCall(request).execute();
            String s = response8.body().string();
            getContext().startActivity(intent);
            return true;
        }else{
            Toast.makeText(getContext(),"ops,something goes wrong",Toast.LENGTH_SHORT).show();
            return false;
        }

        /*getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(),"欢迎你，"+getName(html_final),Toast.LENGTH_LONG).show();
            }
        });
        return true;*/
    }


    private String getName(String html)
    {
        int j=html.indexOf("<div id=\"username-namclient\" class=\"user-menu menu-open\">");
        j+="<div id=\"username-namclient\" class=\"user-menu menu-open\">".length();
        for (int i=j;i<=html.length()-1;i++)
        {

            if (html.charAt(i)=='<')
            {
                return html.substring(j,i);
            }
        }
        return "";
    }


    private String getSAMLRequest(String html)
    {
        String s1="";
        for (int i=0;i<=html.length()-12;i++)
        {
            s1=html.substring(i,i+11);
            if (s1.equals("SAMLRequest"))
            {
                html=html.substring(i);
                break;
            }
        }
        char[] c_array=html.toCharArray();
        int pos=0;
        for (int i=0;i<=html.length()-1;i++)
        {
            if (c_array[i]=='>')
            {
                pos=i;
                break;
            }
        }
        html=html.substring(0,pos-1);
        return html;
    }


}



class Cookie_Class
{
    public static String J_SessionId="";
}



class HttpUtil
{
    //get Jsession
    public static String getJsession(String cookie)
    {
        if (cookie.startsWith("JSESSIONID"))
        {
            char[] array=cookie.toCharArray();
            for (int i=0;i<=cookie.length()-1;i++)
            {
                if (cookie.charAt(i)==';')
                {
                    cookie=cookie.substring(0,i);
                    break;
                }
            }

            return cookie;
        }
        return "";
    }




    static HttpURLConnection conn;



    /**
     * 向对应的网址发送get请求,以String的形式返回服务器的相应
     *
     * @author cjyong at 2017/3/5
     * @param url 发送请求的网址
     * @param usecookie 是否使用cookie
     * @param cookie 需要携带的cookie
     * @param encoding 编码格式
     * @return 以string的形式返回服务器的响应
     * @throws Exception
     */





    public static String sendGetRequest(final String url,final boolean usecookie,final String cookie,final String encoding) throws Exception
    {
        FutureTask<String> task = new FutureTask<String>(
                new Callable<String>()
                {
                    @Override
                    public String call() throws Exception
                    {




                        URL turl = new URL(url);
                        conn = (HttpURLConnection) turl.openConnection();

                        conn.setRequestMethod("GET");
                        conn.setDoOutput(true);
                        //设置时间限制,抛出异常
                        conn.setConnectTimeout(5000);
                        conn.setReadTimeout(5000);
                        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)");
                        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                        if(usecookie)
                            conn.setRequestProperty("Cookie", cookie);
                        InputStream is = conn.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(is,encoding));
                        StringBuilder sb = new StringBuilder();
                        String line = null;
                        while((line = reader.readLine())!= null)
                            sb.append(line+"\n");


                        Map<String , List<String>> map=conn.getHeaderFields();
                        List<String> Jsession=map.get("Set-Cookie");
                        String jsessionString="",s="";
                        if (Jsession.size()>0)
                        {
                            for (int i=Jsession.size()-1;i>=0;i--)
                            {
                                s=getJsession(Jsession.get(i));
                                if (!s.equals(""))
                                {
                                    Cookie_Class.J_SessionId=s;
                                    break;
                                }

                            }
                        }


                        return sb.toString();
                    }
                });
        //格外进行一个线程进行网络操作,防止堵塞
        new Thread(task).start();
        return task.get();
    }

    /**
     * 向对应的网址发送post请求,以String的形式返回服务器的相应
     *
     * @author cjyong at 2017/3/5
     * @param url 发送请求的网址
     * @param data 发送post请求携带的数据
     * @param usecookie 是否使用cookie
     * @param cookie 需要携带的cookie
     * @param encoding 编码格式
     * @return 以string的形式返回服务器的响应
     * @throws Exception
     */
    public static String sendPostRequest(final String url,final String data,final boolean usecookie,final String cookie,final String encoding) throws Exception
    {
        FutureTask<String> task = new FutureTask<String>(
                new Callable<String>()
                {
                    @Override
                    public String call() throws Exception
                    {
                        URL turl = new URL(url);
                        conn = (HttpURLConnection) turl.openConnection();
                        conn.setRequestMethod("POST");
                        conn.setDoOutput(true);
                        //设置时间限制,抛出异常
                        conn.setConnectTimeout(5000);
                        conn.setReadTimeout(5000);
                        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)");
                        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                        if(usecookie)
                            conn.setRequestProperty("Cookie", cookie);
                        OutputStream outStream = conn.getOutputStream();
                        outStream.write(data.getBytes());
                        outStream.flush();
                        outStream.close();
                        InputStream is = conn.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(is,encoding));
                        StringBuilder sb = new StringBuilder();
                        String line = null;
                        while((line = reader.readLine())!= null)
                            sb.append(line+"\n");



                        String Cookie=conn.getHeaderField("Set-Cookie");
                        Map<String , List<String>> map=conn.getHeaderFields();
                        List<String> Jsession=map.get("Set-Cookie");
                        String jsessionString="",s="";
                        if (Jsession.size()>0)
                        {
                            for (int i=Jsession.size()-1;i>=0;i--)
                            {
                                s=getJsession(Jsession.get(i));
                                if (!s.equals(""))
                                {
                                    Cookie_Class.J_SessionId=s;
                                    break;
                                }

                            }
                        }





                        return sb.toString();
                    }
                });
        //格外进行一个线程进行网络操作,防止堵塞
        new Thread(task).start();
        return task.get();
    }

}
