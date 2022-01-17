package idi.Gorsonpy.JavaBean;

import com.alibaba.fastjson.annotation.JSONField;

import java.sql.Timestamp;

//命名为J_Order以避免和依赖中的MySQL关键词重复
public class J_Order {
    //类似于map的映射，JSON标签通过name对应，ordinal表示显示顺序（默认按照name的字母序），还可以自定义时间的输出
    @JSONField(name = "订单编号", ordinal = 1)
    private long id;
    @JSONField(name = "下单时间", format = "yyyy-MM-dd HH:mm:ss", ordinal = 2)
    private Timestamp time;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Timestamp getTime() {
        return time;
    }

}
