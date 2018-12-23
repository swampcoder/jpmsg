package org.particl.ui.desktop;

import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import org.sexydock.tabs.DefaultFloatingTabHandler;
import org.sexydock.tabs.ITabFactory;
import org.sexydock.tabs.ITabbedPaneDndPolicy;
import org.sexydock.tabs.Tab;
import org.sexydock.tabs.jhrome.JhromeTabbedPaneUI;

public class JhromeTabbedPane extends JTabbedPane implements ITabFactory {

   private final JhromeTabbedPane self = this;
   
   public JhromeTabbedPane() 
   {
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run( ) 
         {
            putClientProperty( JhromeTabbedPaneUI.TAB_FACTORY , self );
            setUI(new JhromeTabbedPaneUI( ) );
            putClientProperty( JhromeTabbedPaneUI.NEW_TAB_BUTTON_VISIBLE , true );
            putClientProperty( JhromeTabbedPaneUI.TAB_CLOSE_BUTTONS_VISIBLE , true );
            //putClientProperty( JhromeTabbedPaneUI.TAB_DROP_FAILURE_HANDLER , new DefaultTabDropFailureHandler( this ) );
            
            putClientProperty( JhromeTabbedPaneUI.FLOATING_TAB_HANDLER , new DefaultFloatingTabHandler( ) );
            putClientProperty( JhromeTabbedPaneUI.TAB_CLOSE_BUTTONS_VISIBLE , true );
           /* putClientProperty( JhromeTabbedPaneUI.DND_POLICY , new ITabbedPaneDndPolicy( )
            {
                @Override
                public boolean isTearAwayAllowed( JTabbedPane tabbedPane , Tab tab )
                {
                    return true;
                }
                
                @Override
                public boolean isSnapInAllowed( JTabbedPane tabbedPane , Tab tab )
                {
                    return tab.getContent( ) instanceof DesktopPanel;
                }
            });*/
         }
      });
   }

   @Override
   public Tab createTab( )
   {
       return new Tab( );
   }
   
   @Override
   public Tab createTabWithContent( )
   {
       Tab tab = new Tab( );
       tab.setTitle( "Untitled" );
       Desktop desktop = new Desktop("Untitled");
       tab.setContent( new DesktopPanel( desktop ) );
       return tab;
   }
   
}
