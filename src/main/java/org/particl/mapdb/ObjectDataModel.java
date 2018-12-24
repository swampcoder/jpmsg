package org.particl.mapdb;

import java.util.ArrayList;
import java.util.List;

public class ObjectDataModel<T> {

   private final List<IObjectDataModelListener<T>> listeners = new ArrayList<IObjectDataModelListener<T>>();
   
   public ObjectDataModel() 
   {
      
   }
}
