package api.models.args.users;

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
public class UserDetails {
    private Integer id;
    private String username;
    private String password;
    private String is_ldap_user;
    private String name;
    private String email;
    private String google_id;
    private String github_id;
    private String notifications_enabled;
    private String timezone;
    private String language;
    private String disable_login_form;
    private String twofactor_activated;
    private String twofactor_secret;
    private String token;
    private String notifications_filter;
    private String nb_failed_login;
    private String lock_expiration_date;
    private String gitlab_id;
    private String role;
    private String is_active;
    private String avatar_path;
    private String api_access_token;
    private String filter;
    private String theme;
}
