package com.easynull.atherys.registers.commands;

import com.easynull.atherys.core.researches.Research;
import com.easynull.atherys.registers.AsComponents;
import com.easynull.atherys.registers.AsElements;
import com.easynull.atherys.registers.AsResearches;
import com.easynull.atherys.utils.ResearchUtils;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

import java.util.concurrent.CompletableFuture;

public class ResearchCommands {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("atherys").then(Commands.literal("research").requires(source -> source.hasPermission(2))
                .then(Commands.literal("add")
                        .then(Commands.argument("research", StringArgumentType.string())
                                .suggests((ctx, builder) -> suggestResearch(builder))
                                .executes(ctx -> add(ctx.getSource(), StringArgumentType.getString(ctx, "research")))))
                .then(Commands.literal("remove")
                        .then(Commands.argument("research", StringArgumentType.string())
                                .suggests((ctx, builder) -> suggestResearch(builder))
                                .executes(ctx -> remove(ctx.getSource(), StringArgumentType.getString(ctx, "research")))))
                .then(Commands.literal("addAll")
                        .executes(ctx -> addAll(ctx.getSource())))
                .then(Commands.literal("removeAll")
                        .executes(ctx -> removeAll(ctx.getSource())))
                .then(Commands.literal("give")
                        .then(Commands.argument("research", StringArgumentType.string())
                                .suggests((ctx, builder) -> suggestResearch(builder))
                                .executes(ctx -> give(ctx.getSource(), StringArgumentType.getString(ctx, "research")))))));
    }

    private static CompletableFuture<Suggestions> suggestResearch(SuggestionsBuilder builder) {
        AsResearches.researches.values().forEach(r -> builder.suggest(r.getID()));
        return builder.buildFuture();
    }

    private static int add(CommandSourceStack source, String researchId) {
        ServerPlayer player = source.getPlayer();
        Research research = ResearchUtils.getResearch(researchId);
        if (player == null) return 0;
        if(research.isUnlocked()) return 0;
        ResearchUtils.setState(player, research, true);
        return 1;
    }

    private static int remove(CommandSourceStack source, String researchId) {
        ServerPlayer player = source.getPlayer();
        Research research = ResearchUtils.getResearch(researchId);
        if (player == null) return 0;
        if(!research.isUnlocked()) return 0;
        ResearchUtils.setState(player, research, false);
        return 1;
    }

    private static int addAll(CommandSourceStack source) {
        ServerPlayer player = source.getPlayer();
        if (player == null) return 0;
        ResearchUtils.setStateAll(player, true);
        return 1;
    }

    private static int removeAll(CommandSourceStack source) {
        ServerPlayer player = source.getPlayer();
        if (player == null) return 0;
        ResearchUtils.setStateAll(player, false);
        return 1;
    }

    private static int give(CommandSourceStack source, String researchId) {
        ServerPlayer player = source.getPlayer();
        if (player == null) return 0;
        ItemStack stack = new ItemStack(AsElements.ancientPage.asItem());
        stack.set(AsComponents.research, researchId);
        player.addItem(stack);
        return 1;
    }
}
