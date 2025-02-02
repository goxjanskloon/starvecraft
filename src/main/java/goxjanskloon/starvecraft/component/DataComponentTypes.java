package goxjanskloon.starvecraft.component;
import goxjanskloon.starvecraft.Starvecraft;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.ComponentType;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import java.util.function.UnaryOperator;
import static net.minecraft.component.DataComponentTypes.MAX_STACK_SIZE;
import static net.minecraft.component.DataComponentTypes.LORE;
import static net.minecraft.component.DataComponentTypes.ENCHANTMENTS;
import static net.minecraft.component.DataComponentTypes.REPAIR_COST;
import static net.minecraft.component.DataComponentTypes.ATTRIBUTE_MODIFIERS;
import static net.minecraft.component.DataComponentTypes.RARITY;
public interface DataComponentTypes{
    ComponentType<Boolean> WET=register("wet",builder->builder.packetCodec(PacketCodecs.BOOLEAN));
    ComponentType<Boolean> WATERPROOF=register("waterproof",builder->builder.packetCodec(PacketCodecs.BOOLEAN));
    ComponentType<Boolean> PERISHABLE=register("perishable",builder->builder.packetCodec(PacketCodecs.BOOLEAN));
    ComponentType<Float> DECAY_SPEED=register("decay_speed",builder->builder.packetCodec(PacketCodecs.FLOAT));
    ComponentType<Float> SANITY_MODIFIER=register("sanity_modifier",builder->builder.packetCodec(PacketCodecs.FLOAT));
    ComponentMap DEFAULT_ITEM_COMPONENTS=ComponentMap.builder().add(MAX_STACK_SIZE,64).add(LORE,LoreComponent.DEFAULT).add(ENCHANTMENTS,ItemEnchantmentsComponent.DEFAULT).add(REPAIR_COST,0).add(ATTRIBUTE_MODIFIERS,AttributeModifiersComponent.DEFAULT).add(RARITY,Rarity.COMMON).add(WET,false).add(WATERPROOF,false).add(PERISHABLE,false).build();
    static <T> ComponentType<T> register(String id,UnaryOperator<ComponentType.Builder<T>> builderOperator){
        return Registry.register(Registries.DATA_COMPONENT_TYPE,Identifier.of(Starvecraft.MOD_ID,id),(builderOperator.apply(ComponentType.builder())).build());
    }
}
