package simgakhada.teamup00.contract;

/**
 * "ContractDTO"
 * 연락처에 대한 DTO(Data Transfer Object)입니다.
 * 이름, 전화번호, 이메일, 주소, 생년월일을 갖고 있으며
 * 모두 private String 자료형으로 선언되어 있습니다.
 *
 * 기존 대비 변경점
 * MySQL DB 내에서 Auto_increment(자동 순번 할당)으로 처리될 int no와
 * '그룹 관리' 항목에서 다룰 예정인 int inGroup을 제외하고,
 * 생년월일을 추가하였습니다.
 */
public class ContractDTO
{
    private String name;
    private String phone;
    private String email;
    private String address;
    private String birthday;

    public ContractDTO() {
    }

    public ContractDTO(String name, String phone, String email, String address, String birthday) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "ContractDTO{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
