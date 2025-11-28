package com.trainmurdermysteryextras.index;

import com.trainmurdermysteryextras.block.BrokenGlassPanelBlock;
import com.trainmurdermysteryextras.block.InvisibleStepablePanelBlock;
import com.trainmurdermysteryextras.block.StepablePanelBlock;
import dev.doctor4t.ratatouille.util.registrar.BlockRegistrar;
import dev.doctor4t.trainmurdermystery.block.PrivacyGlassPanelBlock;
import dev.doctor4t.trainmurdermystery.index.TMMBlocks;
import dev.doctor4t.trainmurdermystery.index.TMMItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.BlockSoundGroup;

public interface TMMEBlocks {
    BlockRegistrar registrar = new BlockRegistrar("trainmurdermysteryextras");

    RegistryKey<net.minecraft.item.ItemGroup> BUILDING_GROUP = TMMItems.BUILDING_GROUP;

    Block STEPABLE_SMOOTH_NAVY_STEEL_PANEL = registrar.createWithItem("stepable_smooth_navy_steel_panel", new StepablePanelBlock(AbstractBlock.Settings.copy(TMMBlocks.STAINLESS_STEEL)), TMMEItems.TMME_BLOCK_GROUP);
    Block STEPABLE_NAVY_STEEL_PANEL = registrar.createWithItem("stepable_navy_steel_panel", new StepablePanelBlock(AbstractBlock.Settings.copy(TMMBlocks.STAINLESS_STEEL)), TMMEItems.TMME_BLOCK_GROUP);
    Block STEPABLE_NAVY_STEEL_TILES_PANEL = registrar.createWithItem("stepable_navy_steel_tiles_panel", new StepablePanelBlock(AbstractBlock.Settings.copy(TMMBlocks.STAINLESS_STEEL)), TMMEItems.TMME_BLOCK_GROUP);
    Block STEPABLE_PANEL = registrar.createWithItem("stepable_panel", new InvisibleStepablePanelBlock(AbstractBlock.Settings.copy(TMMBlocks.STAINLESS_STEEL)), TMMEItems.TMME_BLOCK_GROUP);

    Block GOLDEN_GLASS_PANEL_LOWER = registrar.createWithItem("golden_glass_panel_lower", new BrokenGlassPanelBlock(AbstractBlock.Settings.create().strength(0.3f).sounds(BlockSoundGroup.GLASS).allowsSpawning(Blocks::never)), TMMEItems.TMME_BLOCK_GROUP);
    Block GOLDEN_GLASS_PANEL_UPPER = registrar.createWithItem("golden_glass_panel_upper", new BrokenGlassPanelBlock(AbstractBlock.Settings.create().strength(0.3f).sounds(BlockSoundGroup.GLASS).allowsSpawning(Blocks::never)), TMMEItems.TMME_BLOCK_GROUP);
    Block GOLDEN_GLASS_PANEL_TOP = registrar.createWithItem("golden_glass_panel_top", new BrokenGlassPanelBlock(AbstractBlock.Settings.create().strength(0.3f).sounds(BlockSoundGroup.GLASS).allowsSpawning(Blocks::never)), TMMEItems.TMME_BLOCK_GROUP);
    Block GOLDEN_GLASS_PANEL_BOTTOM = registrar.createWithItem("golden_glass_panel_bottom", new BrokenGlassPanelBlock(AbstractBlock.Settings.create().strength(0.3f).sounds(BlockSoundGroup.GLASS).allowsSpawning(Blocks::never)), TMMEItems.TMME_BLOCK_GROUP);
    Block GOLDEN_GLASS_PANEL_LEFT = registrar.createWithItem("golden_glass_panel_left", new BrokenGlassPanelBlock(AbstractBlock.Settings.create().strength(0.3f).sounds(BlockSoundGroup.GLASS).allowsSpawning(Blocks::never)), TMMEItems.TMME_BLOCK_GROUP);
    Block GOLDEN_GLASS_PANEL_RIGHT = registrar.createWithItem("golden_glass_panel_right", new BrokenGlassPanelBlock(AbstractBlock.Settings.create().strength(0.3f).sounds(BlockSoundGroup.GLASS).allowsSpawning(Blocks::never)), TMMEItems.TMME_BLOCK_GROUP);
    Block GOLDEN_GLASS_PANEL_TOP_RIGHT = registrar.createWithItem("golden_glass_panel_top_right", new BrokenGlassPanelBlock(AbstractBlock.Settings.create().strength(0.3f).sounds(BlockSoundGroup.GLASS).allowsSpawning(Blocks::never)), TMMEItems.TMME_BLOCK_GROUP);
    Block GOLDEN_GLASS_PANEL_TOP_LEFT = registrar.createWithItem("golden_glass_panel_top_left", new BrokenGlassPanelBlock(AbstractBlock.Settings.create().strength(0.3f).sounds(BlockSoundGroup.GLASS).allowsSpawning(Blocks::never)), TMMEItems.TMME_BLOCK_GROUP);
    Block GOLDEN_GLASS_PANEL_BOTTOM_RIGHT = registrar.createWithItem("golden_glass_panel_bottom_right", new BrokenGlassPanelBlock(AbstractBlock.Settings.create().strength(0.3f).sounds(BlockSoundGroup.GLASS).allowsSpawning(Blocks::never)), TMMEItems.TMME_BLOCK_GROUP);
    Block GOLDEN_GLASS_PANEL_BOTTOM_LEFT = registrar.createWithItem("golden_glass_panel_bottom_left", new BrokenGlassPanelBlock(AbstractBlock.Settings.create().strength(0.3f).sounds(BlockSoundGroup.GLASS).allowsSpawning(Blocks::never)), TMMEItems.TMME_BLOCK_GROUP);
    Block GOLDEN_GLASS_PANEL_BROKEN = registrar.createWithItem("golden_glass_panel_broken", new BrokenGlassPanelBlock(AbstractBlock.Settings.create().strength(0.3f).sounds(BlockSoundGroup.GLASS).allowsSpawning(Blocks::never).nonOpaque()), TMMEItems.TMME_BLOCK_GROUP);
    Block GOLDEN_GLASS_PANEL_EMPTY = registrar.createWithItem("golden_glass_panel_empty", new BrokenGlassPanelBlock(AbstractBlock.Settings.create().strength(0.3f).sounds(BlockSoundGroup.GLASS).allowsSpawning(Blocks::never).nonOpaque()), TMMEItems.TMME_BLOCK_GROUP);


    static void initialize() {
        registrar.registerEntries();
    }
}
