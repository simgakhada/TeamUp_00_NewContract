package simgakhada.teamup00.statistics;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * ContractStatistics
 * '통계' 항목을 구현하는 클래스입니다.
 * 연락처의 개수, 그룹 별 연락처 개수, 마지막에 추가된 연락처 등을 출력합니다.
 */
public class StatisticsService {
    public void numberOfContracts(Connection con)
    {
        Properties prop = new Properties();
        Statement stmt;
        ResultSet rs;
        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            stmt = con.createStatement();
            String query = prop.getProperty("numberOfContracts");
            rs = stmt.executeQuery(query);
            if (rs.next())
                if(rs.getString(1).equals("0"))
                {
                    System.out.println("연락처가 비어있습니다.");
                }
                else
                {
                    System.out.println("현재 연락처에 " + rs.getString(1) + "개의 연락처가 저장되어 있습니다.");
                }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println();
    }

    public void numberOfContractsInGroup(Connection con)
    {
        Properties prop = new Properties();
        Statement stmt;
        ResultSet rs;

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            stmt = con.createStatement();
            String query = prop.getProperty("numberOfContractsInGroup");
            rs = stmt.executeQuery(query);
            if (rs.next())
                if(rs.getString(1).equals("0"))
                {
                    System.out.println("그룹이 모두 비어있습니다.");
                }
                else
                {
                    System.out.println("현재 그룹에 " + rs.getString(1) + "개의 연락처가 저장되어 있습니다.");
                    System.out.println("그룹 별 저장된 연락처는 [그룹 관리 및 조회] 항목을 이용해주세요.");
                }
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println();
    }

    public void findTheLatestAddedContract(Connection con)
    {
        Properties prop = new Properties();
        Statement stmt;
        ResultSet rs;
        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            stmt = con.createStatement();
            String query = prop.getProperty("findTheLatestAddedContract");
            rs = stmt.executeQuery(query);
            System.out.println("마지막으로 추가된 연락처는 다음과 같습니다.");
            while (rs.next())
            {
                System.out.println("이름: " + rs.getString("NAME")
                        + " 전화번호: " + rs.getString("PHONE")
                        + " 이메일: " + rs.getString("EMAIL")
                        + " 주소: " + rs.getString("ADDRESS")
                        + " 생년월일: " + rs.getString("BIRTH"));
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println();
    }
}

