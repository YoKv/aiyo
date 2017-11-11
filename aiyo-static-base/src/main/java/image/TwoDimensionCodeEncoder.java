package image;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

public class TwoDimensionCodeEncoder {

    public static BufferedImage get2DCode(String content, int width,
                                          int height, String charset) {

        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
        hints.put(EncodeHintType.CHARACTER_SET, charset);

        BitMatrix matrix = null;

        try {

            matrix = new MultiFormatWriter().encode(content,
                    BarcodeFormat.QR_CODE, width, height, hints);

        } catch (WriterException e) {

            e.printStackTrace();

        }

        return MatrixToImageWriter.toBufferedImage(matrix);
    }

    public static BufferedImage get2DCode(String content, int width, int height) {
        return get2DCode(content, width, height, "utf-8");
    }

    public static byte[] getBarCode(String content, int width, int height) {
        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix matrix = null;
        try {

            matrix = new MultiFormatWriter().encode(content,
                    BarcodeFormat.CODE_39, width, height, hints);

        } catch (WriterException e) {
            e.printStackTrace();
        }

        return BufferedImage2Bytes(MatrixToImageWriter.toBufferedImage(matrix));
    }

    public static byte[] BufferedImage2Bytes(BufferedImage image) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            @SuppressWarnings("unused")
            boolean flag = ImageIO.write(image, "jpg", out);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        byte[] b = out.toByteArray();
        return b;
    }

    public static BufferedImage Bytes2BufferedImage(byte[] imageBytes) {
        ByteArrayInputStream in = new ByteArrayInputStream(imageBytes); // 将b作为输入流；

        BufferedImage image;
        try {
            image = ImageIO.read(in);
            return image;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // 将in作为输入流，读取图片存入image中，而这里in可以为ByteArrayInputStream();
        return null;
    }

    public static void main(String[] args) {

        String contents = "http://127.0.0.1:8080/x.html?uId=1";

        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();

        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");

        BitMatrix matrix = null;

        try {

            matrix = new MultiFormatWriter().encode("B000033",
                    BarcodeFormat.CODE_39, 150, 50, hints);

        } catch (WriterException e) {

            e.printStackTrace();

        }

        File file = new File("D://qrcodeImage2.jpg");

        try {

            MatrixToImageWriter.writeToFile(matrix, "jpg", file);

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
}