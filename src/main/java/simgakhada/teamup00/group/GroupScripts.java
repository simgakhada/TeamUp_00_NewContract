package simgakhada.teamup00.group;

/**
 * GroupScripts
 * GroupController 클래스에서 사용되는 연속된 print(ln)을
 * 가시성을 위해 별도의 클래스로 분리하고자 합니다.
 * (사용하지 않아도 무방합니다.)
 */
public class GroupScripts
{
    public void groupScriptController()
    {
        System.out.println("[그룹 관리 및 조회] 연락처와 그에 대한 정보를 관리하고, 조회할 수 있습니다.");
        System.out.println("1. 그룹 추가");
        System.out.println("2. 그룹 삭제");
        System.out.println("3. 그룹 이름 변경");
        System.out.println("4. 그룹 내 연락처 관리");
        System.out.println("5. 그룹 출력");
        System.out.println("9. 메인 메뉴로 이동");
        System.out.print("번호 입력: ");
    }

    public void groupScriptCase4()
    {
        System.out.println("[그룹 내 연락처 관리] 그룹 내의 연락처를 관리할 수 있습니다.");
        System.out.println("1. 그룹 간 연락처 이동");
        System.out.println("2. 그룹 내 연락처 삭제");
        System.out.println("3. 그룹 내 연락처 조회");
        System.out.println("9. 뒤로가기");
        System.out.print("번호 입력: ");
    }
}
