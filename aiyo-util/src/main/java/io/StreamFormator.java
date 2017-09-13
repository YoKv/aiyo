package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamFormator {
    public StreamFormator() {
    }

    static public String getString(InputStream stream) {
        try {
            InputStreamReader isr = new InputStreamReader(stream);
            System.out.println("stream charset:" + isr.getEncoding());
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String tmp = br.readLine();
            while (tmp != null) {
                sb.append(tmp);
                sb.append("\n");
                tmp = br.readLine();
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    static public String getString(InputStream stream, String charset) {
        StringBuilder sb = new StringBuilder();
        try {
            InputStreamReader isr = new InputStreamReader(stream, charset);
            BufferedReader br = new BufferedReader(isr);
            String tmp = br.readLine();
            while (tmp != null) {
                sb.append(tmp);
                sb.append("\n");
                tmp = br.readLine();
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return sb.toString();
        }
    }
}