package goxjanskloon.starvecraft.mixin;
import goxjanskloon.starvecraft.client.network.ClientPlayPacketListener;
import goxjanskloon.starvecraft.entity.player.SanityManager;
import goxjanskloon.starvecraft.network.packet.s2c.play.SanityUpdateS2CPacket;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.NetworkThreadUtils;
import org.spongepowered.asm.mixin.Mixin;
@Mixin(net.minecraft.client.network.ClientPlayNetworkHandler.class) public abstract class ClientPlayNetworkHandlerMixin implements ClientPlayPacketListener{
    @Override public void starvecraft$onSanityUpdate(SanityUpdateS2CPacket packet){
        net.minecraft.client.network.ClientPlayNetworkHandler clientPlayNetworkHandler=(net.minecraft.client.network.ClientPlayNetworkHandler)(Object)this;
        MinecraftClient client=clientPlayNetworkHandler.client;
        NetworkThreadUtils.forceMainThread(packet,clientPlayNetworkHandler,client);
        if(client.player!=null){
            SanityManager sanityManager=client.player.starvecraft$getSanityManager();
            sanityManager.setSanityLevel(packet.getSanityLevel());
            sanityManager.setModifier(packet.getSanityModifier());
        }
    }
}
