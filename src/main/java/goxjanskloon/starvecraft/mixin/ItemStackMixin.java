package goxjanskloon.starvecraft.mixin;
import goxjanskloon.starvecraft.component.DataComponentTypes;
import net.minecraft.component.ComponentType;
import net.minecraft.component.MergedComponentMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(ItemStack.class) public abstract class ItemStackMixin{
    @Shadow public abstract void damage(int amount,PlayerEntity player);
    @Shadow @Final MergedComponentMap components;
    @Shadow @Nullable public abstract <T> T set(ComponentType<? super T> type,@Nullable T value);
    @Inject(method="inventoryTick",at=@At("TAIL")) public void inventoryTick(World world,Entity entity,int slot,boolean selected,CallbackInfo ci){
        Integer ticksPerDecay=components.get(DataComponentTypes.TICKS_PER_DECAY);
        if(ticksPerDecay!=null)
            if(ticksPerDecay==1)
                damage(1,null);
            else{
                Integer decayTicker=components.get(DataComponentTypes.DECAY_TICKER);
                if(decayTicker==null)
                    set(DataComponentTypes.DECAY_TICKER,1);
                else if(++decayTicker>=ticksPerDecay){
                    set(DataComponentTypes.DECAY_TICKER,0);
                    damage(1,null);
                }else set(DataComponentTypes.DECAY_TICKER,decayTicker);
            }
    }
}
