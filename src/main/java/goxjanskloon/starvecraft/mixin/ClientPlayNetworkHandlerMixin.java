package goxjanskloon.starvecraft.mixin;
import goxjanskloon.starvecraft.client.network.OnSanityUpdateInvoker;
import goxjanskloon.starvecraft.entity.player.SanityManager;
import goxjanskloon.starvecraft.network.packet.s2c.play.SanityUpdateS2CPacket;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.NetworkThreadUtils;
import org.spongepowered.asm.mixin.Mixin;
@Mixin(ClientPlayNetworkHandler.class) public abstract class ClientPlayNetworkHandlerMixin implements OnSanityUpdateInvoker{
    @Override public void onSanityUpdate(SanityUpdateS2CPacket packet){
        ClientPlayNetworkHandler clientPlayNetworkHandler=(ClientPlayNetworkHandler)(Object)this;
        MinecraftClient client=clientPlayNetworkHandler.client;
        NetworkThreadUtils.forceMainThread(packet,clientPlayNetworkHandler,client);
        SanityManager sanityManager=client.player.getSanityManager();
        sanityManager.setSanityLevel(packet.getSanityLevel());
        sanityManager.setModifier(packet.getSanityModifier());
    }
}
