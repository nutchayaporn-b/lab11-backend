package se331.rest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import se331.rest.entity.Event;
import se331.rest.repository.EventRepository;

import java.util.List;

@Profile("db")
@Repository
public class EventDaoDbImpl implements EventDao {
    @Autowired
    EventRepository eventRepository;

    @Override
    public Integer getEventSize() {
        return Math.toIntExact(eventRepository.count());
    }

    @Override
    public Page<Event> getEvents(Integer pageSize, Integer page) {
        return eventRepository.findAll(PageRequest.of(page-1,pageSize));
    }

    @Override
    public Event getEvent(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Override
        public Page<Event> getEvent(String title, Pageable page) {
        return eventRepository.findByTitle(title,page);
    }
}
