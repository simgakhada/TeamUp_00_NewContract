package simgakhada.teamup00.run;

import simgakhada.teamup00.contract.ContractController;
import simgakhada.teamup00.group.GroupController;
import simgakhada.teamup00.search.SearchController;
import simgakhada.teamup00.settings.SettingsController;
import simgakhada.teamup00.statistics.StatisticsController;

import java.util.Scanner;

/**
 * MainController
 * 기존의 main 메소드에서 진행되던 while과 switch... case문을
 * 별도의 클래스를 선언하여 해당 부분을 이전하였습니다.
 * 기능은 기존과 다르지 않습니다.
 */
public class MainController
{
    public void run()
    {
        Scanner sc = new Scanner(System.in);
        MainScripts m = new MainScripts();
        ContractController c = new ContractController();
        GroupController g = new GroupController();
        SearchController s = new SearchController();
        SettingsController se = new SettingsController();
        StatisticsController st = new StatisticsController();
        while (true)
        {
            m.mainScriptController();
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice)
            {
                case 1:
                    System.out.println();
                    c.run();
                    break;

                case 2:
                    System.out.println();
                    g.run();
                    break;

                case 3:
                    System.out.println();
                    s.run();
                    break;

                case 4:
                    System.out.println();
                    st.run();
                    break;

                case 9:
                    System.out.println();
                    se.run();
                    break;

                case 0:
                    System.out.println();
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);

                default:
                    m.defaultMessage();
                    break;
            }
        }
    }
}
