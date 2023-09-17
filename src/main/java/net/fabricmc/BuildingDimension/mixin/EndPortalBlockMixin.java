package net.fabricmc.BuildingDimension.mixin;

import net.fabricmc.BuildingDimension.BuildingDimension;
import net.minecraft.block.BlockState;
import net.minecraft.block.EndPortalBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EndPortalBlock.class)
abstract class EndPortalBlockMixin {
    @Inject(
            method = "onEntityCollision",
            at = @At(
                    value = "HEAD"
            ),
            cancellable = true
    )
    private void disableEndPortal(BlockState state, World world, BlockPos pos, Entity entity, CallbackInfo ci) {
        if (world.getRegistryKey().getRegistry().getNamespace().equals(BuildingDimension.MOD_ID)) {
            ci.cancel();
        }
    }
}