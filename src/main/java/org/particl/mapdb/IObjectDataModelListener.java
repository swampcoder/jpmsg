package org.particl.mapdb;

public interface IObjectDataModelListener<T> {

   public void notifyModelAdd(T obj);
   public void notifyModelRemove(T obj);
}
