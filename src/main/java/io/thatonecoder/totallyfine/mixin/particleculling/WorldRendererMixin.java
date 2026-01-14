package io.thatonecoder.totallyfine.mixin.particleculling;

import io.thatonecoder.totallyfine.utils.particleculling.ICuller;
import net.minecraft.client.render.Culler;
import net.minecraft.client.render.world.WorldRenderer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class WorldRendererMixin implements ICuller {
    @Unique
    Culler culler;

    @Inject(method = "setupRender", at = @At("HEAD"))
    public void setupRender(Entity entity, double d, Culler culler, int i, boolean bl, CallbackInfo ci) {
        this.culler = culler;
    }

    @Override
    public Culler getCamera() {
        return this.culler;
    }
}