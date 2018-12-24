package org.particl.rpc.mp.dto;

import java.awt.image.BufferedImage;
import java.lang.ref.SoftReference;

import javax.swing.JFrame;

public class ItemImageData {

   private Integer id;
   private DSNProtocol protocol;
   private Encoding encoding;
   private ImageVersion imageVersion = null;
   private String dataId = null;
   private Integer itemImageId = null;
   private Long updatedAt = null;
   private Long createdAt = null;
   private String originalMime = null;
   private String originalName = null;

   public ItemImageData() 
   {
      super();
   }
   
   @Override
   public String toString() 
   {
      return "ItemImageData id=" + id + " proto=" + protocol + "  encoding=" + encoding + " version=" + imageVersion + " dataid=" + dataId + " " + 
            "  itemImgId=" + itemImageId + " origMime=" + originalMime + "  origName=" + originalName;
   }

   @Override
   public int hashCode() 
   {
      return dataId.hashCode();
   }
   
   @Override
   public boolean equals(Object cmp) 
   {
      if(cmp instanceof ItemImageData) 
      {
         ItemImageData iid = (ItemImageData) cmp;
         return iid.getDataId().equals(dataId);
      }
      return false;
   }
   
   public Integer getId() {
      return id;
   }

   public DSNProtocol getProtocol() {
      return protocol;
   }

   public Encoding getEncoding() {
      return encoding;
   }

   public ImageVersion getImageVersion() {
      return imageVersion;
   }

   public String getDataId() {
      return dataId;
   }

   public Integer getItemImageId() {
      return itemImageId;
   }

   public Long getUpdatedAt() {
      return updatedAt;
   }

   public Long getCreatedAt() {
      return createdAt;
   }

   public String getOriginalMime() {
      return originalMime;
   }

   public String getOriginalName() {
      return originalName;
   }

   void setId(Integer id) {
      this.id = id;
   }

   void setProtocol(DSNProtocol protocol) {
      this.protocol = protocol;
   }

   void setEncoding(Encoding encoding) {
      this.encoding = encoding;
   }

   void setImageVersion(ImageVersion imageVersion) {
      this.imageVersion = imageVersion;
   }

   void setDataId(String dataId) {
      this.dataId = dataId;
   }

   void setItemImageId(Integer itemImageId) {
      this.itemImageId = itemImageId;
   }

   void setUpdatedAt(Long updatedAt) {
      this.updatedAt = updatedAt;
   }

   void setCreatedAt(Long createdAt) {
      this.createdAt = createdAt;
   }

   void setOriginalMime(String originalMime) {
      this.originalMime = originalMime;
   }

   void setOriginalName(String originalName) {
      this.originalName = originalName;
   }

   
}
