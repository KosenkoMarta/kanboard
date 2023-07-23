package api.models.args.tasks;

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

public class TaskDetails {
    private String id;
    private String title;
    private String description;
    private String date_creation;
    private String color_id;
    private String project_id;
    private String column_id;
    private String owner_id;
    private String position;
    private String is_active;
    private String date_completed;
    private String score;
    private String date_due;
    private String category_id;
    private String creator_id;
    private String date_modification;
    private String reference;
    private String date_started;
    private String time_spent;
    private String time_estimated;
    private String swimlane_id;
    private String date_moved;
    private String recurrence_status;
    private String recurrence_trigger;
    private String recurrence_factor;
    private String recurrence_timeframe;
    private String recurrence_basedate;
    private String recurrence_parent;
    private String recurrence_child;
    private String url;
}
