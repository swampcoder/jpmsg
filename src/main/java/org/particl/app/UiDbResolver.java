package org.particl.app;

import java.io.File;

import javax.swing.JFileChooser;

public class UiDbResolver implements IDbResolver {

   
   public UiDbResolver() 
   {
      
   }

   @Override
   public File getDbLocation() {

      JFileChooser dbChooser = new JFileChooser();
      
      return dbChooser.getSelectedFile();
   }
}

