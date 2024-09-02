package simgakhada.teamup00.contract;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

/**
 * ContractDTO
 * 연락처 등록, 수정, 삭제의 기능을 하는 메소드를 구현하는 클래스입니다.
 * 분리된 '연락처 조회' 항목도 해당 클래스 내에 추가합니다.
 *
 * 추가할 사항
 * 1. update()에서 '다음'을 입력받으면 해당 부분은 업데이트하지 말 것.
 * 2. DTO와 연동시킬 것.
 */
public class ContractDAO
{
    private Properties prop = new Properties();
    ContractValidation validation = new ContractValidation();

    public ContractDAO(String url)
    {
        try {
            prop.loadFromXML(new FileInputStream(url));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(Connection con)
    {
        Scanner sc = new Scanner(System.in);
        ContractDTO dto = new ContractDTO();
        PreparedStatement ps;
        int result = 0;
        String query = prop.getProperty("insertInfo");

        System.out.println("[연락처 추가] 지금부터 연락처 등록에 필요한 정보들을 입력합니다.");
        try {
            ps = con.prepareStatement(query);
            System.out.print("이름을 입력해주세요.: ");
            String name = sc.nextLine();
            dto.setName(name);
            System.out.print("전화번호를 입력해주세요. (입력 예시: 01012345678): ");
            String phone = sc.nextLine();
            System.out.print("이메일을 입력해주세요. (입력 예시: exampleid@domain.com): ");
            String email = sc.nextLine();
            System.out.print("주소를 입력해주세요.: ");
            String address = sc.nextLine();
            System.out.print("생년월일를 입력해주세요. (입력 예시: 20010101): ");
            String birth = sc.nextLine();

            System.out.println();
            System.out.println("모든 입력이 완료되어 전화번호, 이메일, 주소에 대한 유효성 검사를 실시합니다.");

            boolean vCheckPN = validation.vCheckPN(phone);
            boolean dCheckPN = validation.dCheckPN(phone);
            boolean vCheckEmail = validation.vCheckEmail(email);
            boolean dCheckEmail = validation.dCheckEmail(email);
            boolean vCheckBirth = validation.vCheckBirth(birth);

            if(vCheckPN && dCheckPN && vCheckEmail && dCheckEmail && vCheckBirth)
            {
                ps.setString(1,name);
                ps.setString(2,phone);
                ps.setString(3,email);
                ps.setString(4,address);
                ps.setString(5,birth);

                result = ps.executeUpdate();
            }
            else
            {
                System.out.println("유효성 검사에 실패하였습니다.");
                if(!vCheckPN)
                {
                    System.out.println("올바르지 않은 전화번호입니다.");
                }
                if(!dCheckPN)
                {
                    System.out.println("중복된 전화번호입니다.");
                }
                if(!vCheckEmail)
                {
                    System.out.println("올바르지 않은 이메일입니다.");
                }
                if(!dCheckEmail)
                {
                    System.out.println("중복된 이메일입니다.");
                }
                if(!vCheckBirth)
                {
                    System.out.println("올바르지 않은 날짜입니다.");
                }
            }
            if(result == 1)
                System.out.println("모든 유효성 검사에 통과하여 연락처가 정상적으로 추가되었습니다.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Connection con)
    {
        Scanner sc = new Scanner(System.in);
        PreparedStatement ps;
        String query = prop.getProperty("deleteInfo");

        try {
            ps = con.prepareStatement(query);
            System.out.println("[연락처 삭제] 연락처를 삭제합니다.");
            System.out.print("삭제하실 연락처의 이름을 입력해주세요.: ");
            String st = sc.nextLine();
            System.out.println();
            ps.setString(1, st);
            int result = ps.executeUpdate();
            if (result == 1)
            {
                System.out.println(st + "에 대한 모든 연락처 정보를 삭제하였습니다.");
                System.out.println();
            }
            else
            {
                System.out.println("연락처 삭제에 실패하였습니다.");
                System.out.println("존재하지 않는 이름입니다.");
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Connection con)
    {
        Scanner sc = new Scanner(System.in);
        PreparedStatement ps = null;
        int result = 0;
        String query = prop.getProperty("updateInfo");

        try {
            ps = con.prepareStatement(query);
            System.out.print("변경을 원하는 연락처의 이름을 입력해 주세요.: ");
            String name = sc.nextLine();
            System.out.println("연락처 정보 수정을 시작합니다.");
            System.out.println("변경하지 않고자 하는 정보가 있을 경우 '다음'을 입력하면 해당 내용은 변경되지 않습니다.");
            System.out.print("새로운 이름을 입력해 주세요.: ");
            String newName = sc.nextLine();
            System.out.print("새로운 전화번호를 입력해주세요. (입력 예시: 01012345678): ");
            String phone = sc.nextLine();
            System.out.print("새로운 이메일을 입력해주세요. (입력 예시: exampleid@domain.com): ");
            String email = sc.nextLine();
            System.out.print("새로운 주소를 입력해주세요.: ");
            String address = sc.nextLine();
            System.out.print("새로운 생년월일를 입력해주세요. (입력 예시: 20010101): ");
            String birth = sc.nextLine();

            System.out.println();
            System.out.println("모든 입력이 완료되어 전화번호, 이메일, 주소에 대한 유효성 검사를 실시합니다.");

            boolean vCheckPN = validation.vCheckPN(phone);
            boolean dCheckPN = validation.dCheckPN(phone);
            boolean vCheckEmail = validation.vCheckEmail(email);
            boolean dCheckEmail = validation.dCheckEmail(email);
            boolean vCheckBirth = validation.vCheckBirth(birth);

            if(newName.equals("다음"))
            {
                newName = name;
            }
            if(phone.equals("다음"))
            {
                vCheckPN = true;
                dCheckPN = true;
            }
            if(email.equals("다음"))
            {
                vCheckEmail = true;
                dCheckEmail = true;
            }
            if(birth.equals("다음"))
            {
                vCheckBirth = true;
            }

            if(vCheckPN && dCheckPN && vCheckEmail && dCheckEmail && vCheckBirth)
            {
                ps.setString(7,name);
                ps.setString(1,newName);
                ps.setString(2,phone);
                ps.setString(3,email);
                ps.setString(4,address);
                ps.setString(5,birth);
                result = ps.executeUpdate();

            }
            else
            {
                System.out.println("유효성 검사에 실패하였습니다.");
                if(!vCheckPN)
                {
                    System.out.println("올바르지 않은 전화번호입니다.");
                }
                if(!dCheckPN)
                {
                    System.out.println("중복된 전화번호입니다.");
                }
                if(!vCheckEmail)
                {
                    System.out.println("올바르지 않은 이메일입니다.");
                }
                if(!dCheckEmail)
                {
                    System.out.println("중복된 이메일입니다.");
                }
                if(!vCheckBirth)
                {
                    System.out.println("올바르지 않은 날짜입니다.");
                }
            }
            if(result == 1)
                System.out.println("모든 유효성 검사에 통과하여 연락처가 정상적으로 추가되었습니다.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void lookUp(Connection con)
    {

    }
}
