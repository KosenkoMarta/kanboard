package utils.database;

public class Queries {
    public final static String QUERY_SELECT_TASKS_ALL="select id, title from kanboard.tasks";
    public final static String QUERY_SELECT_TASK_BY_ID="select id, title from kanboard.tasks where id=?";
    public final static String QUERY_SELECT_TASK_BY_TITLE="select id from kanboard.tasks where title=?";
    public final static String QUERY_SELECT_COMMENT_ID="select id from kanboard.comments where task_id=? AND user_id=? AND comment=?";
    public final static String QUERY_SELECT_PROJECT_ID="select id from kanboard.projects where name=?";
    public final static String QUERY_SELECT_PROJECT_BY_NAME="select id, name, owner_id from kanboard.projects where name=?";
}
