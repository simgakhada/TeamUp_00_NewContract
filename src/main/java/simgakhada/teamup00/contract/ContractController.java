package simgakhada.teamup00.contract;

import simgakhada.teamup00.run.MainScripts;
import simgakhada.teamup00.settings.SettingsDAO;
import simgakhada.teamup00.settings.settingsenum.Sort;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
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
    Scanner sc = new Scanner(System.in);
    private ContractDAO dao = new ContractDAO("src/main/resources/mapper/menu-query.xml");
    ContractScripts c = new ContractScripts();
    public void run()
    {
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
                    System.out.println();
                    runCase4();
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

    /**
     * runCase4
     * 연락처를 조회를 별도의 컨트롤러로 다룹니다.
     */
    public void runCase4()
    {
        Scanner sc = new Scanner(System.in);
        SettingsDAO settingsDAO = new SettingsDAO();
        Sort sort;

        while (true)
        {
            c.contractScriptCase4();
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice)
            {
                case 1:
                    int setting = settingsDAO.loadSortCondition();
                    dao.lookUp(getConnection2(), setting);
                    System.out.println("현재 저장된 기준으로 정렬하여 연락처를 조회하였습니다.");
                    settingsDAO.printSavedConditionSort();
                    System.out.println();
                    return;

                case 2:
                    c.contractScriptSort();
                    int choice2 = sc.nextInt();
                    if(choice2 == 0 || choice2 == 1 || choice2 == 2 || choice2 == 3 || choice2 == 4 || choice2 == 5 || choice2 == 6)
                    {
                        dao.lookUp(getConnection2(), choice2);
                        System.out.println("선택하신 기준으로 정렬하여 연락처를 조회하였습니다.");
                        sort = Sort.values()[choice2];
                        System.out.print("정렬 기준: ");
                        System.out.println(sort.getChoice());
                        System.out.println();
                        System.out.println("해당 설정을 저장하시겠습니까?");
                        System.out.print("답변 입력 (Y / N): ");
                        char YN = sc.next().charAt(0);
                        sc.nextLine();
                        if (YN == 'Y' || YN == 'y')
                            settingsDAO.saveSortCondition(choice2);
                        else if(YN == 'N' || YN == 'n')
                            System.out.println("설정을 저장하지 않고 이전으로 돌아갑니다.");
                    }
                    else if(choice2 == 9)
                    {
                        System.out.println();
                        System.out.println("이전으로 돌아갑니다.");
                        System.out.println();

                    }
                    else
                    {
                        System.out.println();
                        System.out.println("올바른 번호 입력에 실패하여 이전으로 돌아갑니다.");
                        System.out.println();
                    }
                    return;

                case 9:
                    System.out.println();
                    System.out.println("이전으로 돌아갑니다.");
                    System.out.println();
                    return;

                default:
                    System.out.println();
                    System.out.println("올바른 번호 입력에 실패하여 이전으로 돌아갑니다.");
                    System.out.println();
                    return;
            }
        }
    }
}
