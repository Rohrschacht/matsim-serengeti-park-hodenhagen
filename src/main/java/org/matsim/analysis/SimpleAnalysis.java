package org.matsim.analysis;

import org.matsim.core.events.EventsUtils;

public class SimpleAnalysis {
    public static void main(String[] args) {
        var handler = new SimplePersonEventHandler();
        var manager = EventsUtils.createEventsManager();
        var linkVolumeHandler = new HourlyLinkVolumeHandler();
        manager.addHandler(handler);
        manager.addHandler(linkVolumeHandler);

        EventsUtils.readEvents(manager, "scenarios/serengeti-park-v1.0/output/output-serengeti-park-v1.0-run1/serengeti-park-v1.0-run1.output_events.xml.gz");

        var histogramMap = linkVolumeHandler.getHourToVehicles();
        for (var hours : histogramMap.keySet()) {
            System.out.printf("%d: %d\n", hours, histogramMap.get(hours));
        }
    }
}
