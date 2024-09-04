package simgakhada.teamup00.contract;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static simgakhada.teamup00.template.JDBCTemplate.getConnection;

/**
 * "유효성 검사"
 * 전화번호부에 있어 중요한 정보인 전화번호, 이메일, 생년월일에 대하여
 * 아래 메소드에서 각각에 대한 유효성 검사를 진행한다.
 * 전화번호와 이메일은 중복 여부를 함께 검사한다.
 * 각 메소드 이름의 첫 글자로 구분한다.
 * v는 유효한 형식의 정보인지 검사하는 메소드이다.
 * d는 DB 안에 중복된 값이 존재하는지 검사하는 메소드이다.
 * 유효성 검사를 통과했을 경우 메소드는 true를 반환한다.
 * 아래 메소드 중 단 하나라도 false를 반환할 경우
 * 유효성 검사에 실패한 것으로 간주하고,
 * 입력된 정보를 반려한다.
 *
 * 기존 대비 변경점
 * 각 메소드의 세부 설명 및 동작의 이해를 돕기 위한 주석을 추가하였습니다.
 */

public class ContractValidation
{
    boolean result = false;
    /**
     * "vCheckPN"
     * 입력 받은 전화번호에 대한 유효성을 검사하는 메소드입니다.
     * 대한민국에서 실제 쓰이는 전화번호 대부분의 유효성을 체크할 수 있습니다.
     * 현재 휴대전화 번호인 010-ABCD-EFGH 형태,
     * 과거의 휴대전화 번호인 01A(B)-CDE-FGHI 형태,
     * 일반 전화번호인 0A(B)-CDE(F)-GHIJ 형태,
     * 070, 080 등의 0A0-BCDE-FGHI 형태 중 하나라도 만족하면 true, 만족하지 않으면 false를 반환합니다.
     */
    public boolean vCheckPN(String PhoneNum)
    {
        if (PhoneNum.matches("^(01[0-9]-?[2-9][0-9]{3}-?[0-9]{4}|01[0-9][2-9][0-9]{7})$"))
        {
            result = true;
        }
        else if (PhoneNum.matches("^(02-?[1-9][0-9]{3}-?[0-9]{4}|02[1-9][0-9]{7})$"))
        {
            result = true;
        }
        else if (PhoneNum.matches("^(02-?[2-9]{3}-?[0-9]{4}|02[2-9][0-9]{6})$"))
        {
            result = true;
        }
        else if (PhoneNum.matches("^(0[3-8][0-5]-?[2-9][0-9]{3}-?[0-9]{4}|0[3-8][0-5][2-9][0-9]{7})$"))
        {
            result = true;
        }
        else if (PhoneNum.matches("^(0[3-6][0-5]-?[2-9]{3}-?[0-9]{4}|0[3-6][0-5][2-9][0-9]{6})$"))
        {
            result = true;
        }

        return result;
    }

    /**
     * "dCheckPN"
     * 입력 받은 전화번호에 대한 중복을 검사하는 메소드입니다.
     * MySQL Contract 테이블 내의 PHONE Column에
     * 입력 받은 전화번호와 일치하는 전화번호가 존재한다면 false를,
     * 존재하지 않는다면 true를 반환합니다.
     */
    public boolean dCheckPN(String phoneNum)
    {
        Connection con = getConnection();
        PreparedStatement ps;
        ResultSet rs;
        result = true;
        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            ps = con.prepareStatement(prop.getProperty("duplicateCheckPN"));
            ps.setString(1, phoneNum);
            rs = ps.executeQuery();

            while (rs.next())
                result = false;

        } catch (IOException | SQLException e) {
            result = false;
            //throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * "vCheckEmail"
     * 입력 받은 이메일에 대한 유효성을 검사하는 메소드입니다.
     * 외부 라이브러리를 사용하며, 대부분의 웹사이트에서 사용되는 이메일의 유효성을 체크할 수 있습니다.
     * 아이디에 사용할 수 없는 문자, @의 부재, 도메인 형식 등에서 문제가 발생할 경우 false,
     * 이메일 형식에 맞는 문자열이면 true를 반환합니다.
     */
    public boolean vCheckEmail(String email)
    {
        result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException e) {
            result = false;
        }
        return result;
    }

    /**
     * "dCheckEmail"
     * 입력 받은 이메일에 대한 중복을 검사하는 메소드입니다.
     * MySQL Contract 테이블 내의 EMAIL Column에
     * 입력 받은 이메일과 일치하는 이메일이 존재한다면 false를,
     * 존재하지 않는다면 true를 반환합니다.
     */
    public boolean dCheckEmail(String email)
    {
        Connection con = getConnection();
        PreparedStatement ps;
        ResultSet rs;
        result = true;
        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            ps = con.prepareStatement(prop.getProperty("duplicateCheckEmail"));
            ps.setString(1, email);
            rs = ps.executeQuery();

            while (rs.next())
                result = false;

        } catch (IOException | SQLException e){
            result = false;
            //throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * "vCheckBirth"
     * 입력 받은 생년월일에 대한 유효성을 검사하는 메소드입니다.
     * 날짜 형식(년년년년월월일일)에 해당하면 true, 해당하지 않으면 false를 반환합니다.
     * 모든 날짜에 대해 적용 가능합니다.
     * ex) 4년 마다 돌아오는 윤년(2월 29일)
     * ex2) 월 별 마지막 일자가 상이한 부분 (1월: 31일, 2월: 28 또는 29일, 3월: 31일, 4월: 30일 ...)
     */
    public boolean vCheckBirth(String birth)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            Date ret = dateFormat.parse(birth.trim());
            if (dateFormat.format(ret).equals(birth.trim()))
                result = true;
        } catch (ParseException e) {
            result = false;
            //throw new RuntimeException(e);
        }
        return result;
    }
    
    public boolean dCheckName(String name)
    {
        Connection con = getConnection();
        PreparedStatement ps;
        ResultSet rs;
        Properties prop = new Properties();
        result = true;

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            ps = con.prepareStatement(prop.getProperty("duplicateCheckName"));
            ps.setString(1, name);
            rs = ps.executeQuery();

            while(rs.next())
                result = false;

        } catch (IOException | SQLException e) {
            result = false;
            // throw new RuntimeException(e);
        }
        return result;
    }
}