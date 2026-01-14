package io.thatonecoder.totallyfine.utils.particleculling;

import io.thatonecoder.totallyfine.config.TotallyFineConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.particle.Particle;
import net.minecraft.client.render.Culler;

public class ParticleCuller {
    public static boolean shouldRender(Particle instance) {
        if (!TotallyFineConfig.instance.particleCulling.get()) {
            return true;
        }

        Culler camera = ((ICuller) Minecraft.getInstance().worldRenderer).getCamera();

        if (camera != null) {
            return camera.isVisible(instance.getBoundingBox());
        }

        return false;
    }
}