package simgakhada.teamup00.statistics;

/**
 * StatisticsScripts
 * StatisticsController 클래스에서 사용되는 연속된 print(ln)을
 * 가시성을 위해 별도의 클래스로 분리하고자 합니다.
 * (사용하지 않아도 무방합니다.)
 */
public class StatisticsScripts
{
    public void statisticsScriptsController()
    {
        System.out.println("[통계] 현재 저장된 연락처 등의 정보를 토대로 통계를 제공합니다.");
        System.out.println("1. 현재 저장된 연락처의 개수 출력");
        System.out.println("2. 현재 그룹에 속한 연락처의 개수 출력");
        System.out.println("3. 마지막으로 저장된 연락처 출력");
        System.out.println("9. 메인 메뉴로 이동");
        System.out.print("번호 입력: ");
    }
}
