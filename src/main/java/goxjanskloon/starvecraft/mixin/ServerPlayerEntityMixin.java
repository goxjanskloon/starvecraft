package goxjanskloon.starvecraft.mixin;
import goxjanskloon.starvecraft.entity.player.SanityManager;
import goxjanskloon.starvecraft.entity.player.SanityManagerGetter;
import goxjanskloon.starvecraft.network.packet.s2c.play.SanityUpdateS2CPacket;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;
import net.minecraft.util.crash.CrashReportSection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(ServerPlayerEntity.class) public abstract class ServerPlayerEntityMixin{
    @Shadow public ServerPlayNetworkHandler networkHandler;
    @Unique private float syncedSanityLevel=-1e8f;
    @Unique private float syncedSanityModifier=-1e8f;
    @Inject(method="playerTick",at=@At("TAIL")) public void playerTick(CallbackInfo ci){
        try{
            SanityManager sanityManager=((SanityManagerGetter)this).getSanityManager();
            float sanityLevel=sanityManager.getSanityLevel(), sanityModifier=sanityManager.getModifier();
            if(syncedSanityLevel!=sanityManager.getSanityLevel()||syncedSanityModifier!=sanityManager.getModifier()){
                networkHandler.sendPacket(new SanityUpdateS2CPacket(sanityLevel,sanityModifier));
                syncedSanityLevel=sanityLevel;
                syncedSanityModifier=sanityModifier;
            }
        }catch(Throwable t){
            CrashReport crashReport=CrashReport.create(t,"Ticking player");
            CrashReportSection crashReportSection=crashReport.addElement("Player being ticked");
            ((Entity)(Object)this).populateCrashReport(crashReportSection);
            throw new CrashException(crashReport);
        }
    }
}
