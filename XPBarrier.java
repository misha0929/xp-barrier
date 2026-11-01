   if (player == null) return;

            ServerWorld world = server.getOverworld();
            WorldBorder border = world.getWorldBorder();

            if (!initialized) {
                BlockPos spawn = world.getSpawnPos();
                border.setCenter(spawn.getX() + 0.5, spawn.getZ() + 0.5);
                initialized = true;
            }

            int level = player.experienceLevel;

            if (level != lastLevel) {
                border.setSize(Math.max(1.0, 1.0 + level));
                lastLevel = level;
            }
        });
    }
}
