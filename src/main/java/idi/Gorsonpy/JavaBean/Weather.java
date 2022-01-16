package idi.Gorsonpy.JavaBean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Weather {
    @JSONField(name="日期",ordinal = 1)
    private String fxDate;
    @JSONField(name="当日最高温度",ordinal = 2)
    private String tempMax;
    @JSONField(name="当日最低温度",ordinal = 3)
    private String tempMin;
    @JSONField(name="白天天气情况",ordinal = 4)
    private String textDay;
}
