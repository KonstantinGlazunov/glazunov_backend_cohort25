package de.ait.task_05.services.impl;

import de.ait.task_05.dtos.NewSiteDto;
import de.ait.task_05.dtos.SiteDto;
import de.ait.task_05.models.Site;
import de.ait.task_05.repositories.SitesRepository;
import de.ait.task_05.services.SitesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SitesServiceImpl implements SitesService {

    private final SitesRepository sitesRepository;
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
}
