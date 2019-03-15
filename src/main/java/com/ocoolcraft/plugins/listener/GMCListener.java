package com.ocoolcraft.plugins.listener;

import com.ocoolcraft.plugins.service.DataServiceFactory;
import com.ocoolcraft.plugins.utils.GMCLoggerUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class GMCListener implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        String playerName = event.getPlayer().getName();
        if (DataServiceFactory.getDataService().checkMonitor(playerName)) {
            String eventLog = "Block Placed at: " +
                    GMCLoggerUtil.getInstance().getBlockLocation(event.getBlock()) +
                    " --- type: " + event.getBlock().getType().toString();
            GMCLoggerUtil.getInstance().logEventData(playerName,eventLog);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getPlayer() == null) {
            return;
        }
        String playerName = event.getPlayer().getName();
        if (DataServiceFactory.getDataService().checkMonitor(playerName)) {
            String eventLog = "Block Removed at: " +
                    GMCLoggerUtil.getInstance().getBlockLocation(event.getBlock()) +
                    " --- type: " + event.getBlock().getType().toString();
            GMCLoggerUtil.getInstance().logEventData(playerName,eventLog);
        }
    }

}
