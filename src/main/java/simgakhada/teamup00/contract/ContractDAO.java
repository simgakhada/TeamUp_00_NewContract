package simgakhada.teamup00.contract;

import simgakhada.teamup00.contract.contractenum.ContractSortSet;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
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
        PreparedStatement ps;
        int result = 0;
        String query = prop.getProperty("insertInfo");

        System.out.println("[연락처 추가] 지금부터 연락처 등록에 필요한 정보들을 입력합니다.");
        try {
            ps = con.prepareStatement(query);
            System.out.print("이름을 입력해주세요.: ");
            String name = sc.nextLine();
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
            if(result != 0)
                System.out.println("모든 유효성 검사에 통과하여 연락처가 정상적으로 추가되었습니다.");
            System.out.println();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Connection con)
    {
        Scanner sc = new Scanner(System.in);
        PreparedStatement ps;
        PreparedStatement ps2;
        String query = prop.getProperty("deleteInfo");
        String query2 = prop.getProperty("deleteInfoUpgrade");
        int result = 0;


        try {
            System.out.println("[연락처 삭제] 연락처를 삭제합니다.");
            System.out.print("삭제하실 연락처의 이름을 입력해주세요.: ");
            String name = sc.nextLine();

            boolean dCheckName = validation.dCheckName(name);
            if(!dCheckName)
            {
                System.out.println("중복된 이름이 존재하여 전화번호를 요구합니다.");
                System.out.print(name + "의 전화번호를 입력해주세요.: ");
                String phone = sc.nextLine();
                ps2 = con.prepareStatement(query2);
                ps2.setString(1, name);
                ps2.setString(2, phone);
                result = ps2.executeUpdate();
            }
            else
            {
                ps = con.prepareStatement(query);
                ps.setString(1, name);
                result = ps.executeUpdate();
            }

            System.out.println();
            if (result != 0)
            {
                System.out.println(name + "에 대한 모든 연락처 정보를 삭제하였습니다.");
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
        PreparedStatement ps;
        PreparedStatement ps2;
        PreparedStatement ps3;
        String query = prop.getProperty("updateInfo");
        String query2 = prop.getProperty("searchByName");
        String query3 = prop.getProperty("updateInfoUpgrade");
        ResultSet rs;

        boolean vCheckPN;
        boolean dCheckPN;
        boolean vCheckEmail;
        boolean dCheckEmail;
        boolean vCheckBirth;
        String phone = "";
        String oldPhone = "";
        String oldEmail = "";
        String oldAddress = "";
        String oldBirth = "";
        int result = 0;



        try {
            System.out.print("변경을 원하는 연락처의 이름을 입력해 주세요.: ");
            String name = sc.nextLine();
            boolean dCheckName = validation.dCheckName(name);
            if(!dCheckName)
            {
                System.out.println("중복된 이름이 존재하여 전화번호를 요구합니다.");
                System.out.print(name + "의 전화번호를 입력해주세요.: ");
                phone = sc.nextLine();
            }
            System.out.println("연락처 정보 수정을 시작합니다.");
            System.out.println("변경하지 않고자 하는 정보가 있을 경우 '건너뛰기'을 입력하면 해당 내용은 변경되지 않습니다.");
            System.out.print("새로운 이름을 입력해 주세요.: ");
            String newName = sc.nextLine();
            System.out.print("새로운 전화번호를 입력해주세요. (입력 예시: 01012345678): ");
            String newPhone = sc.nextLine();
            System.out.print("새로운 이메일을 입력해주세요. (입력 예시: exampleid@domain.com): ");
            String email = sc.nextLine();
            System.out.print("새로운 주소를 입력해주세요.: ");
            String address = sc.nextLine();
            System.out.print("새로운 생년월일를 입력해주세요. (입력 예시: 20010101): ");
            String birth = sc.nextLine();

            ps2 = con.prepareStatement(query2);
            ps2.setString(1, name);
            rs = ps2.executeQuery();
            while (rs.next())
            {
                oldPhone = rs.getString("PHONE");
                oldEmail = rs.getString("EMAIL");
                oldAddress = rs.getString("ADDRESS");
                oldBirth = rs.getString("BIRTH");
            }
            if(newName.equals("건너뛰기"))
            {
                newName = name;
            }
            if(newPhone.equals("건너뛰기"))
            {
                vCheckPN = true;
                dCheckPN = true;
                newPhone = oldPhone;
            }
            else
            {
                vCheckPN = validation.vCheckPN(newPhone);
                dCheckPN = validation.dCheckPN(newPhone);
            }
            if(email.equals("건너뛰기"))
            {
                vCheckEmail = true;
                dCheckEmail = true;
                email = oldEmail;
            }
            else
            {
                vCheckEmail = validation.vCheckEmail(email);
                dCheckEmail = validation.dCheckEmail(email);
            }
            if(address.equals("건너뛰기"))
            {
                address = oldAddress;
            }
            if(birth.equals("건너뛰기"))
            {
                vCheckBirth = true;
                birth = oldBirth;
            }
            else
            {
                vCheckBirth = validation.vCheckBirth(birth);
            }

            System.out.println();
            System.out.println("모든 입력이 완료되어 전화번호, 이메일, 주소에 대한 유효성 검사를 실시합니다.");
            ps = con.prepareStatement(query);
            ps3 = con.prepareStatement(query3);
            if(vCheckPN && dCheckPN && vCheckEmail && dCheckEmail && vCheckBirth)
            {
                if (!dCheckName)
                {
                    ps3.setString(6, name);
                    ps3.setString(7, phone);
                    ps3.setString(1, newName);
                    ps3.setString(2, newPhone);
                    ps3.setString(3, email);
                    ps3.setString(4, address);
                    ps3.setString(5, birth);
                    result = ps3.executeUpdate();
                }
                else
                {
                    ps.setString(6, name);
                    ps.setString(1, newName);
                    ps.setString(2, newPhone);
                    ps.setString(3, email);
                    ps.setString(4, address);
                    ps.setString(5, birth);
                    result = ps.executeUpdate();
                }
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
            if(result != 0)
                System.out.println("모든 유효성 검사에 통과하여 연락처가 정상적으로 수정되었습니다.");
            System.out.println();
        } catch (SQLException e) {
            System.out.println("연락처 수정에 실패했습니다.");
            // throw new RuntimeException(e);
        }
    }

    public void lookUp(Connection con, String num)
    {
        ContractSortSet sortSet = ContractSortSet.values()[Integer.parseInt(num)];
        Statement stmt;
        String query = sortSet.getQuery();
        ResultSet rs;

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(prop.getProperty(query));
            if(rs.next())
                do {
                    System.out.println("이름: " + rs.getString("NAME")
                            + " 전화번호: " + rs.getString("PHONE")
                            + " 이메일: " + rs.getString("EMAIL")
                            + " 주소: " + rs.getString("ADDRESS")
                            + " 생년월일: " + rs.getString("BIRTH"));
                } while (rs.next());
            else
                System.out.println("조회 결과가 존재하지 않습니다.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println();
    }
}
