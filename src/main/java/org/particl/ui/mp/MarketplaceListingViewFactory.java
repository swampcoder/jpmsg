package org.particl.ui.mp;

import org.particl.ui.desktop.DesktopInputData;
import org.particl.ui.desktop.DesktopView;
import org.particl.ui.desktop.DesktopViewException;
import org.particl.ui.desktop.IDesktopViewFactory;

public class MarketplaceListingViewFactory implements IDesktopViewFactory {

   @Override
   public String getViewGroup() {

      return "Marketplace";
   }

   @Override
   public String getViewDescription() {

      return "Listing Table";
   }

   @Override
   public DesktopView createView(DesktopInputData viewData) throws DesktopViewException {

      return new MarketPlaceListingView(viewData);
   }

}
