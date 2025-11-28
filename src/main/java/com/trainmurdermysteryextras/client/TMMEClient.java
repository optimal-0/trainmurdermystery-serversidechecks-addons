package com.trainmurdermysteryextras.client;

import com.trainmurdermysteryextras.index.TMMEBlocks;
import dev.doctor4t.trainmurdermystery.client.TMMClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.client.render.RenderLayer;

public class TMMEClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                TMMEBlocks.GOLDEN_GLASS_PANEL_LOWER,
                TMMEBlocks.GOLDEN_GLASS_PANEL_UPPER,
                TMMEBlocks.GOLDEN_GLASS_PANEL_TOP,
                TMMEBlocks.GOLDEN_GLASS_PANEL_BOTTOM,
                TMMEBlocks.GOLDEN_GLASS_PANEL_RIGHT,
                TMMEBlocks.GOLDEN_GLASS_PANEL_LEFT,
                TMMEBlocks.GOLDEN_GLASS_PANEL_TOP_RIGHT,
                TMMEBlocks.GOLDEN_GLASS_PANEL_TOP_LEFT,
                TMMEBlocks.GOLDEN_GLASS_PANEL_BOTTOM_RIGHT,
                TMMEBlocks.GOLDEN_GLASS_PANEL_BOTTOM_LEFT,
                TMMEBlocks.GOLDEN_GLASS_PANEL_BROKEN,
                TMMEBlocks.GOLDEN_GLASS_PANEL_EMPTY
        );

        TMMClient.CustomModelProvider customModelProvider = new TMMClient.CustomModelProvider();
        ModelLoadingPlugin.register(customModelProvider);
    }
}
