package org.particl.rabbitmq.testui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import org.particl.rabbitmq.ChannelCreateRequest;
import org.particl.rabbitmq.IParticlUserNodeListener;
import org.particl.rabbitmq.NodeMsgType;
import org.particl.rabbitmq.ParticlSmsgUserNode;
import org.particl.rpc.core.ParticlJSONRPCClient;

// Panel for a single user 
// used for testing locally
public class UserChatPanel extends JPanel implements ActionListener, IParticlUserNodeListener {

   private final String address;
   
   private final StringList knownUsers = new StringList();
   private final StringList knownGroups = new StringList();
   
   private final ParticlSmsgUserNode particl;
   
   private JButton send = new JButton("Send");
   private JTextField input = new JTextField(100);
   private JTextPane textPane = new JTextPane();

   private JButton queryGroups = new JButton("Query Groups");
   
   private JButton addPubKey = new JButton("Add PubKey");
   
   private JButton createGroup = new JButton("New Group");
   
   public UserChatPanel(ParticlSmsgUserNode particl, String address)
   {
      this.particl = particl;
      this.address = address;
      
      setLayout(new BorderLayout());
      initLists();
      initControlPane();
      initTextArea();
      
      send.addActionListener(this);
      addPubKey.addActionListener(this);
      createGroup.addActionListener(this);
      
      setPreferredSize(new Dimension(600,450));
      setBorder(BorderFactory.createLineBorder(Color.BLACK));
      setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(4, 10, 10, 10), BorderFactory.createTitledBorder(address)));
      
      particl.addListener(this);
   }
   
   private void initControlPane()
   {
      JPanel controlPane = new JPanel();
      controlPane.setLayout(new BoxLayout(controlPane, BoxLayout.LINE_AXIS));
      
      controlPane.add(Box.createHorizontalStrut(20));
      controlPane.add(addPubKey);
      controlPane.add(Box.createHorizontalStrut(10));
      controlPane.add(createGroup);
      controlPane.add(Box.createHorizontalStrut(10));
      controlPane.add(queryGroups);
      controlPane.add(Box.createHorizontalGlue());
      add(controlPane, BorderLayout.SOUTH);
      
   }
   
   private void initLists() 
   {
      JPanel listPane = new JPanel();
      listPane.setLayout(new GridLayout(2,1));
      listPane.add(new JScrollPane(knownUsers));
      listPane.add(new JScrollPane(knownGroups));
      add(listPane, BorderLayout.WEST);
   }
   
   private void initTextArea() 
   {
      JPanel chatPane = new JPanel();
      chatPane.setLayout(new BorderLayout());
      JPanel textAreaPane = new JPanel();
      
      textAreaPane.setLayout(new BorderLayout()); 
      textAreaPane.add(new JScrollPane(textPane));
      
      JPanel sendPanel = new JPanel();
      sendPanel.setLayout(new BoxLayout(sendPanel, BoxLayout.LINE_AXIS));
      sendPanel.add(send);
      sendPanel.add(Box.createHorizontalStrut(10));
      sendPanel.add(input);
      textAreaPane.add(sendPanel, BorderLayout.SOUTH);
      textAreaPane.add(new JScrollPane(textPane));
      chatPane.add(textAreaPane, BorderLayout.CENTER);
      add(chatPane, BorderLayout.CENTER);
      
   }
   

   @Override
   public void notifyUserNodeMsg(NodeMsgType msgType, Object nodeObject) {
      
      if(msgType == NodeMsgType.CHANNEL_CREATE_RESPONSE) 
      {
         System.out.println("GOT RESPONSE: " + nodeObject);
      }
      
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      
      try
      {
         if(e.getSource() == send) 
         {
            
         }
         else if(e.getSource() == addPubKey)
         {
            String pk = JOptionPane.showInputDialog("Enter PK");
            if(pk != null) 
            {
               
            }
         }
         else if(e.getSource() == queryGroups) 
         {
            
         }
         else if(e.getSource() == createGroup) 
         {
            String groupDesc = JOptionPane.showInputDialog("Group Desc");
            ChannelCreateRequest request = new ChannelCreateRequest(address, 0,10, groupDesc);
            particl.createChannel("pZKJ9xnCP8Bh3E64nbtLBPNBvRKssLFg59", request);
         }
      }
      catch(Exception ex) 
      {
         ex.printStackTrace();
      }
   }

}
