package com.eagle.net.res;

import com.eagle.utils.SDLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class Ret {
    private static final String RETCODE = "retcode";
    private static final String DESC = "desc";
    private static final String TAG = "Ret";
    public static final String RETCODE_ERR_DATA = "-1";
    public static final String RETCODE_OK = "0";

    private String ret;
    private String desc;

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;

    }


    @Override
    public String toString() {
        return "Ret{" +
                "ret='" + ret + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }


    public void build(String json){
        JSONObject o = converToJson(json);
        if(o == null){
            setRet(RETCODE_ERR_DATA);
        }else {
            setRet(getJsonString(o,RETCODE));
            setDesc(getJsonString(o,DESC));
            onJSONObj(o);
        }
    }

    protected abstract void onJSONObj(JSONObject object);
    /*********************************************/
    public static JSONObject converToJson(String info) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(info.toString());
        } catch (JSONException e) {
            SDLog.e(TAG,"converToJson " + e);
        }
        return jsonObject;
    }

    public static JSONObject getJSONObj(JSONObject source, String name) {
        if (source != null) {
            try {
                return source.getJSONObject(name);
            } catch (JSONException e) {
                SDLog.e(TAG,"getJSONObj " + e);
            }
        }
        return null;
    }

    public static JSONObject getJSONObj(JSONArray array, int idx) {
        if (array != null) {
            try {
                return array.getJSONObject(idx);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static JSONArray getArray(JSONObject jsonObject, String name) {
        try {
            return jsonObject.getJSONArray(name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从json中取得name对应的 string value
     *
     * @param jsonObject
     *            jsonobj
     * @param name
     *            tagname
     * @return value
     */
    public static String getJsonString(JSONObject jsonObject, String name) {
        String value = "";
        if (jsonObject.has(name)) {
            try {
                value = jsonObject.getString(name);
            } catch (JSONException e) {
                if (!"data".equals(name)) {
                    SDLog.e(TAG,"getJsonString " + e);

                }
            }
        }
        return value;
    }

    public static JSONObject getJsonObj(JSONArray jsonArray, int i) {

        try {
            return (JSONObject) jsonArray.get(i);
        } catch (JSONException e) {
            SDLog.d(TAG,"getJsonObj " + e);
        }
        return null;
    }

    public static JSONArray getJsonArray(String data) {
        JSONArray array = null;
        try {
            array = new JSONArray(data);
        } catch (JSONException e) {
            SDLog.e(TAG,"JSONArray " + e);
        }
        return array;
    }

    /**
     * 从json中取出name对应的long型value
     *
     * @param jsonObject
     *            jsonobj
     * @param name
     *            tagname
     * @return long型value
     */
    public static long getJsonLong(JSONObject jsonObject, String name) {
        if (jsonObject.has(name)) {
            try {
                return jsonObject.getLong(name);
            } catch (JSONException e) {
                SDLog.e(TAG,"getJsonLong "  + e);
            }
        }
        return 0;
    }

    public static int getJsonInt(JSONObject jsonObject, String name) {
        if (jsonObject.has(name)) {
            try {
                return jsonObject.getInt(name);
            } catch (JSONException e) {
                SDLog.e(TAG,"getJsonInt "  + e);
            }
        }
        return -1;
    }

    public static boolean getJsonBool(JSONObject jsonObject, String name) {
        if (jsonObject.has(name)) {
            try {
                return jsonObject.getBoolean(name);
            } catch (JSONException e) {
                SDLog.e(TAG,"getJsonBool "  + e);
            }
        }
        return false;
    }

}