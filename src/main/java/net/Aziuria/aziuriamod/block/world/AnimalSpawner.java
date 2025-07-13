package net.Aziuria.aziuriamod.block.world;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.List;
import java.util.Random;

public class AnimalSpawner {

    private static class AnimalChance {
        EntityType<?> type;
        double chance;  // e.g., 0.3 = 30%

        AnimalChance(EntityType<?> type, double chance) {
            this.type = type;
            this.chance = chance;
        }
    }

    // List of animals with spawn chances
    private static final List<AnimalChance> ANIMALS = List.of(
            new AnimalChance(EntityType.SHEEP, 0.35),
            new AnimalChance(EntityType.COW, 0.35),
            new AnimalChance(EntityType.PIG, 0.35),
            new AnimalChance(EntityType.CHICKEN, 0.35),
            new AnimalChance(EntityType.RABBIT, 0.01),
            new AnimalChance(EntityType.WOLF, 0.01),
            new AnimalChance(EntityType.HORSE, 0.01)
    );

    private static final int MIN_SPAWN_RADIUS = 20;
    private static final int MAX_SPAWN_RADIUS = 40;

    public static void spawnAnimalsNearPlayer(ServerLevel level, ServerPlayer player, Random random) {
        BlockPos playerPos = player.blockPosition();

        // Calculate total weight sum once
        double totalChance = ANIMALS.stream().mapToDouble(a -> a.chance).sum();

        for (int i = 0; i < 9; i++) {
            // Random distance between MIN_SPAWN_RADIUS and MAX_SPAWN_RADIUS
            double distance = MIN_SPAWN_RADIUS + random.nextDouble() * (MAX_SPAWN_RADIUS - MIN_SPAWN_RADIUS);
            double angle = random.nextDouble() * 2 * Math.PI;

            int x = playerPos.getX() + (int)(distance * Math.cos(angle));
            int z = playerPos.getZ() + (int)(distance * Math.sin(angle));
            int y = level.getHeight(Heightmap.Types.WORLD_SURFACE, x, z);

            BlockPos spawnPos = new BlockPos(x, y, z);

            // Pick animal based on weighted chance
            double roll = random.nextDouble() * totalChance;
            double cumulative = 0.0;
            EntityType<?> chosenType = null;
            for (AnimalChance ac : ANIMALS) {
                cumulative += ac.chance;
                if (roll <= cumulative) {
                    chosenType = ac.type;
                    break;
                }
            }

            if (chosenType != null) {
                var animal = chosenType.create(level);
                if (animal != null) {
                    animal.moveTo(spawnPos.getX() + 0.5, spawnPos.getY(), spawnPos.getZ() + 0.5, 0, 0);
                    level.addFreshEntity(animal);
                }
            }
        }
    }
}