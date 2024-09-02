package simgakhada.teamup00.group;

/**
 * GroupScripts
 * GroupController 클래스에서 사용되는 연속된 print(ln)을
 * 가시성을 위해 별도의 클래스로 분리하고자 합니다.
 * (사용하지 않아도 무방합니다.)
 */
public class GroupScripts {

    private String name;
    private String phone;
    private String email;
    private String address;

    public GroupScripts() {
    }

    public GroupScripts(String name, String phone, String email, String address) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "GroupScripts{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public void groupScriptsController()
    {

    }
}
