package sg.ntu.itcm.model;

import java.util.Date;

import sg.ntu.itcm.constants.UserLoginType;
import sg.ntu.itcm.constants.enumerator.UserLoginTypeEnumerator;

public class User {
    private Integer id;

    private String userUuid;

    private String firstName;

    private String lastName;

    private Integer age;

    private String gender;

    private String email;

    private String password;

    private String signInProvider;

    private Date createTime;

    private Date modificationTime;

    private String activationStatus;
    
    public User() {
        super();
        this.activationStatus = "NO";
    }
    
    public User(UserLoginType signInType) {
        super();
        UserLoginTypeEnumerator userLoginTypeEnumerator = new UserLoginTypeEnumerator();
        this.signInProvider = userLoginTypeEnumerator.getUserRoleString(signInType);
        this.activationStatus = "NO";
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName == null ? null : firstName.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSignInProvider() {
        return signInProvider;
    }

    public void setSignInProvider(String signInProvider) {
        this.signInProvider = signInProvider == null ? null : signInProvider.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(Date modificationTime) {
        this.modificationTime = modificationTime;
    }

    public String getActivationStatus() {
        return activationStatus;
    }

    public void setActivationStatus(String activationStatus) {
        this.activationStatus = activationStatus == null ? null : activationStatus.trim();
    }
    
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("User [firstName=").append(firstName).append("]")
        .append("[lastName=").append(lastName).append("]")
        .append("[email=").append(email).append("]");
        return builder.toString();
    }
    
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        /**
//         * 测试，写死了角色
//         */
//        List<SimpleGrantedAuthority> authos = new ArrayList<>();
//        SimpleGrantedAuthority sim = new SimpleGrantedAuthority("ROLE_ADMIN");
//        authos.add(sim);
//        return authos;
//    }
}