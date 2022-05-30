package org.matsim.analysis;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.events.LinkLeaveEvent;
import org.matsim.api.core.v01.events.handler.LinkLeaveEventHandler;

import java.util.HashMap;
import java.util.Map;

public class HourlyLinkVolumeHandler implements LinkLeaveEventHandler {
    private final Map<Integer, Integer> hourToVehicles = new HashMap<>();

    @Override
    public void handleEvent(LinkLeaveEvent linkLeaveEvent) {
        if (linkLeaveEvent.getLinkId().equals(Id.createLinkId("4614626650002f"))) {
            var leaveTime = linkLeaveEvent.getTime();
            var hour = (int) leaveTime / 3600;

            var currentVehicles = hourToVehicles.get(hour);
            if (currentVehicles == null) {
                hourToVehicles.put(hour, 1);
            } else {
                hourToVehicles.put(hour, currentVehicles + 1);
            }
        }
    }

    public Map<Integer, Integer> getHourToVehicles() {
        return hourToVehicles;
    }
}
