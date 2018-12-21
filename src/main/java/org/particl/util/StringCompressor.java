package org.particl.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;


// static compressor 
public enum StringCompressor {
   ;
   public static byte[] compress(String text) {

      OutputStream out = null;
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      try {
         out = new DeflaterOutputStream(baos);
         out.write(text.getBytes("UTF-8"));
         out.close();
      } catch (IOException e) {
         throw new AssertionError(e);
      } finally {
         if (out != null) {
            try {
               out.close();
            } catch (IOException e) {

               e.printStackTrace();
            }
         }
      }
      return baos.toByteArray();
   }

   public static String decompress(byte[] bytes) {

      InputStream in = null;
      try {
         in = new InflaterInputStream(new ByteArrayInputStream(bytes));
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         byte[] buffer = new byte[8192];
         int len;
         while ((len = in.read(buffer)) > 0)
            baos.write(buffer, 0, len);
         return new String(baos.toByteArray(), "UTF-8");
      } catch (IOException e) {
         throw new AssertionError(e);
      } finally {
         if (in != null)
            try {
               in.close();
            } catch (IOException e) {
               e.printStackTrace();
            }
      }
   }
}