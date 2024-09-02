package simgakhada.teamup00.search;

import simgakhada.teamup00.search.searchenum.SearchSet;
import simgakhada.teamup00.settings.SettingsDAO;
import simgakhada.teamup00.settings.settingsenum.Search;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * SearchService
 * 연락처 검색에 필요한 기능을 구현하는 클래스입니다.
 * 이름, 전화번호(전체), 전화번호(4자리), 이메일, 주소로 검색을 지원합니다.
 * 검색의 편의를 위해 주소는 시, 군 단위의 지역명 두 글자로 통일합니다.
 * 지역명의 예시는 다음과 같습니다.
 * ex) 서울, 인천, 대구, 부산, ...
 * 샘플로 추가한 2천 개의 연락처 정보에는
 * 대한민국에 존재하는 모든 시, 군 중 하나가 랜덤으로 저장되어 있습니다.
 * settings.properties의 값에 따라 검색을 할 수 있도록 구현할 예정입니다.
 */
public class SearchService
{
    public void superSearch(Connection con, String input, int num)
    {
        Properties prop = new Properties();
        SearchSet searchSet = SearchSet.values()[num];
        PreparedStatement ps;
        ResultSet rs;
        String query = searchSet.getQuery();
        Search search;

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            ps = con.prepareStatement(prop.getProperty(query));
            ps.setString(1, input);
            rs = ps.executeQuery();
            if(!rs.next())
            {
                System.out.println("검색 결과가 없습니다.");
                System.out.println("다시 시도해주세요.");
            }
            else
            {
                System.out.println("검색 결과");
                while(rs.next())
                {
                    System.out.println("이름: " + rs.getString("NAME")
                            + " 전화번호: " + rs.getString("PHONE")
                            + " 이메일: " + rs.getString("EMAIL")
                            + " 주소: " + rs.getString("ADDRESS")
                            + " 생년월일: " + rs.getString("BIRTH"));
                }
                search = Search.values()[num];
                System.out.println("현재 저장된 기준으로 검색하여 연락처를 출력하였습니다.");
                System.out.println("검색 기준: " + search.getChoice());
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
