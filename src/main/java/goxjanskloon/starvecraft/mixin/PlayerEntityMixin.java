package goxjanskloon.starvecraft.mixin;
import goxjanskloon.starvecraft.entity.player.SanityManager;
import goxjanskloon.starvecraft.entity.player.SanityManagerGetter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(PlayerEntity.class) public abstract class PlayerEntityMixin implements SanityManagerGetter{
    @Unique protected SanityManager sanityManager=new SanityManager();
    @Override public SanityManager starvecraft$getSanityManager(){
        return sanityManager;
    }
    @Inject(method="tick",at=@At("TAIL")) public void tick(CallbackInfo ci){
        if((Object)this instanceof ServerPlayerEntity serverPlayer)
            sanityManager.update(serverPlayer);
    }
    @Inject(method="readCustomDataFromNbt",at=@At("TAIL")) public void readCustomDataFromNbt(NbtCompound nbt,CallbackInfo ci){
        sanityManager.readNbt(nbt);
    }
    @Inject(method="writeCustomDataToNbt",at=@At("TAIL")) public void writeCustomDataToNbt(NbtCompound nbt,CallbackInfo ci){
        sanityManager.writeNbt(nbt);
    }
}