package simgakhada.teamup00.group;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

/**
 * GroupService
 * 그룹 검색에 필요한 기능을 구현하는 클래스입니다.
 */
public class GroupService
{
    public void updateContractInGroup(Connection con)
    {
        Scanner sc = new Scanner(System.in);
        PreparedStatement ps;
        PreparedStatement ps2;
        ResultSet rs;
        Properties prop = new Properties();
        int result = 0;
        boolean flag = false;

        System.out.println();
        System.out.println("[그룹 내 연락처 변경] 연락처를 그룹으로 이동시킬 수 있습니다.");
        System.out.print("그룹 이름을 입력해주세요.: ");
        String groupName = sc.nextLine();

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            String query = prop.getProperty("updateContractInGroup");
            String query2 = prop.getProperty("doSomethingContractInGroupSupporter");
            ps2 = con.prepareStatement(query2);
            ps2.setString(1, groupName);
            rs = ps2.executeQuery();
            while(rs.next())
                flag = true;

            if(flag)
            {
                System.out.print("그룹에 추가할 연락처의 이름을 입력해주세요.: ");
                String name = sc.nextLine();
                ps = con.prepareStatement(query);
                ps.setString(1, groupName);
                ps.setString(2, name);
                result = ps.executeUpdate();

                if(result != 0)
                {
                    System.out.println(name + "의 연락처가 그룹 " + groupName + "에 " + "성공적으로 이동되었습니다.");
                }
                else
                {
                    System.out.println("연락처 이동에 실패하였습니다.");
                    System.out.println("연락처 정보가 존재하지 않습니다.");
                }
            }
            else
                System.out.println("존재하지 않는 그룹입니다.");
            System.out.println();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteContractInGroup(Connection con)
    {
        Scanner sc = new Scanner(System.in);
        PreparedStatement ps;
        PreparedStatement ps2;
        Properties prop = new Properties();
        ResultSet rs;
        int result = 0;
        boolean flag = false;

        System.out.println("[그룹 내 연락처 삭제] 연락처를 그룹에서 제외할 수 있습니다.");
        System.out.print("그룹 이름을 입력해주세요.: ");
        String groupName = sc.nextLine();

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            String query = prop.getProperty("deleteContractInGroup");
            String query2 = prop.getProperty("doSomethingContractInGroupSupporter");
            ps2 = con.prepareStatement(query2);
            ps2.setString(1, groupName);
            rs = ps2.executeQuery();
            while(rs.next())
                flag = true;

            if(flag)
            {
                System.out.print("그룹에서 제외할 연락처의 이름을 입력해주세요.: ");
                String name = sc.nextLine();

                ps = con.prepareStatement(query);
                ps.setString(1, name);
                result = ps.executeUpdate();

                if(result != 0)
                {
                    System.out.println("그룹 " + groupName + "에서 " + name + "의 연락처가 성공적으로 제외되었습니다.");
                }
                else
                {
                    System.out.println("연락처 제외에 실패하였습니다.");
                    System.out.println("연락처 정보가 존재하지 않습니다.");
                }
            }
            else
                System.out.println("존재하지 않는 그룹입니다.");
            System.out.println();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
