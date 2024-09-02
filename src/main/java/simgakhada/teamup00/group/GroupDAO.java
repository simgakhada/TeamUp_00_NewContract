package simgakhada.teamup00.group;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * GroupDAO
 * 그룹의 추가, 수정, 삭제를 구현하는 메소드입니다.
 * 분리된 '그룹 조회' 항목도 해당 클래스 내에 추가합니다.
 */
public class GroupDAO
{
    private Properties prop = new Properties();

    public GroupDAO(String url) {
        try {
            prop.loadFromXML(new FileInputStream(url));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(String groupName) { // 그룹 추가

    }

    public void delete() { // 그룹 삭체

    }

    public void update() { // 그룹
    }

    public void loopUp()
    {

    }
}
