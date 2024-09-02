package simgakhada.teamup00.search;

import simgakhada.teamup00.run.MainScripts;
import simgakhada.teamup00.settings.SettingsDAO;
import simgakhada.teamup00.settings.settingsenum.Search;

import java.util.Scanner;

import static simgakhada.teamup00.template.JDBCTemplate.getConnection2;

public class SearchController
{
    SearchService searchService = new SearchService();
    SettingsDAO settingsDAO = new SettingsDAO();
    Search search;
    public void run()
    {
        Scanner sc = new Scanner(System.in);
        SearchScripts s = new SearchScripts();
        MainScripts m = new MainScripts();
        while (true)
        {
            s.searchScriptsController();
            int choice = sc.nextInt();
            sc.nextLine();
            System.out.println();
            switch (choice)
            {
                case 1:
                    int setting = settingsDAO.loadSearchCondition();
                    if(setting == 0)
                    {
                        System.out.println("저장된 설정이 없습니다.");
                        System.out.println("이전으로 돌아갑니다.");
                    }
                    else
                    {
                        runSupport(setting);
                    }
                    System.out.println();
                    return;

                case 2:
                    s.searchScriptsCase2();
                    int choice2 = sc.nextInt();
                    sc.nextLine();
                    if(choice2 == 1 || choice2 == 2 || choice2 == 3 || choice2 == 4 || choice2 == 5)
                    {
                        runSupport(choice2);
                        System.out.println();
                        System.out.println("해당 설정을 저장하시겠습니까?");
                        System.out.print("답변 입력 (Y / N): ");
                        char YN = sc.next().charAt(0);
                        sc.nextLine();
                        if (YN == 'Y' || YN == 'y')
                            settingsDAO.saveSearchCondition(choice2);
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
                        m.defaultMessage();
                    }
                    return;

                case 9:
                    System.out.println();
                    System.out.println("메인 메뉴로 돌아갑니다.");
                    System.out.println();
                    return;

                default:
                    m.defaultMessage();
                    break;
            }
        }
    }

    public void runSupport(int num)
    {
        Scanner sc = new Scanner(System.in);
        search = Search.values()[num];
        System.out.println("검색에 필요한 " + search.getChoice() + "을(를) 입력해주세요.");
        System.out.print(search.getChoice() + " 입력: ");
        String searchWith = sc.nextLine();
        searchService.superSearch(getConnection2(), searchWith, num);
    }
}
