package org.particl.ui.smsg;

import java.awt.BorderLayout;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jdesktop.swingx.JXMultiSplitPane;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.MultiSplitLayout;
import org.particl.app.Application;
import org.particl.rpc.core.ParticlJSONRPCClient;
import org.particl.ui.desktop.AppFrame;
import org.particl.ui.desktop.DesktopConfiguration;
import org.particl.ui.desktop.DesktopException;
import org.particl.ui.desktop.DesktopMenu;
import org.particl.ui.desktop.DesktopPanel;
import org.particl.ui.rpc.RpcParamMenu;

import com.jtattoo.plaf.smart.SmartLookAndFeel;

public class SmsgClient {

   private SmsgClientStatusBarPanel statusPanel;
   
   public SmsgClient(ParticlJSONRPCClient rpc) throws DesktopException 
   {
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() 
         {
            try {
               Application.frame().addDesktop("SMSG");
            } catch (DesktopException e) {
               e.printStackTrace();
            }
            statusPanel = new SmsgClientStatusBarPanel(rpc);
            Application.frame().setStatusBar(statusPanel);
            setupMenubar(Application.frame().getJMenubar());
         }
      });
      
   }
   
   private static void setupMenubar(JMenuBar menubar)
   {
      DesktopMenu desktopConfig = new DesktopMenu();
      menubar.add(desktopConfig);
      
      RpcParamMenu rpcConfig = new RpcParamMenu();
      menubar.add(rpcConfig);
   }
  
   
   private static void initLAF() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException 
   {
   // setup the look and feel properties
      Properties props = new Properties();
      
      props.put("logoString", "my company"); 
      props.put("licenseKey", "INSERT YOUR LICENSE KEY HERE");
      
      props.put("selectionBackgroundColor", "180 240 197"); 
      props.put("menuSelectionBackgroundColor", "180 240 197"); 
      
      props.put("controlColor", "218 254 230");
      props.put("controlColorLight", "218 254 230");
      props.put("controlColorDark", "180 240 197"); 

      props.put("buttonColor", "218 230 254");
      props.put("buttonColorLight", "255 255 255");
      props.put("buttonColorDark", "244 242 232");

      props.put("rolloverColor", "218 254 230"); 
      props.put("rolloverColorLight", "218 254 230"); 
      props.put("rolloverColorDark", "180 240 197"); 

      props.put("windowTitleForegroundColor", "0 0 0");
      props.put("windowTitleBackgroundColor", "180 240 197"); 
      props.put("windowTitleColorLight", "218 254 230"); 
      props.put("windowTitleColorDark", "180 240 197"); 
      props.put("windowBorderColor", "218 254 230");
      
      // set your theme
      SmartLookAndFeel.setCurrentTheme(props);
      // select the Look and Feel
      //UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
      //UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
      UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");

   }
   
   public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, DesktopException, IOException 
   {
      initLAF();
      Application.initialize();
      Application.initializeDb(null);
      ParticlJSONRPCClient rpc = new ParticlJSONRPCClient("localhost", "particl", "password", 22222);
      new SmsgClient(rpc);
   }
   
}
