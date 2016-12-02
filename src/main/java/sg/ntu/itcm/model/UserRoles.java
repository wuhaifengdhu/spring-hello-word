package sg.ntu.itcm.model;

import sg.ntu.itcm.constants.UserRole;
import sg.ntu.itcm.constants.enumerator.UserRoleEnumerator;

public class UserRoles {
    private Integer id;

    private String userUuid;

    private String role;
    
    public UserRoles() {
        super();
    }
    
    public UserRoles(final String userUuid, final UserRole role) {
        super();
        this.userUuid = userUuid;
        UserRoleEnumerator userRoleEnumerator = new UserRoleEnumerator();
        this.role = userRoleEnumerator.getUserRoleString(role);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid == null ? null : userUuid.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }
    
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("UserRole [uuid=").append(userUuid).append("]").
        append("[role=").append(role).append("]");
        return builder.toString();
    }
}