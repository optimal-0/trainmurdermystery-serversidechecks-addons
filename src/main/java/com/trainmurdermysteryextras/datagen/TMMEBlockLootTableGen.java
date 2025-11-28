package com.trainmurdermysteryextras.datagen;

import com.trainmurdermysteryextras.index.TMMEBlocks;
import dev.doctor4t.trainmurdermystery.block.OrnamentBlock;
import dev.doctor4t.trainmurdermystery.block.PanelBlock;
import dev.doctor4t.trainmurdermystery.block.property.OrnamentShape;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.Direction;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;

public class TMMEBlockLootTableGen extends FabricBlockLootTableProvider {

    public TMMEBlockLootTableGen(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {

        this.addSelfDrop(TMMEBlocks.GOLDEN_GLASS_PANEL_LOWER);
        this.addSelfDrop(TMMEBlocks.GOLDEN_GLASS_PANEL_UPPER);
        this.addSelfDrop(TMMEBlocks.GOLDEN_GLASS_PANEL_TOP);
        this.addSelfDrop(TMMEBlocks.GOLDEN_GLASS_PANEL_BOTTOM);
        this.addSelfDrop(TMMEBlocks.GOLDEN_GLASS_PANEL_RIGHT);
        this.addSelfDrop(TMMEBlocks.GOLDEN_GLASS_PANEL_LEFT);
        this.addSelfDrop(TMMEBlocks.GOLDEN_GLASS_PANEL_TOP_RIGHT);
        this.addSelfDrop(TMMEBlocks.GOLDEN_GLASS_PANEL_TOP_LEFT);
        this.addSelfDrop(TMMEBlocks.GOLDEN_GLASS_PANEL_BOTTOM_RIGHT);
        this.addSelfDrop(TMMEBlocks.GOLDEN_GLASS_PANEL_BOTTOM_LEFT);
        this.addSelfDrop(TMMEBlocks.GOLDEN_GLASS_PANEL_BROKEN);
        this.addSelfDrop(TMMEBlocks.GOLDEN_GLASS_PANEL_EMPTY);


    }


    private void addSelfDrop(Block block) {
        this.addSelfDrop(block, this::drops);
    }

    private void addSelfDrop(Block block, Function<Block, LootTable.Builder> function) {
        if (block.getHardness() == -1.0f) {
            // Register drops as nothing if block is unbreakable
            this.addDrop(block, dropsNothing());
        } else {
            this.addDrop(block, function);
        }
    }


    private ConstantLootNumberProvider count(float value) {
        return ConstantLootNumberProvider.create(value);
    }


}
