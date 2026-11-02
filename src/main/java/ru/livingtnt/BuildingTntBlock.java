package ru.livingtnt;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.TNTBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import javax.annotation.Nullable;

public class BuildingTntBlock extends TNTBlock {
    private final StructureType type;

    public BuildingTntBlock(StructureType type) {
        super(AbstractBlock.Properties.create(net.minecraft.block.material.Material.TNT)
                .hardnessAndResistance(0.0F).sound(net.minecraft.block.SoundType.PLANT));
        this.type = type;
    }

    @Override
    protected void explode(World world, BlockPos pos, @Nullable LivingEntity igniter) {
        if (world.isRemote) return;
        world.removeBlock(pos, false);
        world.playSound(null, pos, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 1.0F, 0.85F);
        world.addParticle(ParticleTypes.EXPLOSION_EMITTER, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 0, 0, 0);
        StructureBuilder.build(world, pos, type);
    }
}
