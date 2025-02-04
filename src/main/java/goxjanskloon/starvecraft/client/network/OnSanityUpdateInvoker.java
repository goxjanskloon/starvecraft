package goxjanskloon.starvecraft.client.network;
import goxjanskloon.starvecraft.network.packet.s2c.play.SanityUpdateS2CPacket;
public interface OnSanityUpdateInvoker{
    default void starvecraft$onSanityUpdate(SanityUpdateS2CPacket packet){}
}
