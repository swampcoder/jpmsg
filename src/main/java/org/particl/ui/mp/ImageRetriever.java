package org.particl.ui.mp;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.imageio.ImageIO;

import org.particl.app.Application;
import org.particl.app.IAppService;
import org.particl.rpc.mp.dto.DSNProtocol;
import org.particl.rpc.mp.dto.ItemImage;
import org.particl.rpc.mp.dto.ItemImageData;

public class ImageRetriever implements Runnable, IAppService {

   public static interface IRetrievableImageHandler {
      public void notifyImageRetrieval(ItemImage image, Map<ItemImageData, BufferedImage> imageMap);
   }

   public final static int NUM_IMAGE_RETRIEVAL_THREADS = 4;

   static {

      // start retriever threads
      ImageRetriever retriever = new ImageRetriever();
      for (int i = 0; i < NUM_IMAGE_RETRIEVAL_THREADS; i++) {
         Thread imageThread = new Thread(retriever);
         imageThread.setName("Image Retreiver Thread # " + (i + 1));
         imageThread.start();
      }

      // register as service
      Application.initService(retriever, ImageRetriever.class);
   }

   private final BlockingQueue<ItemImageRequest> imageRequests = new ArrayBlockingQueue<ItemImageRequest>(1000);

   private final Map<ItemImageData, SoftReference<BufferedImage>> cachedImages = new HashMap<ItemImageData, SoftReference<BufferedImage>>();

   private static class ItemImageRequest {
      private final ItemImage itemImage;
      private final List<IRetrievableImageHandler> handlers;

      public ItemImageRequest(ItemImage itemImage, IRetrievableImageHandler... handlers) {
         this.itemImage = itemImage;
         this.handlers = Arrays.asList(handlers);
      }
   }

   protected ImageRetriever() {
      super();
   }

   public BufferedImage tryCache(ItemImageData iid) {
      SoftReference<BufferedImage> cachedImg = cachedImages.get(iid);
      if (cachedImg != null)
         return cachedImg.get();
      else
         return null;
   }

   public void requestImage(ItemImage itemImage, IRetrievableImageHandler... handlers) {
      ItemImageRequest request = new ItemImageRequest(itemImage, handlers);
      imageRequests.offer(request);
   }

   @Override
   public void run() {

      while (true) {
         ItemImageRequest itemImage;
         try {
            itemImage = imageRequests.take();
            processImageRequest(itemImage);
         } catch (InterruptedException e) {
            // nothing interrupts these threads
         }
      }
   }

   private void cacheImg(ItemImageData iid, BufferedImage image) {
      cachedImages.put(iid, new SoftReference<BufferedImage>(image));
   }

   private void processImageRequest(ItemImageRequest request) {

      Map<ItemImageData, BufferedImage> imageMap = new HashMap<ItemImageData, BufferedImage>();
      for (ItemImageData iid : request.itemImage.getItemImageData()) {
         BufferedImage cached = tryCache(iid);
         if (cached != null) {
            imageMap.put(iid, cached);
            continue;
         }
         if (iid.getProtocol() == DSNProtocol.LOCAL) {
            URL localURL;
            try {

               localURL = new URL(iid.getDataId());

               BufferedImage img = ImageIO.read(localURL);
               imageMap.put(iid, img);
               cacheImg(iid, img);
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
      }

      for (IRetrievableImageHandler handler : request.handlers) {
         handler.notifyImageRetrieval(request.itemImage, imageMap);
      }
   }
}
