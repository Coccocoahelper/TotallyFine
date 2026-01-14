package io.thatonecoder.totallyfine.mixin.particleculling;

import io.thatonecoder.totallyfine.utils.particleculling.ParticleCuller;
import net.minecraft.client.ParticleManager;
import net.minecraft.client.entity.particle.Particle;
import net.minecraft.client.render.vertex.BufferBuilder;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ParticleManager.class)
public class ParticleManagerMixin {

    @Redirect(
        method = "render",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/particle/Particle;render(Lnet/minecraft/client/render/vertex/BufferBuilder;Lnet/minecraft/entity/Entity;FFFFFF)V")
    )
    public void cullParticle(Particle instance, BufferBuilder builder, Entity entity, float f, float g, float h, float i, float j, float k) {
        if (ParticleCuller.shouldRender(instance)) {
            instance.render(builder, entity, f, g, h, i, j, k);
        }
    }

    @Redirect(method = "renderLit", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/particle/Particle;render(Lnet/minecraft/client/render/vertex/BufferBuilder;Lnet/minecraft/entity/Entity;FFFFFF)V"))
    public void cullLitParticle(Particle instance, BufferBuilder builder, Entity entity, float f, float g, float h, float i, float j, float k) {
        if (ParticleCuller.shouldRender(instance)) {
           instance.render(builder, entity, f, g, h, i, j, k);
        }
    }
}