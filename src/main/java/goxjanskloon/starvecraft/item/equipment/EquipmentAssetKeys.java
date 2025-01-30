package goxjanskloon.starvecraft.item.equipment;
import goxjanskloon.starvecraft.Starvecraft;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
public interface EquipmentAssetKeys{
    RegistryKey<EquipmentAsset> GARLAND=RegistryKey.of(net.minecraft.item.equipment.EquipmentAssetKeys.REGISTRY_KEY,Identifier.of(Starvecraft.MOD_ID,"garland"));
}
