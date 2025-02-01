package goxjanskloon.starvecraft.mixin;
import goxjanskloon.starvecraft.entity.player.SanityManager;
import goxjanskloon.starvecraft.entity.player.SanityManagerGetter;
import goxjanskloon.starvecraft.item.Items;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(PlayerEntity.class) public abstract class PlayerEntityMixin implements SanityManagerGetter{
    @Unique protected SanityManager sanityManager=new SanityManager();
    @Unique protected int garlandTicker=0;
    @Override public SanityManager getSanityManager(){
        return sanityManager;
    }
    @Shadow public abstract ItemStack getEquippedStack(EquipmentSlot slot);
    @Inject(method="tick",at=@At("TAIL")) public void tick(CallbackInfo ci){
        if((Object)this instanceof ServerPlayerEntity serverPlayer){
            sanityManager.update(serverPlayer);
            ItemStack headStack=getEquippedStack(EquipmentSlot.HEAD);
            if(headStack.isOf(Items.GARLAND)&&++garlandTicker>=1200){
                garlandTicker=0;
                headStack.damage(1,(LivingEntity)(Object)this,EquipmentSlot.HEAD);
            }
        }
    }
    @Inject(method="readCustomDataFromNbt",at=@At("TAIL")) public void readCustomDataFromNbt(NbtCompound nbt,CallbackInfo ci){
        sanityManager.readNbt(nbt);
    }
    @Inject(method="writeCustomDataToNbt",at=@At("TAIL")) public void writeCustomDataToNbt(NbtCompound nbt,CallbackInfo ci){
        sanityManager.writeNbt(nbt);
    }
}