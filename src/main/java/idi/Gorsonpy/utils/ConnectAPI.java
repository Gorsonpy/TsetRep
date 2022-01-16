package idi.Gorsonpy.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import idi.Gorsonpy.JavaBean.Basic;
import idi.Gorsonpy.JavaBean.Weather;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

//连接API的类
public class ConnectAPI {
    //获取指定名称城市的基本信息并返回basic类
    public static Basic getBasicInf(String cityName) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();//创建一个HttpClient
        //URL中要用的参数key和cityName
        String key = "85a6330920bb48a399b5adcb2dc21ba0";
        //uri地址
        String uri = "https://geoapi.qweather.com/v2/city/lookup";

        //参数存放在params里头，并且用StringBuffer的类方法填入
        StringBuffer params = new StringBuffer();
        try {
            //中文用encode方法单独写入，特殊字符如“&”等最好也单独写入
            params.append("location=" + URLEncoder.encode(cityName, "utf-8"));
            params.append("&");
            params.append("key=" + key);
        } catch (UnsupportedEncodingException ue) {
            ue.printStackTrace();
        }

        //创建一个Get请求
        HttpGet httpget = new HttpGet(uri + "?" + params);
        //响应模型
        CloseableHttpResponse response = null;
        Basic basic = null;

        try {
            //配置信息
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(5000)//连接时限
                    .setConnectionRequestTimeout(5000)//请求时限
                    .setSocketTimeout(5000)//读写时限
                    .setRedirectsEnabled(true).build();//设置允许重定向，默认为true

            //应用设置
            httpget.setConfig(requestConfig);

            //由客户端发送get请求
            response = httpClient.execute(httpget);

            //从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                //responseEntity存放的就是JSON形式返回信息
                String json = EntityUtils.toString(responseEntity);//用toString方法变为String类型

                //先把json形式的字符串转化为json对象
                JSONObject jsonObject = JSONObject.parseObject(json);

                //拿出jsonObject中的location(location是JSONArray类型的)
                JSONArray jsonArray = jsonObject.getJSONArray("location");
                basic = new Basic();
                basic.setName((jsonArray.getJSONObject(0)).getString("name"));
                basic.setLon((jsonArray.getJSONObject(0)).getString("lon"));
                basic.setLat((jsonArray.getJSONObject(0)).getString("lat"));
                basic.setCity_ID((jsonArray.getJSONObject(0).getString("id")));
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭客户端和响应
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return basic;
    }

    //从API请求指定城市三日天气信息
    public static ArrayList<Weather> getWeatherInf(String cityId){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String key="85a6330920bb48a399b5adcb2dc21ba0";
        String uri="https://devapi.qweather.com/v7/weather/3d";
        ArrayList<Weather> weatherArrayList=null;

        StringBuffer params=new StringBuffer();
        params.append("key="+key);
        params.append("&");
        params.append("location="+cityId);

        //创建一个Get请求
        HttpGet httpget = new HttpGet(uri + "?" + params);
        //响应模型
        CloseableHttpResponse response = null;
        try {
            //配置信息
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(5000)//连接时限
                    .setConnectionRequestTimeout(5000)//请求时限
                    .setSocketTimeout(5000)//读写时限
                    .setRedirectsEnabled(true).build();//设置允许重定向，默认为true

            //应用设置
            httpget.setConfig(requestConfig);

            //由客户端发送get请求
            response = httpClient.execute(httpget);

            //从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                weatherArrayList=new ArrayList<>();
                //responseEntity存放的就是JSON形式返回信息
                String json = EntityUtils.toString(responseEntity);//用toString方法变为String类型

                //先把json形式的字符串转化为json对象
                JSONObject jsonObject = JSONObject.parseObject(json);

                //拿出jsonObject中的location(location是JSONArray类型的)
                JSONArray jsonArray = jsonObject.getJSONArray("daily");
                for(int i = 0; i < jsonArray.size(); ++ i){
                    Weather weather = new Weather();
                    weather.setFxDate(jsonArray.getJSONObject(i).getString("fxDate"));
                    weather.setTempMax(jsonArray.getJSONObject(i).getString("tempMax"));
                    weather.setTempMin(jsonArray.getJSONObject(i).getString("tempMin"));
                    weather.setTextDay(jsonArray.getJSONObject(i).getString("textDay"));
                    weatherArrayList.add(weather);
                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭客户端和响应
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return weatherArrayList;
    }
}


