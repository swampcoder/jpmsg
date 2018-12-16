package org.particl.rpc.mp;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.particl.rpc.mp.address.IAddressService;
import org.particl.rpc.mp.bid.IBidService;
import org.particl.rpc.mp.cart.ICartService;
import org.particl.rpc.mp.category.ICategoryService;
import org.particl.rpc.mp.escrow.IEscrowService;
import org.particl.rpc.mp.favorite.IFavoriteService;
import org.particl.rpc.mp.image.IImageService;
import org.particl.rpc.mp.info.IInformationService;
import org.particl.rpc.mp.item.IItemService;
import org.particl.rpc.mp.location.ILocationService;
import org.particl.rpc.mp.market.IMarketService;
import org.particl.rpc.mp.order.IOrderService;
import org.particl.rpc.mp.orderitem.IOrderItemService;
import org.particl.rpc.mp.payment.IPaymentService;
import org.particl.rpc.mp.price.IPriceService;
import org.particl.rpc.mp.profile.IProfileService;
import org.particl.rpc.mp.setting.ISettingService;
import org.particl.rpc.mp.shipping.IShippingService;
import org.particl.rpc.mp.template.ITemplateService;

import com.thetransactioncompany.jsonrpc2.client.ConnectionConfigurator;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2Session;

import wf.bitcoin.krotjson.Base64Coder;

public class ParticlMarketApi implements IParticlMarket {

   private final URL serverUrl;
   private JSONRPC2Session jsonSession;
   
   private IMarketService marketService;
   
   private static class BasicAuthenticator implements ConnectionConfigurator {
      private final URL serverUrl;
      private BasicAuthenticator(URL serverUrl)
      {
         this.serverUrl = serverUrl;
      }
      public void configure(HttpURLConnection connection) {
      
          // add custom HTTP header
          connection.addRequestProperty("Authorization", "Basic " +  String.valueOf(Base64Coder.encode(serverUrl.getUserInfo().getBytes())));
      }
  }
   
   public ParticlMarketApi(String host, int port, String user, String password) throws MalformedURLException 
   {
      super();
      serverUrl = new URL("http://" + user + ':' + password + "@" + host + ":" + Integer.toString(port) + "/api/rpc");
      jsonSession = new JSONRPC2Session(serverUrl);
      jsonSession.getOptions().setRequestContentType("application/json");
      jsonSession.setConnectionConfigurator(new BasicAuthenticator(serverUrl));
      
      // instantiate services 
      marketService = new MarketServiceImpl(jsonSession);
   }
   
   @Override
   public IAddressService getAddressService() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public IBidService getBidService() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public ICartService getCartService() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public ICategoryService getCategoryService() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public IEscrowService getEscrowService() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public IFavoriteService getFavoriteService() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public IImageService getImageService() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public IInformationService getInformationService() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public IItemService getItemService() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public ILocationService getLocationService() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public IMarketService getMarketService() {

      return marketService;
   }

   @Override
   public IOrderService getOrderService() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public IOrderItemService getOrderItemService() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public IPaymentService getPaymentService() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public IPriceService getPriceService() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public IProfileService getProfileService() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public IShippingService getShippingService() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public ITemplateService getTemplateService() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public ISettingService getSettingService() {
      // TODO Auto-generated method stub
      return null;
   }

}
