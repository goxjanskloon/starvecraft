package goxjanskloon.starvecraft.component;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.ComponentType;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.Rarity;
import net.minecraft.util.dynamic.Codecs;
import static net.minecraft.component.DataComponentTypes.MAX_STACK_SIZE;
import static net.minecraft.component.DataComponentTypes.LORE;
import static net.minecraft.component.DataComponentTypes.ENCHANTMENTS;
import static net.minecraft.component.DataComponentTypes.REPAIR_COST;
import static net.minecraft.component.DataComponentTypes.ATTRIBUTE_MODIFIERS;
import static net.minecraft.component.DataComponentTypes.RARITY;
public interface DataComponentTypes{
    ComponentType<Float> WETNESS=net.minecraft.component.DataComponentTypes.register("wetness",builder->builder.codec(Codecs.NON_NEGATIVE_FLOAT).packetCodec(PacketCodecs.FLOAT));
    ComponentMap DEFAULT_ITEM_COMPONENTS=ComponentMap.builder().add(MAX_STACK_SIZE, 64).add(LORE, LoreComponent.DEFAULT).add(ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT).add(REPAIR_COST,0).add(ATTRIBUTE_MODIFIERS,AttributeModifiersComponent.DEFAULT).add(RARITY,Rarity.COMMON).add(WETNESS,0f).build();
}
