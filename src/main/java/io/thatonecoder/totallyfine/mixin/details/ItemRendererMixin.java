package io.thatonecoder.totallyfine.mixin.glint;

import io.thatonecoder.totallyfine.config.TotallyFineConfig;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.living.LivingEntity;
import net.minecraft.client.resource.model.BakedModel;
import net.minecraft.client.render.entity.ItemRenderer;
import net.minecraft.client.render.model.block.ModelTransformations;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin {

    @Inject(
        method = "renderItemInHand",
        at = @At("HEAD"),
        cancellable = true
    )
    private void hideItemInHand(ItemStack item, LivingEntity entity, ModelTransformations.Type transform, CallbackInfo ci) {
        if (!TotallyFineConfig.instance.renderItemInHand.get()) {
            ci.cancel();
        }
    }
}