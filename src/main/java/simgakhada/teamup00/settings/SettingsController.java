package simgakhada.teamup00.settings;

import simgakhada.teamup00.run.MainScripts;

import java.util.Objects;
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
        SettingsService dao = new SettingsService();
        SettingsScripts s = new SettingsScripts();
        MainScripts m = new MainScripts();
        while (true)
        {
            s.settingsScriptController();
            String choice = sc.nextLine();
            //sc.nextLine();

            switch (choice)
            {
                case "1":
                    dao.printSavedCondition();
                    break;

                case "2":
                    s.settingsScriptSearch();
                    String choice2 = sc.nextLine();
                    //sc.nextLine();
                    if (Objects.equals(choice2, "0") || Objects.equals(choice2, "1") || Objects.equals(choice2, "2") || Objects.equals(choice2, "3") || Objects.equals(choice2, "4") || Objects.equals(choice2, "5"))
                    {
                        dao.saveSearchCondition(Integer.parseInt(choice2));
                    }
                    else if(Objects.equals(choice2, "9"))
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

                case "3":
                    s.settingsScriptSort();
                    String choice3 = sc.nextLine();
                    //sc.nextLine();
                    if (Objects.equals(choice3, "0") || Objects.equals(choice3, "1") || Objects.equals(choice3, "2") || Objects.equals(choice3, "3") || Objects.equals(choice3, "4") || Objects.equals(choice3, "5") || Objects.equals(choice3, "6") || Objects.equals(choice3, "7") || Objects.equals(choice3, "8"))
                    {
                        dao.saveSortCondition(Integer.parseInt(choice3));
                    }
                    else if(Objects.equals(choice3, "9"))
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

                case "4":
                    dao.autoSaveOnOff();
                    break;

                case "5":
                    dao.lockUnlock();
                    break;

                case "6":
                    dao.changePassword();
                    break;

                case "9":
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
