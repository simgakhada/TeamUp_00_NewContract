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
}
