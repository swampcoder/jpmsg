package org.particl.rabbitmq;

// Once a user has queried for group chat rooms (routes setup in Rabbit MQ) 
// Then the request to join a specific group is sent and if accepted causes a GroupChatShiftedNotification 
// to be sent out to all current participants of the group

// This message is sent over particl SMSG interface 

public class GroupChannelJoinRequest {

}
