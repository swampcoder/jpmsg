package org.particl.rabbitmq.testui;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class StringList extends JList<String> {

   private DefaultListModel<String> model = new DefaultListModel<String>();
   
   public StringList() 
   {
      super();
      setModel(model);
   }
   
   public void addTo(String entry) 
   {
      model.addElement(entry);
   }
   
   public void removeFrom(String entry) 
   {
      model.removeElement(entry);
   }
}
