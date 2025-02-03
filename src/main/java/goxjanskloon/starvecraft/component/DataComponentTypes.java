package goxjanskloon.starvecraft.component;
import goxjanskloon.starvecraft.Starvecraft;
import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;
import java.util.function.UnaryOperator;
public interface DataComponentTypes{
    ComponentType<Boolean> WET=register("wet",builder->builder.packetCodec(PacketCodecs.BOOLEAN));
    ComponentType<Boolean> WATERPROOF=register("waterproof",builder->builder.packetCodec(PacketCodecs.BOOLEAN));
    ComponentType<Integer> TICKS_PER_DECAY=register("ticks_per_decay",builder->builder.codec(Codecs.POSITIVE_INT).packetCodec(PacketCodecs.VAR_INT));
    ComponentType<Integer> DECAY_TICKER=register("decay_ticker",builder->builder.codec(Codecs.POSITIVE_INT).packetCodec(PacketCodecs.VAR_INT));
    ComponentType<Float> SANITY_MODIFIER=register("sanity_modifier",builder->builder.packetCodec(PacketCodecs.FLOAT));
    static <T> ComponentType<T> register(String id,UnaryOperator<ComponentType.Builder<T>> builderOperator){
        return Registry.register(Registries.DATA_COMPONENT_TYPE,Identifier.of(Starvecraft.MOD_ID,id),(builderOperator.apply(ComponentType.builder())).build());
    }
}
