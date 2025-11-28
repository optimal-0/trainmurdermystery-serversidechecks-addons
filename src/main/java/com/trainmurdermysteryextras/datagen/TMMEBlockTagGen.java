package com.trainmurdermysteryextras.datagen;

import com.trainmurdermysteryextras.index.TMMEBlocks;
import dev.doctor4t.trainmurdermystery.index.TMMBlocks;
import dev.doctor4t.trainmurdermystery.index.tag.TMMBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class TMMEBlockTagGen extends FabricTagProvider.BlockTagProvider {

    public TMMEBlockTagGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    protected void configure(RegistryWrapper.WrapperLookup arg) {

        this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(TMMEBlocks.GOLDEN_GLASS_PANEL_LOWER)
                .add(TMMEBlocks.GOLDEN_GLASS_PANEL_UPPER)
                .add(TMMEBlocks.GOLDEN_GLASS_PANEL_TOP)
                .add(TMMEBlocks.GOLDEN_GLASS_PANEL_BOTTOM)
                .add(TMMEBlocks.GOLDEN_GLASS_PANEL_RIGHT)
                .add(TMMEBlocks.GOLDEN_GLASS_PANEL_LEFT)
                .add(TMMEBlocks.GOLDEN_GLASS_PANEL_TOP_RIGHT)
                .add(TMMEBlocks.GOLDEN_GLASS_PANEL_TOP_LEFT)
                .add(TMMEBlocks.GOLDEN_GLASS_PANEL_BOTTOM_RIGHT)
                .add(TMMEBlocks.GOLDEN_GLASS_PANEL_BOTTOM_LEFT)
                .add(TMMEBlocks.GOLDEN_GLASS_PANEL_BROKEN)
                .add(TMMEBlocks.GOLDEN_GLASS_PANEL_EMPTY);
    }
}
