package api.models.args.projects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectProperties {
    private String id;
    private String name;
    private String is_active;
    private String token;
    private String last_modified;
    private String is_public;
    private String is_private;
    private String default_swimlane;
    private String show_default_swimlane;
    private String description;
    private String identifier;
    private ProjectUrls url;
}
