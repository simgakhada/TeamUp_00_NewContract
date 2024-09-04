package simgakhada.teamup00.statistics;

import simgakhada.teamup00.run.MainScripts;
import java.util.Scanner;

import static simgakhada.teamup00.template.JDBCTemplate.getConnection;

public class StatisticsController
{
    public void run()
    {
        Scanner sc = new Scanner(System.in);
        StatisticsScripts s = new StatisticsScripts();
        StatisticsService ss = new StatisticsService();
        MainScripts m = new MainScripts();
        while(true)
        {
            s.statisticsScriptsController();
            String choice = sc.nextLine();
            //sc.nextLine();
            switch (choice)
            {
                case "1":
                    ss.numberOfContracts(getConnection());
                    break;

                case "2":
                    ss.numberOfContractsInGroup(getConnection());
                    break;

                case "3":
                    ss.findTheLatestAddedContract(getConnection());
                    break;

                case "9":
                    System.out.println("통계 확인을 마치고 메인 메뉴로 돌아갑니다.");
                    System.out.println();
                    return;

                default:
                    m.defaultMessage();
                    break;
            }
        }
    }
}
