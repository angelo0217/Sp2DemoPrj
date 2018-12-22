package com.demo.basic.config.oauthException;

import com.demo.basic.RtnCode;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
/**
 * 自訂義錯誤
 * 參考網址https://blog.csdn.net/dandandeshangni/article/details/80472147
 *
 * Created on 2018-12-19
 *
 * @author dean
 * @email loveangelo0217@gmail.com
 * @since 1.0
 */
public class DemoOauthExceptionSerializer extends StdSerializer<DemoOauthException> {
    public DemoOauthExceptionSerializer() {
        super(DemoOauthException.class);
    }

    @Override
    public void serialize(DemoOauthException value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        gen.writeStartObject();
        gen.writeNumberField("code", RtnCode.AUTH_ERROR);
        gen.writeStringField("msg", "("+String.valueOf(value.getHttpErrorCode())+")" + value.getMessage());
        gen.writeStringField("path", request.getServletPath());
        if (value.getAdditionalInformation()!=null) {
            for (Map.Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
                String key = entry.getKey();
                String add = entry.getValue();
                gen.writeStringField(key, add);
            }
        }
        gen.writeEndObject();
    }
}