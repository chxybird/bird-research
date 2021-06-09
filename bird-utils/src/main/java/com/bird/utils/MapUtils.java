package com.bird.utils;

/**
 * @Author lipu
 * @Date 2021/6/1 14:50
 * @Description 地图计算
 */
public class MapUtils {

    /**
     * 地球半径,单位 km
     */
    private static final double EARTH_RADIUS = 6378.137;


    /**
     * @Author lipu
     * @Date 2021/6/1 14:54
     * @Description 计算位置 误差不能大于1
     */
    public static boolean isRange(Double x1,Double y1,Double x2,Double y2){
        // 纬度
        double lat1 = Math.toRadians(x1);
        double lat2 = Math.toRadians(x2);
        // 经度
        double lng1 = Math.toRadians(y1);
        double lng2 = Math.toRadians(y2);
        // 纬度之差
        double a = lat1 - lat2;
        // 经度之差
        double b = lng1 - lng2;
        // 计算两点距离的公式
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(b / 2), 2)));
        // 弧长乘地球半径, 返回单位: 千米
        s =  s * EARTH_RADIUS;
        return s>1.0;
    }


}
