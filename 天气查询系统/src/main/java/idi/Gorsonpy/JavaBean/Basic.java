package idi.Gorsonpy.JavaBean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data //lombok的Data可以自动添加getter、setter方法等等
public class Basic {

    @JSONField(name = "城市名称", ordinal = 2)
    private String name;
    @JSONField(name = "城市id", ordinal = 1)
    private String city_ID;
    @JSONField(name = "所在纬度", ordinal = 3)
    private String lat;//纬度
    @JSONField(name = "所在经度", ordinal = 4)
    private String lon;//精度
}
