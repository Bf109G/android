package com.chen.app.utils.gson.typeadapter;

import android.util.Log;

import com.chen.app.utils.gson.NumberUtil;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Author by chennan
 * Date on 2021/11/3
 * Description
 */
public class LongTypeAdapter extends TypeAdapter<Long> {
    @Override
    public void write(JsonWriter out, Long value) throws IOException {
        try {
            if (value == null){
                value = 0L;
            }
            out.value(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Long read(JsonReader in) throws IOException {
        try {
            Long value;
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                Log.e("TypeAdapter", "null is not a number");
                return 0L;
            }
            if (in.peek() == JsonToken.BOOLEAN) {
                boolean b = in.nextBoolean();
                Log.e("TypeAdapter", b + " is not a number");
                return 0L;
            }
            if (in.peek() == JsonToken.STRING) {
                String str = in.nextString();
                if (NumberUtil.isIntOrLong(str)){
                    return Long.parseLong(str);
                } else {
                    Log.e("TypeAdapter", str + " is not a int number");
                    return 0L;
                }
            } else {
                value = in.nextLong();
                return value;
            }
        } catch (Exception e) {
            Log.e("TypeAdapter", "Not a number", e);
        }
        return 0L;
    }
}
