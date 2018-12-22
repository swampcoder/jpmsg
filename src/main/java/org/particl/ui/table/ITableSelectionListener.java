package org.particl.ui.table;

import java.util.List;

public interface ITableSelectionListener<T> {

   public void notifySelection(List<T> selections);
}
