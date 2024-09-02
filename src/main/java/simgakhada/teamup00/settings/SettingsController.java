package simgakhada.teamup00.settings;

import simgakhada.teamup00.run.MainScripts;

import java.util.Scanner;

/**
 * SettingsController
 * 설정을 관리하는 메뉴를 출력하는 클래스입니다.
 */
public class SettingsController
{
    public void run()
    {
        Scanner sc = new Scanner(System.in);
        SettingsDAO dao = new SettingsDAO();
        SettingsScripts s = new SettingsScripts();
        MainScripts m = new MainScripts();
        while (true)
        {
            s.settingsScriptController();
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice)
            {
                case 1:
                    dao.printSavedCondition();
                    break;

                case 2:
                    s.settingsScriptSearch();
                    int choice2 = sc.nextInt();
                    sc.nextLine();
                    if (choice2 == 0 || choice2 == 1 || choice2 == 2 || choice2 == 3 || choice2 == 4 || choice2 == 5)
                    {
                        dao.saveSearchCondition(choice2);
                    }
                    else if(choice2 == 9)
                    {
                        System.out.println();
                        System.out.println("이전으로 돌아갑니다.");
                        System.out.println();
                    }
                    else
                    {
                        m.defaultMessage();
                    }
                    break;

                case 3:
                    s.settingsScriptSort();
                    int choice3 = sc.nextInt();
                    sc.nextLine();
                    if (choice3 == 0 || choice3 == 1 || choice3 == 2 || choice3 == 3 || choice3 == 4 || choice3 == 5 || choice3 == 6 || choice3 == 7 || choice3 == 8)
                    {
                        dao.saveSortCondition(choice3);
                    }
                    else if(choice3 == 9)
                    {
                        System.out.println();
                        System.out.println("이전으로 돌아갑니다.");
                        System.out.println();
                    }
                    else
                    {
                        m.defaultMessage();
                    }
                    break;

                case 9:
                    System.out.println();
                    System.out.println("설정을 마치고 메인 메뉴로 이동합니다.");
                    System.out.println();
                    return;

                default:
                    m.defaultMessage();
                    break;
            }
        }
    }
}
