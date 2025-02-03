package goxjanskloon.starvecraft.entity.player;
import goxjanskloon.starvecraft.component.DataComponentTypes;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
public class SanityManager{
    public static final float MAX_SANITY_LEVEL=20f;
    private float sanityLevel=MAX_SANITY_LEVEL,modifier=0f;
    public float getSanityLevel(){
        return sanityLevel;
    }
    public float getModifier(){
        return modifier;
    }
    public void setSanityLevel(float sanityLevel){
        this.sanityLevel=sanityLevel;
    }
    public void setModifier(float modifier){
        this.modifier=modifier;
    }
    public void readNbt(NbtCompound nbt){
        if(nbt.contains("sanityLevel",NbtElement.NUMBER_TYPE))
            sanityLevel=nbt.getFloat("sanityLevel");
    }
    public void writeNbt(NbtCompound nbt){
        nbt.putFloat("sanityLevel",sanityLevel);
    }
    public void update(ServerPlayerEntity player){
        float currentModifier=0f;
        ServerWorld serverWorld=player.getServerWorld();
        if(serverWorld.isNight()||serverWorld.getLightLevel(player.getBlockPos())<=7){
            for(ItemStack itemStack:player.getHandItems())
                if(itemStack.getItem() instanceof BlockItem blockItem&&blockItem.getBlock().getDefaultState().getLuminance()>0){
                    currentModifier=-1/900f;
                    break;
                }
            if(currentModifier==0f)
                currentModifier=-1/60f;
        }else currentModifier=1/4800f;
        if(player.isWet())
            currentModifier-=1/2000f;
        for(ItemStack itemStack:player.getEquippedItems())
            currentModifier+=itemStack.getComponents().getOrDefault(DataComponentTypes.SANITY_MODIFIER,0f);
        sanityLevel=MathHelper.clamp(sanityLevel+(modifier=currentModifier),0f,MAX_SANITY_LEVEL);
    }
}
