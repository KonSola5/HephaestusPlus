package konsola5.hephaestusplus.modifiers;

import io.wispforest.owo.ops.WorldOps;
import konsola5.hephaestusplus.ids.MoarModifierIds;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.phys.Vec3;
import nourl.mythicmetals.misc.BlockBreaker;
import nourl.mythicmetals.misc.MythicParticleSystem;
import nourl.mythicmetals.registry.RegisterCriteria;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.TinkerHooks;
import slimeknights.tconstruct.library.modifiers.hook.interaction.BlockInteractionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InteractionSource;
import slimeknights.tconstruct.library.modifiers.util.ModifierHookMap;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import javax.annotation.ParametersAreNonnullByDefault;

// A copy-paste of a Blast Mining ability from Mythic Metals' BanglumPickaxe.java/BanglumShovel.java,
// translated to Official Mojang mappings.
@SuppressWarnings("deprecation")
@ParametersAreNonnullByDefault
public class BangBangModifier extends Modifier implements BlockInteractionModifierHook {
    @Override
    protected void registerHooks(ModifierHookMap.Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, TinkerHooks.BLOCK_INTERACT);
    }

    @Override
    public int getPriority() {
        return 76; // before Pathing
    }

    @Override
    public InteractionResult afterBlockUse(@NotNull IToolStackView tool, ModifierEntry modifier, UseOnContext context, InteractionSource source) {
        if (FabricLoader.getInstance().isModLoaded("mythicmetals")) {
            if (ModifierUtil.getModifierLevel(context.getItemInHand(), MoarModifierIds.legendary_banglum) > 0) {
                boolean shouldPass = false;
                var world = context.getLevel();
                var player = context.getPlayer();
                var depth = (2 * modifier.getLevel()) - 1;

                if (player != null && !player.getCooldowns().isOnCooldown(tool.getItem()) && !tool.isBroken() && !world.isClientSide()) {
                    var iterator = BlockBreaker.findBlocks(context, depth);

                    for (BlockPos blockPos : iterator) {
                        // if (tool.getItem().isCorrectToolForDrops(world.getBlockState(blockPos))) {
                        if (tool.getDefinitionData().getHarvestLogic().isEffective(tool, world.getBlockState(blockPos))) {
                            WorldOps.breakBlockWithItem(world, blockPos, context.getItemInHand());
                            ToolDamageUtil.damage(tool, 2, player, context.getItemInHand());
                            shouldPass = true;
                        }
                    }
                }

                if (shouldPass) {
                    var pos = context.getClickedPos();
                    var facing = context.getHorizontalDirection();
                    var pos2 = pos.relative(facing, depth);
                    MythicParticleSystem.EXPLOSION_TRAIL.spawn(world, Vec3.atLowerCornerOf(pos), Vec3.atLowerCornerOf(pos2));
                    WorldOps.playSound(world, pos, SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS);

                    RegisterCriteria.BLAST_MINING.trigger((ServerPlayer) player);
                    player.getCooldowns().addCooldown(tool.getItem(), 100);
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return InteractionResult.FAIL;
    }
}

