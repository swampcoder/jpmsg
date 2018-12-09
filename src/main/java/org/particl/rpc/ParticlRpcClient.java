package org.particl.rpc;

import java.util.List;
import java.util.Map;

import org.particl.rpc.SmsgSendFailException;
import wf.bitcoin.javabitcoindrpcclient.BitcoinRPCException;
import wf.bitcoin.javabitcoindrpcclient.BitcoindRpcClient;

public interface ParticlRpcClient extends BitcoindRpcClient {

   public SMSG getSMSG();
   
   public MARKET getMARKET();

   static enum SmsgOption {
      Delete, SetRead, AsciiEncoding, HexEncoding;
   }

   static enum SmsgInboxMode {
      All, Unread, Clear;
   }
   
   static enum SmsgOutboxMode
   {
	   All, Clear;
   }
   
   static enum SmsgLocation
   {
	   Inbox, Outbox, Sending;
   }

   /**
    * Interfacing defining particl smsg functionality
    * 
    * @author mint
    */
   public static interface SMSG {
	   
      public SmsgMessage viewid(String msgId, SmsgOption... options) throws BitcoinRPCException;

      public boolean addaddress(String address, String pubKey) throws BitcoinRPCException;

      public boolean addlocaladdress(String address) throws BitcoinRPCException;

      public SmsgBucketStats bucketStats( ) throws BitcoinRPCException;
      
      public boolean bucketDump () throws BitcoinRPCException;

      public boolean disable() throws BitcoinRPCException;

      public boolean enable(String walletName) throws BitcoinRPCException;

      public String getpubkey(String address) throws BitcoinRPCException;
      
      public boolean importprivkey(String privKey, String label) throws BitcoinRPCException;
      
      public List<SmsgKey> smsgKeys() throws BitcoinRPCException;

      public List<SmsgMessage> inbox(SmsgInboxMode mode, String filter) throws BitcoinRPCException;
      
      public List<SmsgMessage> outbox(SmsgOutboxMode mode, String filter) throws BitcoinRPCException;

      public boolean purge(String msgId) throws BitcoinRPCException;

      public boolean scanbuckets() throws BitcoinRPCException;

      public boolean scanchain() throws BitcoinRPCException;

      public SmsgMessageSendResult send(String from, String to, String text) throws BitcoinRPCException, SmsgSendFailException;

      //public String sendanon(String to, String text) throws BitcoinRPCException;
      // add smsgview

   }
   
   // Particl Market interface - TODO break out into sub interfaces per functionality for easier implementions 
   public static interface MARKET 
   {

      // MARKET
      public Object market_list() throws BitcoinRPCException;
      public Object market_add(String marketName, String privKey, String address) throws BitcoinRPCException;
      
      // BID
      public void bid_search(String page, int pageLimit, String itemHash, String searchString, Object todo);
      public boolean bid_accept(String bidId);
      public boolean bid_cancel(String itemHash, String bidId);
      public boolean bid_reject(String itemHash, String bidId);
      public boolean bid_send(String itemHash, int profileId, int addressId, String _bidDataKey, String _bidDataValue);
      
      // ESCROW
      public boolean escrow_add(Integer listingItemTemplateId, EscrowType escrowType, 
            Double buyerRatio, Double sellerRatio);
      public boolean escrow_update(Integer listingItemTemplateId, EscrowType escrowType, 
            Double buyerRatio, Double sellerRatio);
      public boolean escrow_remove(Integer listingItemTemplateId);
      public boolean escrow_lock(String orderItemId, String nonce, String memo);
      public boolean escrow_refund(String orderItemId, String accepted, String memo);
      public boolean escrow_release(String orderItemId, String memo);
      
      // LOCATION
      public boolean location_add(Integer listingItemTemplateId, String region, 
            String address, String gpsMarkerTitle, String gpsMarkerDesc, 
            Double gpsLatitude, Double gpsLongitude);
      public boolean location_update(Integer listingItemTemplateId, String region, 
            String address, String gpsMarkerTitle, String gpsMarkerDesc, 
            Double gpsLatitude, Double gpsLongitude);
      public boolean location_remove(Integer listingItemTemplateId);
      
      // ITEM
      public Object item_search(Integer page, Integer pageLimit, Ordering ordering, 
            Integer categoryId, String categoryName, ItemType itemType, Object profileId,
            Double minPrice, Double maxPrice, String country, String shippingDestination, 
            String searchString, Boolean flagged, Boolean withRelated) throws BitcoinRPCException;
      public List item_get(Integer listingItemId, String itemHash);
      public boolean item_flag(String listingItemHash, Integer profileId);
      
      // PAYMENT
      public boolean payment_update(Integer listingItemTemplateId, String paymentType, String currency, Double basePrice, 
            Double domesticShippingPrice, Double internationalShippingPrice, String paymentAddress);
      
      // SHIPPING
      public List shipping_list(Integer listingItemTemplateId, Integer listingItemId);
      public boolean shipping_add(Integer listingItemTemplateId, String countryName, 
            String countryCode, ShippingAvailability shippingAvailability);
      public boolean shipping_remove(Integer destinationId, Integer listingItemTemplateId, 
            String country, String countryCode, ShippingAvailability shippingAvailability);

      // IMAGE
      public List image_list(Integer listingItemTemplateId, Integer listingItemId) throws BitcoinRPCException;
      public boolean image_add(Integer listingItemTemplateId, String dataId, DSNProtocol dsn, Object encoding, String imgData);
      public boolean image_remove(Integer itemImageId) throws BitcoinRPCException;
      
      // INFORMATION
      public ItemInformation information_get(Integer listingItemTemplateId);   
      public boolean information_update(int listingItemTemplateId, String title, 
            String shortDesc, String longDesc, String categoryId);

      // TEMPLATE
      public boolean template_remove(Integer listingTemplateId);
      public boolean template_post(Integer listingTemplateId, Integer daysRetention, 
            Integer marketId);
      
      // FAVORITE
      public List favorite_list(Integer profileId, String profileName, Boolean withRelated);
      public boolean favorite_add(Integer profileId, Integer itemId, String itemHash);
      public boolean favorite_remove(Integer profileId, Integer itemId, String itemHash);
      
      // CATEGORY
      public List<ItemCategory> category_list();
      public ItemCategory category_get(Integer categoryId, String categoryKey);
      public ItemCategory category_add(String categoryName, String description, 
            Integer parentItemCategory, String parentItemCategoryKey);
      public ItemCategory category_update(Integer categoryId, String categoryName, 
            String description, Integer _parentItemCategoryId);
      public boolean category_remove(Integer categoryId);
      public List<ItemCategory> category_search(String searchText);
      
      // PROFILE
      public List<Profile> profile_list();
      public Profile profile_get(Integer _profileId, String _profileName);
      public boolean profile_add(String profileName, String _particlAddress);
      public boolean profile_update(Integer profileId, String newProfileName);
      public boolean profile_remove(Integer profileId, String profileName);
      
      // PROFILE ADDRESS
      public List<ProfileAddress> profile_address_list(Integer profileId);
      public boolean profile_address_modify(ModifyType modifyType, Integer addressId, String title, 
            String firstName, String lastName, String addressLine1, String addressLine2, 
            String city, String state, String country, String countryCode, String zip); 
      public boolean profile_address_remove(Integer profileAddressId);
      
      // PROFILE FAVORITE
      public List<ProfileFavorite> profile_favorite_list(Integer _profileId, String _profileName, Boolean withRelated);
      public boolean profile_favorite_add(Integer profileId, Integer itemId, String itemHash);
      public boolean profile_favorite_remove(Integer profileId, Integer listingItemId, String itemHash);
      
      
      // CART (is cart ID string or int?)
      public List<ShoppingCart> cart_list(Integer profileId, String profileName);
      public ShoppingCart cart_get(Integer cartId);
      public boolean cart_add(String cartName, Integer profileId);
      public boolean cart_update(Integer cartId, String newCartName);
      public boolean cart_remove(Integer cartId);
      public boolean cart_clear(Integer cartId);
      
      // CART ITEM
      public List cartitem_list(Integer cartId, Boolean withRelated);
      public boolean cartitem_add(Integer cardId, Integer itemId, String itemHash);
      public boolean cartitem_remove(Integer cartId, Integer itemId, String itemHash);
      
      // ITEM OBJECT
      public List itemobject_search(String searchText);
      
      // ORDER
      public List<OrderItem> order_search(String itemHash, OrderStatus _status, String _buyerAddress, 
            String _sellerAddress, Ordering _ordering);
      
      // ORDER ITEM
      public List<OrderItem> orderitem_status(String itemHash, String buyer, String seller);
      
      // PRICE TICKER
      public Map<String, PriceTicker> priceticker(String baseCurrency, String...currencies);
      
      // CURRENCY PRICE CONV.
      public List<CurrencyPrice> currencyprice(String from, String... to);
      
      // MESSAGING
      public boolean messaging_update(int listingItemTemplateId, String msgProtocol, String pubKey) throws BitcoinRPCException;
      
      // SETTING
      public boolean setting_remove(int profileId, String key);
      public Object setting_get(int profileId, String key);
      public List setting_list(int profileId);
      public boolean setting_set(int profileId, String key, String value);
      
      public Object search(Integer page, Integer pageLimit, Object ordering, Object type, Object status, Object msgId);
      
      
   }

 
}
