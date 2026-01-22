package io.thatonecoder.totallyfine.mixin.glint;

import io.thatonecoder.totallyfine.config.TotallyFineConfig;
import net.minecraft.entity.living.LivingEntity;
import net.minecraft.client.render.model.Model;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.layer.AbstractArmorLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractArmorLayer.class)
public class AbstractArmorLayerMixin {

    @Inject(
        method = "renderEnchantmentGlint",
        at = @At("HEAD"),
        cancellable = true
    )
    private void disableGlint(LivingEntity entity, Model model, float walkAnimationProgress, float walkAnimationSpeed, float tickDelta, float bob, float yaw, float pitch, float scale, CallbackInfo ci) {
        if (!TotallyFineConfig.instance.glint.get()) {
            ci.cancel();
        }
    }
}
