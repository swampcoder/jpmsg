package org.woocommerce.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.particl.app.IAppService;

import com.icoderman.woocommerce.ApiVersionType;
import com.icoderman.woocommerce.EndpointBaseType;
import com.icoderman.woocommerce.WooCommerce;
import com.icoderman.woocommerce.WooCommerceAPI;
import com.icoderman.woocommerce.oauth.OAuthConfig;

public class WooCommerceConnection implements IAppService {

   public static final String WooCommerceDbName = "woocommerice-db";
   
   
   public WooCommerceConnection() 
   {
   // Setup client
      OAuthConfig config = new OAuthConfig("http://woocommerce.com", "consumerKey", "consumerSecret");
      WooCommerce wooCommerce = new WooCommerceAPI(config, ApiVersionType.V2);

      // Prepare object for request
      Map<String, Object> productInfo = new HashMap<>();
      productInfo.put("name", "Premium Quality");
      productInfo.put("type", "simple");
      productInfo.put("regular_price", "21.99");
      productInfo.put("description", "Pellentesque habitant morbi tristique senectus et netus");

      // Make request and retrieve result
      Map product = wooCommerce.create(EndpointBaseType.PRODUCTS.getValue(), productInfo);

      System.out.println(product.get("id"));

      // Get all with request parameters
      Map<String, String> params = new HashMap<>();
      params.put("per_page","100");
      params.put("offset","0");
      List products = wooCommerce.getAll(EndpointBaseType.PRODUCTS.getValue(), params);

      System.out.println(products.size());
      
   }
}
