package goxjanskloon.starvecraft.mixin;
import net.minecraft.component.ComponentMap;
import net.minecraft.item.Item;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
@Mixin(Item.Settings.class) public class Item$SettingsMixin{
    @Redirect(method="<init>",at=@At(value="FIELD",target="Lnet/minecraft/component/DataComponentTypes;DEFAULT_ITEM_COMPONENTS:Lnet/minecraft/component/ComponentMap;",opcode=Opcodes.GETSTATIC)) private ComponentMap getDefaultItemComponents(){
        return goxjanskloon.starvecraft.component.DataComponentTypes.DEFAULT_ITEM_COMPONENTS;
    }
}
