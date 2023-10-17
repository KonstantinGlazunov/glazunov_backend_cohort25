package de.ait.task_05.services.impl;

import de.ait.task_05.dtos.NewSiteDto;
import de.ait.task_05.dtos.SiteDto;
import de.ait.task_05.exeptions.RestExeption;
import de.ait.task_05.models.Event;
import de.ait.task_05.models.Site;
import de.ait.task_05.repositories.EventsRepository;
import de.ait.task_05.repositories.SitesRepository;
import de.ait.task_05.services.SitesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SitesServiceImpl implements SitesService {

    private final SitesRepository sitesRepository;
    private final EventsRepository eventsRepository;

    @Override
    public SiteDto addSite(NewSiteDto newSite) {
        Site site = Site.builder()
                .name(newSite.getName())
                .build();
        sitesRepository.save(site);
        return SiteDto.from(site);
    }

    @Override
    public List<SiteDto> getSites() {
        return null;
    }

    @Override
    public SiteDto getSite(Long siteId) {
        return null;
    }

    @Override
    public SiteDto deleteSite(Long siteId) {
        Site site = getSiteOrThrow(siteId);

        Set<Event> events = eventsRepository.findEventsBySiteId(siteId);
        for (Event event : events) {
            event.setSite(null);
        }
        eventsRepository.saveAll(events);

        sitesRepository.delete(site);

        return SiteDto.from(site);
    }

    private Site getSiteOrThrow(Long siteId) {
        return sitesRepository.findById(siteId)
                .orElseThrow(() -> new RestExeption(HttpStatus.NOT_FOUND, "Site with id <" + siteId + "> not found"));
    }
}
