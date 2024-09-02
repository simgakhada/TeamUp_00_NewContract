package simgakhada.teamup00.group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * GroupSearch
 * 그룹 검색에 필요한 기능을 구현하는 클래스입니다.
 */
public class GroupSearch {
    private Map<String, GroupSearch> contacts;
    Scanner sc = new Scanner(System.in);
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rset = null;

    public GroupSearch() {
        contacts = new HashMap<>();
    }

    public GroupSearch searchByName(String name) {             // 이름으로 검색
        System.out.println("검색하실 연락처의 이름을 입력해주세요. : ");
        String searchByName = sc.nextLine();
        return contacts.get(name.toLowerCase());
    }

    public GroupSearch searchByPhone(String phone) throws SQLException {     // 전화번호로 검색
        System.out.println("검색하실 연락처의 뒷 4자리를 입력 해주세요. : "); // 뒷 4자리 입력 받기 위한 질문(?)
        String searchByPhone = sc.nextLine();                                  // 뒷 4자리를 입력 받기 위한 입력문(스캐너)

        if (searchByPhone.length() != 4) {                              // 정확하게 4자리만 입력하여 검색하도록 함
            System.out.println("정확하게 4자리만 입력 해주세요.");
            return contacts.get(phone);
        }

        String query = "SELECT phone FROM CONTRACT WHERE SUBSTRING(contract, -4) = ?";
        pstmt = con.prepareStatement(query);
        pstmt.setString(1, searchByPhone);
        rset = pstmt.executeQuery();

        boolean found = false;
        while (rset.next()) {
            String contract = rset.getString("contract");
            System.out.println("검색하신 연락처는 " + contract + " 입니다.");
            found = true;
        }

        if (!found) {
            System.out.println("error! 검색하신 연락처를 찾을 수 없습니다. 다시 입력 해주세요.");
        }

        return null;
    }

    public GroupSearch searchByEmail(String email) {    // email 로 검색
        System.out.println("검색하실 연락처의 E-mail을 입력 해주세요.");
        String searchByEmail = sc.nextLine();
        return contacts.get(email);
    }

    public GroupSearch searByAddress(String address) {     // 주소로 검색
        System.out.println("검색하실 연락처의 주소를 입력 해주세요.");
        String searByAddress = sc.nextLine();
        return contacts.get(address);
    }

}
