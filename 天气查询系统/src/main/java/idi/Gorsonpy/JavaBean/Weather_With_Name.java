package idi.Gorsonpy.JavaBean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;


//带有城市名字的天气类
@Data
public class Weather_With_Name {
    @JSONField(name = "城市", ordinal = 1)
    private String name;
    @JSONField(name = "日期", ordinal = 2)
    private String fxDate;
    @JSONField(name = "当日最高温度", ordinal = 3)
    private String tempMax;
    @JSONField(name = "当日最低温度", ordinal = 4)
    private String tempMin;
    @JSONField(name = "白天天气情况", ordinal = 5)
    private String textDay;
}
