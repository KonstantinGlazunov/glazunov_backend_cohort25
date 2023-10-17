package de.ait.task_05.services;

import de.ait.task_05.dtos.NewSiteDto;
import de.ait.task_05.dtos.SiteDto;

import java.util.List;

public interface SitesService {

    SiteDto addSite(NewSiteDto newSite);

    List<SiteDto> getSites();

    SiteDto getSite(Long siteId);


    SiteDto deleteSite(Long siteId);
}
