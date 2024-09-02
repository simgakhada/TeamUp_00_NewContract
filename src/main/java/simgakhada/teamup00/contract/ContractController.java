package simgakhada.teamup00.contract;

import simgakhada.teamup00.run.MainScripts;

import java.util.Scanner;

import static simgakhada.teamup00.template.JDBCTemplate.getConnection2;

/**
 * ContractController
 * 연락처를 관리하는 메뉴를 출력하는 클래스입니다.
 *
 * 기존 대비 변경점
 * '조회' 항목 중 '연락처 조회' 기능을 '연락처 관리' 항목으로 이관합니다.
 */
public class ContractController
{
    private ContractDAO dao = new ContractDAO("src/main/resources/mapper/menu-query.xml");

    public void run()
    {
        Scanner sc = new Scanner(System.in);
        ContractScripts c = new ContractScripts();
        MainScripts m = new MainScripts();
        while(true)
        {
            c.contractScriptController();
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice)
            {
                case 1 :
                    dao.add(getConnection2());
                    break;

                case 2 :
                    dao.delete(getConnection2());
                    break;

                case 3 :
                    dao.update(getConnection2());
                    break;

                case 4 :
                    break;

                case 9 :
                    System.out.println();
                    System.out.println("연락처 관리를 마치고 메인 메뉴로 돌아갑니다.");
                    System.out.println();
                    return;

                default :
                    m.defaultMessage();
                    break;
            }
        }
    }
}
