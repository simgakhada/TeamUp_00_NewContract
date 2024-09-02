package simgakhada.teamup00.contract;

/**
 * ContractScripts
 * ContractController 클래스에서 사용되는 연속된 print(ln)을
 * 가시성을 위해 별도의 클래스로 분리하였습니다.
 */
public class ContractScripts
{
    public void contractScriptController()
    {
        System.out.println("[연락처 관리 및 조회] 연락처와 그에 대한 정보를 관리하고, 조회할 수 있습니다.");
        System.out.println("1. 연락처 추가");
        System.out.println("2. 연락처 삭제");
        System.out.println("3. 연락처 변경");
        System.out.println("4. 연락처 조회");
        System.out.println("9. 메인 메뉴로");
        System.out.print("번호 입력: ");
    }

    public void contractScriptSort()
    {
        System.out.println();
        System.out.println("기존의 저장된 값을 사용하지 않고 기준을 직접 선택하여 정렬합니다.");
        System.out.println("아래의 선택지는 정렬의 기준을 의미합니다.");
        System.out.println("0. 정렬하지 않고 조회하기");
        System.out.println("1. 이름 (오름차순)    \t 2. 이름 (내림차순)");
        System.out.println("3. 전화번호 (오름차순) \t 4. 전화번호 (내림차순)");
        System.out.println("5. 생년월일 (오름차순) \t 6. 생년월일 (내림차순)");
        System.out.println("7. 그룹 이름 (오름차순) \t 8. 그룹 이름 (내림차순)");
        System.out.println("9. 뒤로가기");
        System.out.print("번호 입력: ");
    }

    public void contractScriptCase4()
    {
        System.out.println("[연락처 조회] 연락처를 특정 기준에 따라 정렬하거나, 정렬하지 않고 출력합니다.");
        System.out.println("1. 현재 저장된 기준에 따라 출력하기");
        System.out.println("2. 정렬 기준 직접 선택하기");
        System.out.println("9. 뒤로가기");
        System.out.print("번호 입력: ");
    }
}
