package goxjanskloon.starvecraft.item;
import goxjanskloon.starvecraft.Starvecraft;
import goxjanskloon.starvecraft.item.equipment.EquipmentAssetKeys;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
public interface Items{
    Item GARLAND=register("garland",new Item.Settings().maxDamage(120).component(DataComponentTypes.EQUIPPABLE,EquippableComponent.builder(EquipmentSlot.HEAD).equipSound(SoundEvents.ITEM_ARMOR_EQUIP_LEATHER).model(EquipmentAssetKeys.GARLAND).damageOnHurt(false).build()).component(goxjanskloon.starvecraft.component.DataComponentTypes.SANITY_MODIFIER,1/2250f).component(goxjanskloon.starvecraft.component.DataComponentTypes.PERISHABLE,true));
    static Item register(String id,Item.Settings settings){
        RegistryKey<Item> key=RegistryKey.of(RegistryKeys.ITEM,Identifier.of(Starvecraft.MOD_ID,id));
        Item item=new Item(settings.registryKey(key));
        if(item instanceof BlockItem blockItem)
            blockItem.appendBlocks(Item.BLOCK_ITEMS,item);
        return Registry.register(Registries.ITEM,key,item);
    }
    static void initialize(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(itemGroup->itemGroup.add(GARLAND));
    }
}
