package simgakhada.teamup00.run;

/**
 * MainScripts
 * MainController 클래스에서 사용되는 연속된 print(ln)을
 * 가시성을 위해 별도의 클래스로 분리하였습니다.
 */
public class MainScripts
{
    public void mainScriptController()
    {
        System.out.println("[전화번호부] 메인 메뉴");
        System.out.println("기능을 선택해주세요.");
        System.out.println("1. 연락처 관리 및 조회");
        System.out.println("2. 그룹 관리 및 조회");
        System.out.println("3. 연락처 검색");
        System.out.println("4. 통계");
        System.out.println("9. 설정");
        System.out.println("0. 프로그램 종료");
        System.out.print("번호 입력: ");
    }

    public void defaultMessage()
    {
        System.out.println();
        System.out.println("올바르지 않은 응답입니다.");
        System.out.println("사용법을 확인 후 다시 시도해주세요.");
        System.out.println();
    }
}
