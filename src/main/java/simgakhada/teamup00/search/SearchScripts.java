package simgakhada.teamup00.search;

import simgakhada.teamup00.settings.SettingsDAO;

public class SearchScripts
{
    SettingsDAO settingsDAO = new SettingsDAO();
    public void searchScriptsController()
    {
        System.out.println("[연락처 검색] 입력한 내용과 일치하는 연락처를 찾아 출력합니다.");
        System.out.print("현재 저장된 ");
        settingsDAO.printSavedConditionSearch();
        System.out.println("1. 현재 저장된 기준에 따라 검색하기");
        System.out.println("2. 검색 기준 직접 선택하기");
        System.out.println("9. 뒤로가기");
        System.out.print("번호 입력: ");
    }
    public void searchScriptsCase2()
    {
        System.out.println("기존의 저장된 값을 사용하지 않고 방법을 직접 선택하여 검색합니다.");
        System.out.println("아래의 선택지는 검색 방법을 의미합니다.");
        System.out.println("1. 이름");
        System.out.println("2. 전화번호 (전체)");
        System.out.println("3. 전화번호 (4자리)");
        System.out.println("4. 이메일");
        System.out.println("5. 주소");
        System.out.println("9. 뒤로가기");
        System.out.print("번호 입력: ");
    }
}
