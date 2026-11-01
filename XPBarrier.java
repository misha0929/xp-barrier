
package xpbarrier;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.border.WorldBorder;

public class XPBarrier implements ModInitializer {
    private static int lastLevel = -1;
    private static boolean initialized = false;

    @Override
    public void onInitialize() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            ServerPlayer player = server.getPlayerList()
                .getPlayers().stream().findFirst().orElse(null);

            if (player == null) return;

            ServerLevel world = server.overworld();
            WorldBorder border = world.getWorldBorder();

            if (!initialized) {
                BlockPos spawn = world.getSharedSpawnPos();
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
