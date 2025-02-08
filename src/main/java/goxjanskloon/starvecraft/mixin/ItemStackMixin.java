package goxjanskloon.starvecraft.mixin;
import goxjanskloon.starvecraft.component.DataComponentTypes;
import net.minecraft.component.ComponentType;
import net.minecraft.component.MergedComponentMap;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(ItemStack.class) public abstract class ItemStackMixin{
    @Shadow public abstract int getDamage();
    @Shadow public abstract void setDamage(int damage);
    @Shadow public abstract boolean shouldBreak();
    @Shadow public abstract void decrement(int amount);
    @Unique public void decay(){
        setDamage(getDamage()+1);
        if(shouldBreak())
            decrement(1);
    }
    @Shadow @Final MergedComponentMap components;
    @Shadow @Nullable public abstract <T> T set(ComponentType<? super T> type,@Nullable T value);
    @Inject(method="inventoryTick",at=@At("TAIL")) public void inventoryTick(World world,Entity entity,int slot,boolean selected,CallbackInfo ci){
        int ticksPerDecay=components.getOrDefault(DataComponentTypes.TICKS_PER_DECAY,0);
        if(ticksPerDecay!=0)
            if(ticksPerDecay==1)
                decay();
            else{
                int decayTicker=components.getOrDefault(DataComponentTypes.DECAY_TICKER,0);
                if(decayTicker==0)
                    set(DataComponentTypes.DECAY_TICKER,1);
                else if(++decayTicker>=ticksPerDecay){
                    set(DataComponentTypes.DECAY_TICKER,0);
                    decay();
                }else set(DataComponentTypes.DECAY_TICKER,decayTicker);
            }
    }
}
