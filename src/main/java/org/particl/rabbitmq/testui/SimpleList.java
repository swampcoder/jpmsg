package org.particl.rabbitmq.testui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class SimpleList<T> extends JList<T> {

   private DefaultListModel<T> model = new DefaultListModel<T>();

   public SimpleList() {
      super();
      setModel(model);

      addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent evt) {
            if (evt.getClickCount() == 2) {
               int index = locationToIndex(evt.getPoint());
               if (index != -1) {
                  handleDoubleClick(model.getElementAt(index));
               }
            }
         }
      });
   }

   protected void handleDoubleClick(T t) {   }

   public void addTo(T entry) {
      model.addElement(entry);
   }

   public void removeFrom(T entry) {
      model.removeElement(entry);
   }

   public boolean contains(T entry) {
      return model.contains(entry);
   }
}
