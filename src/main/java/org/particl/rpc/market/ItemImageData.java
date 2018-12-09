package org.particl.rpc.market;

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
