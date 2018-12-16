package org.particl.rpc.mp;

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

public interface IParticlMarket {

   public IAddressService getAddressService();
   public IBidService getBidService();
   public ICartService getCartService();
   public ICategoryService getCategoryService();
   public IEscrowService getEscrowService();
   public IFavoriteService getFavoriteService();
   public IImageService getImageService();
   public IInformationService getInformationService();
   public IItemService getItemService();
   public ILocationService getLocationService();
   public IMarketService getMarketService();
   public IOrderService getOrderService();
   public IOrderItemService getOrderItemService();
   public IPaymentService getPaymentService();
   public IPriceService getPriceService();
   public IProfileService getProfileService();
   public IShippingService getShippingService();
   public ITemplateService getTemplateService();
   public ISettingService getSettingService();
   
   // TODO messaging
   
   
   
}
