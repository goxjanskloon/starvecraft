package goxjanskloon.starvecraft.mixin;
import goxjanskloon.starvecraft.Starvecraft;
import goxjanskloon.starvecraft.entity.player.SanityManager;
import goxjanskloon.starvecraft.entity.player.SanityManagerGetter;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.profiler.Profilers;
import net.minecraft.util.profiler.Profiler;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(InGameHud.class) public abstract class InGameHudMixin{
    @Unique private static final Identifier SANITY_FULL_TEXTURE=Identifier.of(Starvecraft.MOD_ID,"hud/sanity_full");
    @Unique private static final Identifier SANITY_HALF_TEXTURE=Identifier.of(Starvecraft.MOD_ID,"hud/sanity_half");
    @Unique private static final Identifier SANITY_EMPTY_TEXTURE=Identifier.of(Starvecraft.MOD_ID,"hud/sanity_empty");
    @Shadow private int ticks;
    @Shadow @Final private Random random;
    @Unique private void renderSanity(DrawContext context,PlayerEntity player,int top,int right){
        SanityManager sanityManager=((SanityManagerGetter)player).getSanityManager();
        int sanityLevel=MathHelper.ceil(sanityManager.getSanityLevel());
        boolean isVibrating=sanityManager.getModifier()<0&&ticks%(sanityLevel*3+1)==0;
        for(int i=0;i<10;++i){
            int k=i<<1,j=right-(k<<2)-9,l=isVibrating?top+random.nextInt(3)-1:top;
            context.drawGuiTexture(RenderLayer::getGuiTextured,SANITY_EMPTY_TEXTURE,j,l,9,9);
            if(k<sanityLevel)
                context.drawGuiTexture(RenderLayer::getGuiTextured,(k|1)<sanityLevel?SANITY_FULL_TEXTURE:SANITY_HALF_TEXTURE,j,l,9,9);
        }
    }
    @Shadow @Nullable protected abstract PlayerEntity getCameraPlayer();
    @Inject(method="renderStatusBars",at=@At("TAIL")) public void renderStatusBars(DrawContext context,CallbackInfo ci){
        PlayerEntity playerEntity=getCameraPlayer();
        if(playerEntity!=null){
            Profiler profiler=Profilers.get();
            profiler.push("sanity");
            renderSanity(context,playerEntity,context.getScaledWindowHeight()-59,(context.getScaledWindowWidth()>>1)+91);
            profiler.pop();
        }
    }
}
