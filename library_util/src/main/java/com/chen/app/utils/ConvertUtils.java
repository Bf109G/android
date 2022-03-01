package com.chen.app.utils;

import com.chen.app.constant.MemoryConstants;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Author by chennan
 * Date on 2022/3/1
 * Description
 */
public final class ConvertUtils {

    private static final int BUFFER_SIZE = 8192;

    private ConvertUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * Size of byte to fit size of memory.
     * <p>to three decimal places</p>
     *
     * @param byteSize Size of byte.
     * @return fit size of memory
     */
    public static String byte2FitMemorySize(final long byteSize) {
        return byte2FitMemorySize(byteSize, 3);
    }

    /**
     * Size of byte to fit size of memory.
     * <p>to three decimal places</p>
     *
     * @param byteSize  Size of byte.
     * @param precision The precision
     * @return fit size of memory
     */
    public static String byte2FitMemorySize(final long byteSize, int precision) {
        if (precision < 0) {
            throw new IllegalArgumentException("precision shouldn't be less than zero!");
        }
        if (byteSize < 0) {
            throw new IllegalArgumentException("byteSize shouldn't be less than zero!");
        } else if (byteSize < MemoryConstants.BYTE) {
            return String.format("%." + precision + "fB", (double) byteSize);
        } else if (byteSize < MemoryConstants.MB) {
            return String.format("%." + precision + "fKB", (double) byteSize / MemoryConstants.KB);
        } else if (byteSize < MemoryConstants.GB) {
            return String.format("%." + precision + "fMB", (double) byteSize / MemoryConstants.MB);
        } else {
            return String.format("%." + precision + "fGB", (double) byteSize / MemoryConstants.GB);
        }
    }

    /**
     * Output stream to input stream.
     */
    public static ByteArrayInputStream output2InputStream(final OutputStream out) {
        if (out == null) return null;
        return new ByteArrayInputStream(((ByteArrayOutputStream) out).toByteArray());
    }

    /**
     * Input stream to bytes.
     */
    public static byte[] inputStream2Bytes(final InputStream is) {
        if (is == null) return null;
        return input2OutputStream(is).toByteArray();
    }

    /**
     * Input stream to output stream.
     */
    public static ByteArrayOutputStream input2OutputStream(final InputStream is) {
        if (is == null) return null;
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            byte[] b = new byte[BUFFER_SIZE];
            int len;
            while ((len = is.read(b, 0, BUFFER_SIZE)) != -1) {
                os.write(b, 0, len);
            }
            return os;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
} 
