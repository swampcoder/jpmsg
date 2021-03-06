package org.particl.ui.smsg;

import org.particl.ui.desktop.DesktopInputData;
import org.particl.ui.desktop.DesktopView;
import org.particl.ui.desktop.DesktopViewException;
import org.particl.ui.desktop.IDesktopViewFactory;

public class SmsgBucketViewFactory implements IDesktopViewFactory {

   @Override
   public String getViewGroup() {

      return "Particl SMSG";
   }

   // TODO change to Arrays.asList("sub levels");
   @Override
   public String getViewDescription() {

      return "SMSG Bucket Statistics";
   }

   @Override
   public DesktopView createView(DesktopInputData viewData) throws DesktopViewException {

      return new SmsgBucketView(viewData);
   }

}
