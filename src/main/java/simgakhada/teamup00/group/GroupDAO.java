package simgakhada.teamup00.group;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

import static simgakhada.teamup00.template.JDBCTemplate.*;


/**
 * GroupDAO
 * 그룹의 추가, 수정, 삭제를 구현하는 메소드입니다.
 * 분리된 '그룹 조회' 항목도 해당 클래스 내에 추가합니다.
 */
public class GroupDAO {

    private Properties prop = new Properties();

    public GroupDAO() {
        try {
            String url = " ";
            prop.loadFromXML(new FileInputStream(url));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void add() { // 그룹 추가

        Scanner sc = new Scanner(System.in);
        PreparedStatement pstmt = null;
        Connection con = null;
        int result = 0;

        String query = prop.getProperty("insertInto");

        System.out.println("추가하실 그룹의 정보를 입력해주세요.");

        System.out.println("이름을 입력 해주세요. : ");
        String name = sc.nextLine();

        System.out.println("전화번호를 입력 해주세요 : ");
        String phone = sc.nextLine();

        System.out.println("E-mail 을 입력 해주세요. : ");
        String email = sc.nextLine();

        System.out.println("주소를 입력 해주세요. : ");
        String address = sc.nextLine();

        System.out.println("생년월일 8자리를 입력 해주세요. : ");
        String birth = sc.nextLine();

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,name);
            pstmt.setString(2,phone);
            pstmt.setString(3,email);
            pstmt.setString(4,address);
            pstmt.setString(5,birth);

            result = pstmt.executeUpdate();

            if (result == 1) {
                System.out.printf("변경에 성공 하였습니다.");
            } else {
                System.out.println("다시 시도 하세요.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
        }
    }

    public void delete() { // 그룹 삭제

        Scanner sc = new Scanner(System.in);
        PreparedStatement pstmt = null;
        Connection con = null;
        int result = 0;

        Properties prop = new Properties();

        System.out.println("삭제하실 그룹명을 입력 해주세요. : ");
        String groupDelete = sc.nextLine();

        try {
            pstmt = con.prepareStatement("deleteInfo");
            pstmt.setString(1,groupDelete);

            result = pstmt.executeUpdate();

            if (result == 1) {
                System.out.println("입력하신 그룹이 성공적으로 삭제되었습니다.");
            } else {
                System.out.println("다시 시도하세요.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
        }
    }

    public void update() { // 그룹 변경

        Scanner sc = new Scanner(System.in);

        Connection con = null;
        PreparedStatement pstmt = null;
        int result = 0;

        Properties prop = new Properties();

        System.out.println("변경하실 그룹명을 입력해주세요.");
        String updateGroup = sc.next();

        try {
            pstmt = con.prepareStatement("updateGroup");
            pstmt.setString(1,updateGroup);

            result = pstmt.executeUpdate();

            if (result == 1) {
                System.out.println("입력하신 그룹명이 성공적으로 변경 되었습니다.");
            } else {
                System.out.println("다시 시도하세요.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
        }
    }

    public void loopUp() { // 그루룹
        Scanner sc = new Scanner(System.in);

        PreparedStatement pstmt = null;
        Connection con = null;
        int result = 0;

        Properties prop = new Properties();

        System.out.println("조회하실 그룹명을 입력 해주세요.");
        String loopupGroup = sc.next();

        try {
            pstmt = con.prepareStatement("loopupGroup");
            pstmt.setString(1,loopupGroup);

            result = pstmt.executeUpdate();

            if (result == 1) {
                System.out.println("조회하신 그룹 정보입니다." + loopupGroup);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
          close(con);
          close(pstmt);
        }
    }

}
