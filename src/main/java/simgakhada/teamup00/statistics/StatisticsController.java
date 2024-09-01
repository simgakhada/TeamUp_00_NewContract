package simgakhada.teamup00.statistics;

import java.util.Scanner;

public class StatisticsController
{
    public void run()
    {
        Scanner sc = new Scanner(System.in);
        StatisticsScripts s = new StatisticsScripts();
        while(true)
        {
            s.statisticsScriptsController();
            int choice = sc.nextInt();
            sc.close();
            switch (choice)
            {
                case 1:
                    break;

                case 2:
                    break;

                case 3:
                    break;

                case 9:
                    System.out.println("통계 확인을 마치고 메인 메뉴로 돌아갑니다.");
                    return;
            }
        }
    }
}
