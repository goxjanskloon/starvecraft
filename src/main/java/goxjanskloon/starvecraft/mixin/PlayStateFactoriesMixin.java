package goxjanskloon.starvecraft.mixin;
import goxjanskloon.starvecraft.network.packet.s2c.play.SanityUpdateS2CPacket;
import net.minecraft.network.NetworkStateBuilder;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.state.PlayStateFactories;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(PlayStateFactories.class) public abstract class PlayStateFactoriesMixin{
    @Inject(method="method_55958",at=@At("TAIL")) private static void method_55958(NetworkStateBuilder<? extends ClientPlayPacketListener,? extends PacketByteBuf> builder,CallbackInfo ci){
        builder.add(SanityUpdateS2CPacket.PACKET_TYPE,SanityUpdateS2CPacket.CODEC);
    }
}
