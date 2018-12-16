package org.particl.rpc.mp.dto;

import java.util.ArrayList;
import java.util.List;

public class Proposal {

   private Integer id = null;
   private String submitter = null;
   private String hash = null;
   private String item = null;
   private ProposalType type = null;
   private String title = null;
   private String description = null;
   private Long timeStart = null;
   private Long postedAt = null;
   private Long receivedAt = null;
   private Long expiredAt = null;
   private Long updatedAt = null;
   private Long createdAt = null;
   
   private List<ProposalOption> proposalOptions = new ArrayList<ProposalOption>();
   
   public Proposal() 
   {
      super();
   }
   
   
}
