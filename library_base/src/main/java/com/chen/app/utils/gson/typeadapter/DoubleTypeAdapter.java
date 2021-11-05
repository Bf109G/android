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
public class DoubleTypeAdapter extends TypeAdapter<Double> {

    @Override
    public void write(JsonWriter out, Double value) throws IOException {
        try {
            if (value == null){
                value = 0D;
            }
            out.value(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Double read(JsonReader in) throws IOException {
        try {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                Log.e("TypeAdapter", "null is not a number");
                return 0D;
            }
            if (in.peek() == JsonToken.BOOLEAN) {
                boolean b = in.nextBoolean();
                Log.e("TypeAdapter", b + " is not a number");
                return 0D;
            }
            if (in.peek() == JsonToken.STRING) {
                String str = in.nextString();
                if (NumberUtil.isFloatOrDouble(str)){
                    return Double.parseDouble(str);
                } else {
                    Log.e("TypeAdapter", str + " is not a number");
                    return 0D;
                }
            } else {
                Double value = in.nextDouble();
                return value == null ? 0D : value;
            }
        } catch(Exception e){
            Log.e("TypeAdapter", e.getMessage(), e);
        }
        return 0D;
    }
}
