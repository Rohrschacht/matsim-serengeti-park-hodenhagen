package org.matsim.analysis;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.events.PersonArrivalEvent;
import org.matsim.api.core.v01.events.PersonDepartureEvent;
import org.matsim.api.core.v01.events.handler.PersonArrivalEventHandler;
import org.matsim.api.core.v01.events.handler.PersonDepartureEventHandler;
import org.matsim.api.core.v01.population.Person;

import java.util.HashMap;
import java.util.Map;

public class SimplePersonEventHandler implements PersonArrivalEventHandler, PersonDepartureEventHandler {

    private final Map<Id<Person>, Double> personToTravelTime = new HashMap<>();

    @Override
    public void handleEvent(PersonDepartureEvent event) {
        var person = event.getPersonId();
        var departureTime = event.getTime();
        personToTravelTime.put(person, departureTime);
        System.out.printf("Departure: %s: %s\n", event.getTime(), event.getPersonId());
    }

    @Override
    public void handleEvent(PersonArrivalEvent event) {
        var person = event.getPersonId();
        var arrivalTime = event.getTime();
        var departureTime = personToTravelTime.get(person);
        var travelTime = arrivalTime - departureTime;
        System.out.printf("Arrival: %s: %s\nTravel time: %s: %s\n", event.getTime(), event.getPersonId(), person, travelTime);
    }

}
