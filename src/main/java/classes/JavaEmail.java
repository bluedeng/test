package classes;

/**
 * @author dengtianzhi
 * @version 1.0
 * @created 2016/10/11 下午3:47
 */
public class JavaEmail {

    private String name;

    private String domain;

    public JavaEmail() {
    }

    public JavaEmail(String name, String domain) {
        this.name = name;
        this.domain = domain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public String toString() {
        return "JavaEmail{" + name + "@" + domain + "}";
    }
}