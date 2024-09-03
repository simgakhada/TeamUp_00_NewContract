package simgakhada.teamup00.search;

import simgakhada.teamup00.run.MainScripts;
import simgakhada.teamup00.settings.SettingsDAO;
import simgakhada.teamup00.settings.settingsenum.Search;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import static simgakhada.teamup00.template.JDBCTemplate.getConnection2;

public class SearchController
{
    File path = new File("src/main/resources/config/settings.properties");
    Properties prop = new Properties();
    FileInputStream fis;
    SearchService searchService = new SearchService();
    SettingsDAO settingsDAO = new SettingsDAO();
    Search search;
    public void run()
    {
        Scanner sc = new Scanner(System.in);
        SearchScripts s = new SearchScripts();
        MainScripts m = new MainScripts();
        int choice2 = 0;
        try {
            fis = new FileInputStream(path);
            prop.load(fis);
            fis.close();
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
                            System.out.println("저장된 검색 기준이 없습니다.");
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
                        choice2 = sc.nextInt();
                        sc.nextLine();
                        if(choice2 == 1 || choice2 == 2 || choice2 == 3 || choice2 == 4 || choice2 == 5)
                        {
                            runSupport(choice2);
                            System.out.println();
                            if (prop.getProperty("autoSave").equals("true"))
                            {
                                System.out.println("자동 저장이 켜져있습니다.");
                                System.out.println("설정을 저장하고 이전으로 돌아갑니다.");
                                settingsDAO.saveSearchCondition(choice2);
                            }
                            else
                            {
                                System.out.println("이번에 사용한 검색 기준을 설정에 저장하시겠습니까?");
                                System.out.print("답변 입력 ('Y', 'y', '네', '예' 이외의 대답은 모두 '아니오'로 처리합니다.): ");
                                char YN = sc.next().charAt(0);
                                sc.nextLine();
                                if (YN == 'Y' || YN == 'y' || YN == '네' || YN == '예')
                                    settingsDAO.saveSearchCondition(choice2);
                                else
                                    System.out.println("저장하지 않고 이전으로 돌아갑니다.");
                            }
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void runSupport(int num)
    {
        Scanner sc = new Scanner(System.in);
        String searchWith;
        search = Search.values()[num];
        System.out.println("검색에 필요한 " + search.getChoice() + "을(를) 입력해주세요.");
        if(num == 3)
        {
            do
            {
                System.out.println("검색 조건이 " + search.getChoice() + "일 경우 전화번호 뒤 4자리와 일치하는 검색 결과를 출력합니다.");
                System.out.print(search.getChoice() + " 입력: ");
                searchWith = sc.nextLine();
            } while(searchWith.length() != 4);
        }
        else
        {
            System.out.print(search.getChoice() + " 입력: ");
            searchWith = sc.nextLine();
        }
        searchService.superSearch(getConnection2(), searchWith, num);
        search = Search.values()[num];
        System.out.println("현재 저장된 기준으로 검색하여 연락처를 출력하였습니다.");
        System.out.println("검색 기준: " + search.getChoice());
    }
}
