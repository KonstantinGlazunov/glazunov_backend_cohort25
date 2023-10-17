package de.ait.task_05.dtos;


import de.ait.task_05.models.Site;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SiteDto {
    private Long id;
    private String name;

    public static SiteDto from(Site site){
        return SiteDto.builder()
                .id(site.getId())
                .name(site.getName())
                .build();
    }
}
