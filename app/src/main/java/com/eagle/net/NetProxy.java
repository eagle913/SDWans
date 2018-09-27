package com.eagle.net;

import com.eagle.utils.SDLog;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.Map;

public abstract class NetProxy implements IHttpProxy {

    private static final String TAG = NetProxy.class.getSimpleName();
    private URLConnection connection;
    private String url;
    private String param;
    private StringRes response;


    public void setResponse(IResponse response){
        //TODO
    }

    protected abstract String buildUrl(String url,Map<String ,String>params);

    protected abstract void setMethed(URLConnection connection);
    protected abstract void setRequestProperty(URLConnection connection,Map<String,String> headers);
    protected void writeReqData(URLConnection connection){}

    protected  String buildParam(Map<String ,String>params,Map<String,String> headers){
            return "";
    }
    @Override
    public InputStream excute(String url, Map<String, String> param, Map<String, String> header) {
        return excute(url,buildParam(param,header),header);
    }

    @Override
    public InputStream excute(String strUrl, String param, Map<String, String> header) {

        String result = null;
        URL url = null;
        HttpURLConnection connection = null;
        InputStreamReader in = null;
        try {
            SDLog.d(TAG,"doPostRequest " + strUrl
                    + " param = " + param);
            url = new URL(strUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            connection.setRequestProperty("Charset", "utf-8");
            connection.setConnectTimeout(TIME_OUT);
            connection.setReadTimeout(TIME_OUT);
            DataOutputStream dop = new DataOutputStream(connection
                    .getOutputStream());
            dop.write(param.getBytes("utf-8"));
            dop.flush();
            dop.close();
            int code = connection.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                in = new InputStreamReader(connection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(in);
                StringBuffer strBuffer = new StringBuffer();
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    strBuffer.append(line);
                }
                result = strBuffer.toString();
                SDLog.d("doPostRequest", "strUrl " + strUrl + " param " + param
                        + " result " + result);
            }else
            {
                response.onConnectError(IResponse.ERR_HOST, "");
            }
        } catch (UnknownHostException e) {
            response.onConnectError(IResponse.ERR_HOST, "");
            SDLog.w("AbsHttpAction",e);
        } catch (FileNotFoundException e) {
            response.onConnectError(IResponse.ERR_HOST, "");
        } catch (SocketTimeoutException e) {
            response.onConnectError(IResponse.ERR_TIME_OUT, "");
            SDLog.w("AbsHttpAction",e);
        } catch(SocketException e){
            response.onConnectError(IResponse.ERR_TIME_OUT, "");
            SDLog.w("AbsHttpAction",e);
        } catch (Exception e) {
            response.onConnectError(IResponse.ERR_EXCEPTION, "");
            SDLog.w("AbsHttpAction",e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (result != null) {
                response.onRes(result);
            }
        }



        return null;
    }


    public void asynExcuteGet(String strUrl,StringRes response){

    }

    public void excuteGet(String strUrl,StringRes response){
        String result = null;
        URL url = null;
        HttpURLConnection connection = null;
        InputStreamReader in = null;
        try {
            url = new URL(strUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            connection.setRequestProperty("Charset", "utf-8");
            connection.setConnectTimeout(IResponse.ERR_TIME_OUT);
            connection.setReadTimeout(IResponse.ERR_TIME_OUT);
            in = new InputStreamReader(connection.getInputStream());
            int code = connection.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                BufferedReader bufferedReader = new BufferedReader(in);
                StringBuffer strBuffer = new StringBuffer();
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    strBuffer.append(line);
                }
                result = strBuffer.toString();
            }
        } catch (UnknownHostException e) {
            response.onConnectError(IResponse.ERR_HOST, "");
            SDLog.w("AbsHttpAction",e);
        } catch (FileNotFoundException e) {
            response.onConnectError(IResponse.ERR_EXCEPTION, "");
        } catch (SocketTimeoutException e) {
            response.onConnectError(IResponse.ERR_TIME_OUT, "");
            SDLog.w("AbsHttpAction",e);
        }  catch(SocketException e){
            response.onConnectError(IResponse.ERR_TIME_OUT, "");
            SDLog.w("AbsHttpAction",e);
        } catch (Exception e) {
            response.onConnectError(IResponse.ERR_EXCEPTION, "");
            SDLog.w("AbsHttpAction",e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (result != null) {
                response.onRes(result);
            }

        }
        SDLog.d(TAG,"getReq " + strUrl + " result " + result);
    }

}
