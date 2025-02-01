package goxjanskloon.starvecraft.entity.player;
import goxjanskloon.starvecraft.item.Items;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
public class SanityManager{
    public static final float MAX_SANITY_LEVEL=20f;
    private float sanityLevel=MAX_SANITY_LEVEL,modifier=0f;
    public void add(float sanityLevelModifier){
        sanityLevel=MathHelper.clamp(sanityLevel+sanityLevelModifier,0f,MAX_SANITY_LEVEL);
    }
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
        if(nbt.contains("SanityLevel",NbtElement.NUMBER_TYPE))
            sanityLevel=nbt.getFloat("SanityLevel");
    }
    public void writeNbt(NbtCompound nbt){
        nbt.putFloat("SanityLevel",sanityLevel);
    }
    public void update(ServerPlayerEntity player){
        float currentModifier=0f;
        ServerWorld serverWorld=player.getServerWorld();
        if(serverWorld.isNight()||serverWorld.getLightLevel(player.getBlockPos())<=7){
            for(ItemStack itemStack:player.getHandItems()){
                Item item=itemStack.getItem();
                if(item instanceof BlockItem blockItem&&blockItem.getBlock().getDefaultState().getLuminance()>0){
                    currentModifier=-1/900f;
                    break;
                }
            }
            if(currentModifier==0f)
                currentModifier=-1/60f;
        }else currentModifier=1/4800f;
        if(player.isWet())
            currentModifier-=1/2000f;
        if(player.getEquippedStack(EquipmentSlot.HEAD).isOf(Items.GARLAND))
            currentModifier+=1/2250f;
        add(currentModifier);
        modifier=currentModifier;
    }
}
