package api.enums;

public enum ProjectRoles {
    MANAGER("project-manager"),
    MEMBER("project-member"),
    VIEWER("project-viewer");
    private String roleProject;

    ProjectRoles(String roleProject){
        this.roleProject=roleProject;
    }

    public String getRoleProject(){
        return roleProject;
    }
}
