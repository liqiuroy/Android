package utils;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import android.graphics.Bitmap;
import android.util.Log;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;

/**
 * dependence com.google.zxing
 * @author Roy
 *
 */
public class QRCodeUtils {
	public static Bitmap createQRcode(String text,int w,int h){
		if(text==null || text.trim().length()==0){
			return null;
		}
		Log.i("info", "qr text ----->"+text);
		try{
			int[] pixels = core(text,w,h);
			Bitmap bitmap = Bitmap.createBitmap(w, h,Bitmap.Config.ARGB_8888);
			bitmap.setPixels(pixels, 0, w, 0, 0, w, h);
            return bitmap;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static String scanQRImg(Bitmap img){
		Map<DecodeHintType, String> hints = new HashMap<DecodeHintType, String>();
        hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
        LuminanceSource source = new BitmapLuminanceSource(img);
        BinaryBitmap bitmap1 = new BinaryBitmap(new HybridBinarizer(source));
        QRCodeReader reader = new QRCodeReader();
        Result result;
        try{
        	result = reader.decode(bitmap1, hints);
        	return result.getText();
        }catch(Exception e){
        	e.printStackTrace();
        	return null;
        }
	}
	
	private static int[] core(String text,int w,int h) throws WriterException{
		Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		BitMatrix bitMatrix = new QRCodeWriter().encode(text,BarcodeFormat.QR_CODE, w, h, hints);
        int[] pixels = new int[w * h];
        for (int y = 0; y < w; y++) {
            for (int x = 0; x < w; x++) {
                if (bitMatrix.get(x, y)) {
                    pixels[y * w + x] = 0xff000000;
                } 
                //else {
                //    pixels[y * w + x] = 0xffffffff;
                //}
            }
        }
        return pixels;
	}
	
	static class BitmapLuminanceSource extends LuminanceSource{
		private byte[] bitmapPixels;
		protected BitmapLuminanceSource(Bitmap bitmap){
			super(bitmap.getWidth(),bitmap.getHeight());
			int[] data = new int[bitmap.getWidth() * bitmap.getHeight()];  
		    this.bitmapPixels = new byte[bitmap.getWidth() * bitmap.getHeight()];  
		    bitmap.getPixels(data, 0, getWidth(), 0, 0, getWidth(), getHeight()); 
		    
		    for (int i = 0; i < data.length; i++) {  
		        this.bitmapPixels[i] = (byte) data[i];  
		    }  
		}
		
		@Override
		public byte[] getMatrix() {
			return this.bitmapPixels;
		}

		@Override
		public byte[] getRow(int y, byte[] row) {
			System.arraycopy(this.bitmapPixels,y*getWidth(), row, 0, getWidth());
			return row;
		}
		
	}
}
