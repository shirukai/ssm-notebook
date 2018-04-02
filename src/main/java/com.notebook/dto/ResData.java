package com.notebook.dto;

import java.util.HashMap;
import java.util.Map;

public class ResData {
    public ResData() {
    }

    /**
     * 根据状态返回结果
     *
     * @param state 1 执行成功 0 执行失败
     * @return map
     */
    public static Map<String, Object> resData(int state) {
        Map<String, Object> map = new HashMap<String, Object>();
        String data = state != 0 ? "执行成功" : "执行失败";
        map.put("state", state);
        map.put("data", data);
        return map;
    }

    /**
     * 自定义成功数据
     *
     * @param state   状态
     * @param object 成功或错误信息
     * @return 状态和数据
     */
    public static Map<String, Object> resData(int state, Object object) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", state);
        map.put("data", object);
        return map;
    }


}
