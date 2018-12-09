package org.particl.rpc.market;

import java.util.List;
import java.util.Map;

import org.particl.rpc.ParticlRpcClient;
import org.particl.rpc.ParticlRpcClient.MARKET;

import wf.bitcoin.javabitcoindrpcclient.BitcoinRPCException;

abstract public class MarketAdapter implements MARKET {

   @Override
   public Object market_list() throws BitcoinRPCException {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public Object market_add(String marketName, String privKey, String address) throws BitcoinRPCException {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public void bid_search(String page, int pageLimit, String itemHash, String searchString, Object todo) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public boolean bid_accept(String bidId) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean bid_cancel(String itemHash, String bidId) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean bid_reject(String itemHash, String bidId) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean bid_send(String itemHash, int profileId, int addressId, String _bidDataKey, String _bidDataValue) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean escrow_add(Integer listingItemTemplateId, EscrowType escrowType, Double buyerRatio,
         Double sellerRatio) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean escrow_update(Integer listingItemTemplateId, EscrowType escrowType, Double buyerRatio,
         Double sellerRatio) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean escrow_remove(Integer listingItemTemplateId) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean escrow_lock(String orderItemId, String nonce, String memo) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean escrow_refund(String orderItemId, String accepted, String memo) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean escrow_release(String orderItemId, String memo) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean location_add(Integer listingItemTemplateId, String region, String address, String gpsMarkerTitle,
         String gpsMarkerDesc, Double gpsLatitude, Double gpsLongitude) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean location_update(Integer listingItemTemplateId, String region, String address, String gpsMarkerTitle,
         String gpsMarkerDesc, Double gpsLatitude, Double gpsLongitude) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean location_remove(Integer listingItemTemplateId) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public Object item_search(Integer page, Integer pageLimit, Ordering ordering, Integer categoryId,
         String categoryName, ItemType itemType, Object profileId, Double minPrice, Double maxPrice, String country,
         String shippingDestination, String searchString, Boolean flagged, Boolean withRelated)
         throws BitcoinRPCException {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public List item_get(Integer listingItemId, String itemHash) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public boolean item_flag(String listingItemHash, Integer profileId) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean payment_update(Integer listingItemTemplateId, String paymentType, String currency, Double basePrice,
         Double domesticShippingPrice, Double internationalShippingPrice, String paymentAddress) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public List shipping_list(Integer listingItemTemplateId, Integer listingItemId) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public boolean shipping_add(Integer listingItemTemplateId, String countryName, String countryCode,
         ShippingAvailability shippingAvailability) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean shipping_remove(Integer destinationId, Integer listingItemTemplateId, String country,
         String countryCode, ShippingAvailability shippingAvailability) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public List image_list(Integer listingItemTemplateId, Integer listingItemId) throws BitcoinRPCException {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public boolean image_add(Integer listingItemTemplateId, String dataId, DSNProtocol dsn, Object encoding,
         String imgData) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean image_remove(Integer itemImageId) throws BitcoinRPCException {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public ItemInformation information_get(Integer listingItemTemplateId) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public boolean information_update(int listingItemTemplateId, String title, String shortDesc, String longDesc,
         String categoryId) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean template_remove(Integer listingTemplateId) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean template_post(Integer listingTemplateId, Integer daysRetention, Integer marketId) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public List favorite_list(Integer profileId, String profileName, Boolean withRelated) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public boolean favorite_add(Integer profileId, Integer itemId, String itemHash) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean favorite_remove(Integer profileId, Integer itemId, String itemHash) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public List<ItemCategory> category_list() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public ItemCategory category_get(Integer categoryId, String categoryKey) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public ItemCategory category_add(String categoryName, String description, Integer parentItemCategory,
         String parentItemCategoryKey) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public ItemCategory category_update(Integer categoryId, String categoryName, String description,
         Integer _parentItemCategoryId) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public boolean category_remove(Integer categoryId) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public List<ItemCategory> category_search(String searchText) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public List<Profile> profile_list() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public Profile profile_get(Integer _profileId, String _profileName) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public boolean profile_add(String profileName, String _particlAddress) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean profile_update(Integer profileId, String newProfileName) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean profile_remove(Integer profileId, String profileName) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public List<ProfileAddress> profile_address_list(Integer profileId) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public boolean profile_address_modify(ModifyType modifyType, Integer addressId, String title, String firstName,
         String lastName, String addressLine1, String addressLine2, String city, String state, String country,
         String countryCode, String zip) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean profile_address_remove(Integer profileAddressId) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public List<ProfileFavorite> profile_favorite_list(Integer _profileId, String _profileName, Boolean withRelated) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public boolean profile_favorite_add(Integer profileId, Integer itemId, String itemHash) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean profile_favorite_remove(Integer profileId, Integer listingItemId, String itemHash) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public List<ShoppingCart> cart_list(Integer profileId, String profileName) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public ShoppingCart cart_get(Integer cartId) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public boolean cart_add(String cartName, Integer profileId) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean cart_update(Integer cartId, String newCartName) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean cart_remove(Integer cartId) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean cart_clear(Integer cartId) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public List cartitem_list(Integer cartId, Boolean withRelated) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public boolean cartitem_add(Integer cardId, Integer itemId, String itemHash) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean cartitem_remove(Integer cartId, Integer itemId, String itemHash) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public List itemobject_search(String searchText) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public List<OrderItem> order_search(String itemHash, OrderStatus _status, String _buyerAddress,
         String _sellerAddress, Ordering _ordering) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public List<OrderItem> orderitem_status(String itemHash, String buyer, String seller) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public Map<String, PriceTicker> priceticker(String baseCurrency, String... currencies) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public List<CurrencyPrice> currencyprice(String from, String... to) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public boolean messaging_update(int listingItemTemplateId, String msgProtocol, String pubKey)
         throws BitcoinRPCException {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean setting_remove(int profileId, String key) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public Object setting_get(int profileId, String key) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public List setting_list(int profileId) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public boolean setting_set(int profileId, String key, String value) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public Object search(Integer page, Integer pageLimit, Object ordering, Object type, Object status, Object msgId) {
      // TODO Auto-generated method stub
      return null;
   }

}
