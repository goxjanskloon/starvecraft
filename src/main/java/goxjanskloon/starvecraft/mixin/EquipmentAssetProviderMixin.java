package goxjanskloon.starvecraft.mixin;
import goxjanskloon.starvecraft.Starvecraft;
import goxjanskloon.starvecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.client.data.EquipmentAssetProvider;
import net.minecraft.client.render.entity.equipment.EquipmentModel;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.function.BiConsumer;
@Mixin(EquipmentAssetProvider.class) public abstract class EquipmentAssetProviderMixin{
    @Inject(method="bootstrap",at=@At("TAIL")) private static void bootstrap(BiConsumer<RegistryKey<EquipmentAsset>,EquipmentModel> equipmentBiConsumer,CallbackInfo ci){
        equipmentBiConsumer.accept(EquipmentAssetKeys.GARLAND,EquipmentModel.builder().addHumanoidLayers(Identifier.of(Starvecraft.MOD_ID,"garland")).build());
    }
}
