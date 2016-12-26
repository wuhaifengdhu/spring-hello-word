package sg.ntu.itcm.email;

public class EmailModel {

    private String subject;

    private String fromEmail;

    private String fromName;

    private String toEmail;

    private String username;

    private String urlLink;
    
    public EmailModel() {
        super();
        fromEmail = "itcm_cap@outlook.com";
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }
    
    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }
    
    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getUrlLink() {
        return urlLink;
    }

    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
    }
    
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Email Body [subject=").append(subject).append("]").append("[fromEmail=").append(fromEmail).append("]").append("[fromName=").append(fromName).append("]")
        .append("[toEmail=").append(toEmail).append("]").append("[username=").append(username).append("]").append("[urlLink=").append(urlLink).append("]");
        return builder.toString();
    }

}
