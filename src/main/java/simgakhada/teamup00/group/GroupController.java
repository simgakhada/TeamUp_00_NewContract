package simgakhada.teamup00.group;

import simgakhada.teamup00.run.MainScripts;

import java.util.Scanner;
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
        GroupDAO dao = new GroupDAO();
        GroupScripts g = new GroupScripts();
        MainScripts m = new MainScripts();
        GroupSearch gs = new GroupSearch();
        while(true)
        {
            g.groupScriptsController();
            int choice = sc.nextInt();
            sc.nextLine();
            switch(choice)
            {
                case 1:
                    break;

                case 2:
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
}
