package org.particl.ui.smsg;

import org.particl.ui.desktop.DesktopInputData;
import org.particl.ui.desktop.DesktopView;
import org.particl.ui.desktop.DesktopViewException;
import org.particl.ui.desktop.IDesktopViewFactory;

public class SmsgOutboxViewFactory implements IDesktopViewFactory {

   @Override
   public String getViewGroup() {

      return "Particl SMSG";
   }

   @Override
   public String getViewDescription() {

      return "SMSG Outbox";
   }

   @Override
   public DesktopView createView(DesktopInputData viewData) throws DesktopViewException {

      return new SmsgInboxView(viewData);
   }

}