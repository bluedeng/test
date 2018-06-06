package classes;

/**
 * @author dengtianzhi
 * @version 1.0
 * @created 2016/10/11 下午3:46
 */
public class JavaPerson {

    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "JavaPersion{" + "name='" + name + '\'' + ", age=" + age + '}';
    }

    public static void main(String... args) {
        System.out.println(new JavaPerson().toString());
    }
}