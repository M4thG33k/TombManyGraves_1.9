package com.m4thg33k.tombmanygraves.client.fx;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.profiler.Profiler;
import org.lwjgl.opengl.GL11;

//Borrowed from Botania
public class ParticleRenderDispatcher {
    public static int pathFXCount = 0;
    public static int depthIgnoringPathFXCount = 0;

    public static void dispatch()
    {
        Tessellator tessellator = Tessellator.getInstance();

        Profiler profiler = Minecraft.getMinecraft().mcProfiler;

        GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
        GlStateManager.depthMask(false);
        GlStateManager.enableBlend();
//        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
        GlStateManager.alphaFunc(GL11.GL_GREATER, 0.003921569F);
//        GlStateManager.disableLighting();

        profiler.startSection("path");
        PathFX.dispatchQueuedRenders(tessellator);
        profiler.endSection();

        GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1f);
        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);
        GL11.glPopAttrib();
    }
}
