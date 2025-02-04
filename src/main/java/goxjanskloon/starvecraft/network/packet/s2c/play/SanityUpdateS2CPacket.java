package goxjanskloon.starvecraft.network.packet.s2c.play;
import goxjanskloon.starvecraft.Starvecraft;
import net.minecraft.network.NetworkSide;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.PacketType;
import net.minecraft.util.Identifier;
public class SanityUpdateS2CPacket implements Packet<ClientPlayPacketListener>{
    public static final PacketCodec<PacketByteBuf,SanityUpdateS2CPacket> CODEC=Packet.createCodec(SanityUpdateS2CPacket::write,SanityUpdateS2CPacket::new);
    private final float sanityLevel,sanityModifier;
    public static final PacketType<SanityUpdateS2CPacket> PACKET_TYPE=new PacketType<>(NetworkSide.CLIENTBOUND,Identifier.of(Starvecraft.MOD_ID,"set_sanity"));
    public SanityUpdateS2CPacket(float sanityLevel,float sanityModifier){
        this.sanityLevel=sanityLevel;
        this.sanityModifier=sanityModifier;
    }
    private SanityUpdateS2CPacket(PacketByteBuf buf){
        this.sanityLevel=buf.readFloat();
        this.sanityModifier=buf.readFloat();
    }
    public float getSanityModifier(){
        return sanityModifier;
    }
    public float getSanityLevel(){
        return sanityLevel;
    }
    private void write(PacketByteBuf buf){
        buf.writeFloat(sanityLevel);
        buf.writeFloat(sanityModifier);
    }
    @Override public PacketType<? extends Packet<ClientPlayPacketListener>> getPacketType(){
        return PACKET_TYPE;
    }
    @Override public void apply(ClientPlayPacketListener listener){
        listener.starvecraft$onSanityUpdate(this);
    }
}
