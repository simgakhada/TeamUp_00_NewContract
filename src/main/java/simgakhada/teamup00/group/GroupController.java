package simgakhada.teamup00.group;

import simgakhada.teamup00.contract.ContractDAO;
import simgakhada.teamup00.run.MainScripts;
import simgakhada.teamup00.settings.SettingsDAO;
import simgakhada.teamup00.settings.settingsenum.Sort;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import static simgakhada.teamup00.template.JDBCTemplate.getConnection;
import static simgakhada.teamup00.template.JDBCTemplate.getConnection2;

/**
 * GroupController
 * 그룹 관리하는 메뉴를 출력하는 클래스입니다.
 *
 * 기존 대비 변경점
 * '조회' 항목 중 '그룹 조회' 기능을 '그룹 관리' 항목으로 이관합니다.
 */
public class GroupController
{
    public void run()
    {
        Scanner sc = new Scanner(System.in);
        GroupDAO dao = new GroupDAO("src/main/resources/mapper/menu-query.xml");
        GroupScripts g = new GroupScripts();
        MainScripts m = new MainScripts();

        while(true)
        {
            g.groupScriptController();
            int choice = sc.nextInt();
            sc.nextLine();
            switch(choice)
            {
                case 1:
                    dao.add(getConnection());
                    break;

                case 2:
                    dao.delete(getConnection());
                    break;

                case 3:
                    dao.update(getConnection());
                    break;

                case 4:
                    runCase4();
                    break;

                case 9:
                    System.out.println();
                    System.out.println("그룹 관리를 마치고 메인 메뉴로 돌아갑니다.");
                    return;

                default:
                    m.defaultMessage();
                    break;
            }
        }
    }

    /**
     * runCase4
     * 그룹 내 연락처 추가, 삭제, 이동을 별도의 컨트롤러로 다룹니다.
     */
    public void runCase4()
    {
        Scanner sc = new Scanner(System.in);
        GroupDAO dao = new GroupDAO("src/main/resources/mapper/menu-query.xml");
        SettingsDAO settingsDAO = new SettingsDAO();
        GroupScripts g = new GroupScripts();
        MainScripts m = new MainScripts();
        GroupService gs = new GroupService();

        while (true)
        {
            g.groupScriptCase4();
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice)
            {
                case 1:
                    break;

                case 2:
                    break;

                case 9:
                    System.out.println();
                    System.out.println("이전으로 돌아갑니다.");
                    System.out.println();
                    return;

                default:
                    m.defaultMessage();
                    break;
            }
        }
    }
}
