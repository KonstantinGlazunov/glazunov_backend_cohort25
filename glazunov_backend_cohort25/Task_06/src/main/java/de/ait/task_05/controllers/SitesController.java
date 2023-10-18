package de.ait.task_05.controllers;

import de.ait.task_05.controllers.api.SitesApi;
import de.ait.task_05.dtos.NewSiteDto;
import de.ait.task_05.dtos.SiteDto;
import de.ait.task_05.services.SitesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class SitesController implements SitesApi {

    private final SitesService sitesService;

    @Override
    public SiteDto addSite(NewSiteDto newSite) {
        return sitesService.addSite(newSite);
    }

    @Override
    public SiteDto deleteSite(Long siteId) {
        return sitesService.deleteSite(siteId);
    }
}
