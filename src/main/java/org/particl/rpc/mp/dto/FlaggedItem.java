package org.particl.rpc.mp.dto;

public class FlaggedItem {

   private Integer id = null;
   private Integer listingItemId = null;
   private Integer proposalId = null;
   private String reason = null;
   private Long updatedAt = null;
   private Long createdAt = null;
   
   private Proposal proposal = null;
   
   public FlaggedItem() 
   {
      super();
   }

   public Integer getId() {
      return id;
   }

   public Integer getListingItemId() {
      return listingItemId;
   }

   public Integer getProposalId() {
      return proposalId;
   }

   public String getReason() {
      return reason;
   }

   public Long getUpdatedAt() {
      return updatedAt;
   }

   public Long getCreatedAt() {
      return createdAt;
   }

   public Proposal getProposal() {
      return proposal;
   }

   void setId(Integer id) {
      this.id = id;
   }

   void setListingItemId(Integer listingItemId) {
      this.listingItemId = listingItemId;
   }

   void setProposalId(Integer proposalId) {
      this.proposalId = proposalId;
   }

   void setReason(String reason) {
      this.reason = reason;
   }

   void setUpdatedAt(Long updatedAt) {
      this.updatedAt = updatedAt;
   }

   void setCreatedAt(Long createdAt) {
      this.createdAt = createdAt;
   }

   void setProposal(Proposal proposal) {
      this.proposal = proposal;
   }
   
   
}
