package simgakhada.teamup00.group;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * GroupDAO
 * 그룹의 추가, 수정, 삭제를 구현하는 메소드입니다.
 * 분리된 '그룹 조회' 항목도 해당 클래스 내에 추가합니다.
 */
public class GroupDAO
{
    private Properties prop = new Properties();

    public GroupDAO(String url)
    {
        try {
            prop.loadFromXML(new FileInputStream(url));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void print(Connection con)
    {
        Properties prop = new Properties();
        System.out.println("[그룹 출력] 현재 등록된 그룹을 모두 출력합니다.");
        System.out.println("출력 결과");
        Statement stmt;
        ResultSet rs;

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            String query = prop.getProperty("printGroup");
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            if(!rs.next())
            {
                System.out.println("그룹이 존재하지 않습니다.");
            }
            else
                while(rs.next())
                    System.out.println("그룹명: " + rs.getString("GROUP_NAME"));
            System.out.println();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(Connection con)
    {
        Scanner sc = new Scanner(System.in);
        PreparedStatement ps;
        String query = prop.getProperty("insertGroup");
        int result = 0;

        try {
            ps = con.prepareStatement(query);
            System.out.println("[그룹 추가] 그룹을 추가합니다.");
            System.out.print("추가하실 그룹명을 입력해주세요.: ");
            String groupInsert = sc.nextLine();
            System.out.println();

            ps.setString(1, groupInsert);
            result = ps.executeUpdate();

            if (result == 1) {
                System.out.println("입력하신 그룹이 성공적으로 추가되었습니다.");
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Connection con)
    {
        Scanner sc = new Scanner(System.in);
        PreparedStatement ps;
        String query = prop.getProperty("deleteGroup");
        int result = 0;

        try {
            ps = con.prepareStatement(query);
            System.out.println("[그룹 삭제] 그룹을 삭제합니다.");
            System.out.print("삭제하실 그룹명을 입력해주세요.: ");
            String groupDelete = sc.nextLine();
            System.out.println();

            ps.setString(1, groupDelete);
            result = ps.executeUpdate();

            if (result == 1) {
                System.out.println("입력하신 그룹이 성공적으로 삭제되었습니다.");
                System.out.println();
            } else {
                System.out.println("그룹 삭제에 실패했습니다.");
                System.out.println("존재하지 않는 그룹입니다.");
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
        String query = prop.getProperty("updateGroup");

        try {
            ps = con.prepareStatement(query);
            System.out.println("[그룹 이름 변경] 그룹의 이름을 변경합니다.");
            System.out.print("변경하실 그룹의 이름을 입력해주세요.: ");
            String groupOld = sc.nextLine();
            System.out.print("새로운 이름을 입력해주세요.: ");
            String groupNew = sc.nextLine();
            System.out.println();
            ps.setString(2, groupOld);
            ps.setString(1, groupNew);
            int result = ps.executeUpdate();
            if (result == 1)
            {
                System.out.println(groupOld + "을 " + groupNew + "로 변경하였습니다.");
                System.out.println();
            }
            else
            {
                System.out.println("그룹 이름 변경에 실패하였습니다.");
                System.out.println("존재하지 않는 이름입니다.");
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void lookUp(Connection con)
    {
        Scanner sc = new Scanner(System.in);
        PreparedStatement ps;
        ResultSet rs;
        String query = prop.getProperty("lookUpGroup");

        try {
            ps = con.prepareStatement(query);
            System.out.println("[그룹 조회] 그룹에 속한 연락처를 조회합니다.");
            System.out.print("조회하실 그룹의 이름을 입력해주세요.: ");
            String groupLook = sc.nextLine();
            System.out.println();

            ps.setString(1, groupLook);
            rs = ps.executeQuery();
            System.out.println("[" + groupLook + "에 속한 연락처]");
            while (rs.next())
            {
                System.out.println("이름: " + rs.getString("NAME")
                        + " 전화번호: " + rs.getString("PHONE")
                        + " 이메일: " + rs.getString("EMAIL")
                        + " 주소: " + rs.getString("ADDRESS")
                        + " 생년월일: " + rs.getString("BIRTH"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println();
    }
}
